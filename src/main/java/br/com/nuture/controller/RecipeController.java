package br.com.nuture.controller;

import br.com.nuture.exception.RestNotFoundException;
import br.com.nuture.model.Recipe;
import br.com.nuture.repository.RecipeRepository;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("/nuture/recipes")
public class RecipeController {

    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private RecipeRepository repository;

    @PostMapping
    public ResponseEntity<Recipe> create(@RequestBody @Valid Recipe recipe) {
        log.info("creating recipe: " + recipe);
        repository.save(recipe);
        recipe.getIngredients().forEach(i -> i.setRecipe(recipe));
        repository.save(recipe);
        return ResponseEntity.status(HttpStatus.CREATED).body(recipe);
    }

    @PutMapping("{id}")
    public ResponseEntity<Recipe> update(@PathVariable Long id, @RequestBody @Valid Recipe recipe) {
        log.info("updating recipe with id " + id);
        getRecipe(id);
        recipe.setId(id);
        repository.save(recipe);
        return ResponseEntity.ok(recipe);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Recipe> destroy(@PathVariable Long id) {
        log.info("deleting recipe with id " + id);
        repository.delete(getRecipe(id));
        return ResponseEntity.noContent().build();
    }

    @GetMapping("{id}")
    public ResponseEntity<Recipe> show(@PathVariable Long id) {
        log.info("searching recipe with id " + id);
        return ResponseEntity.ok(getRecipe(id));
    }

    @GetMapping
    public ResponseEntity<Page<Recipe>> index(@RequestParam(required = false) String name, @PageableDefault(size = 5, sort = "name") Pageable pageable) {
        Page<Recipe> recipes = (name == null)?
                repository.findAll(pageable):
                repository.findByNameContaining(name, pageable);
        return ResponseEntity.ok(recipes);
    }

    private Recipe getRecipe(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RestNotFoundException("recipe not found"));
    }

}
