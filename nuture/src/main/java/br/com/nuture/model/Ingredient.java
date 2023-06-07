package br.com.nuture.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "NTR_T_INGREDIENTS")
@SequenceGenerator(name = "ingredient", sequenceName = "SQ_INGREDIENTS", allocationSize = 1)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ingredient")
    @Column(name = "id_ingredients")
    private Long id;

    @Column(name = "nm_ingredient", nullable = false, length = 60)
    @NotBlank
    @Size(max = 60, message = "Name must be maximum 60 characters")
    private String name;

    @Column(name = "qtd_ingredient", nullable = false, length = 10)
    @NotBlank
    @Size(max = 60, message = "Quantity must be maximum 60 characters")
    private String quantity;

    @Column(name = "nm_category", nullable = false, length = 25)
    @NotBlank
    @Size(max = 60, message = "Category must be maximum 60 characters")
    private String category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_recipe")
    @JsonIgnore
    private Recipe recipe;

}