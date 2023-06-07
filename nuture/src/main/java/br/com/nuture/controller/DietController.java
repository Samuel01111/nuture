package br.com.nuture.controller;

import br.com.nuture.exception.RestNotFoundException;
import br.com.nuture.model.Diet;
import br.com.nuture.repository.DietRepository;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
@RestController
@RequestMapping("/nuture/diets")
public class DietController {

    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private DietRepository repository;

    @PostMapping
    public ResponseEntity<Diet> create(@RequestBody @Valid Diet diet) {
        log.info("creating diet: " + diet);
        repository.save(diet);
        return ResponseEntity.status(HttpStatus.CREATED).body(diet);
    }

    @PutMapping("{id}")
    public ResponseEntity<Diet> update(@PathVariable Long id, @RequestBody @Valid Diet diet) {
        log.info("updating diet with id " + id);
        getDiet(id);
        diet.setId(id);
        repository.save(diet);
        return ResponseEntity.ok(diet);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Diet> destroy(@PathVariable Long id) {
        log.info("deleting diet with id " + id);
        repository.delete(getDiet(id));
        return ResponseEntity.noContent().build();
    }

    @GetMapping("{id}")
    public ResponseEntity<Diet> show(@PathVariable Long id) {
        log.info("searching diet with id " + id);
        return ResponseEntity.ok(getDiet(id));
    }

    @GetMapping
    public ResponseEntity<Page<Diet>> index(@PageableDefault(size = 5) Pageable pageable) {
        Page<Diet> diets = repository.findAll(pageable);
        return ResponseEntity.ok(diets);
    }

    private Diet getDiet(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RestNotFoundException("diet not found"));
    }


}
