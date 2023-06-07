package br.com.nuture.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "NTR_T_RECIPE")
@SequenceGenerator(name = "recipe", sequenceName = "SQ_RECIPE", allocationSize = 1)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "recipe")
    @Column(name = "id_recipe")
    private Long id;

    @Column(name = "nm_recipe", nullable = false, length = 100)
    @NotBlank
    private String name;

    @Column(name = "ds_description", nullable = false)
    @NotBlank
    private String description;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
    @Valid
    private List<Ingredient> ingredients = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY, value = "user")
    @NotNull
    private User user;

}
