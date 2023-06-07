package br.com.nuture.repository;

import br.com.nuture.model.Recipe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    Page<Recipe> findByNameContaining(String name, Pageable pageable);
}
