package br.com.nuture.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "NTR_T_DIET")
@SequenceGenerator(name = "diet", sequenceName = "SQ_DIET", allocationSize = 1)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Diet {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "diet")
    @Column(name = "id_diet")
    private Long id;

    @Column(name = "ds_diet", length = 150, nullable = false)
    @NotBlank
    @Size(max = 150, message = "Description must be with maximum 150 characters")
    private String description;

    @Column(name = "ds_breakfast", length = 150, nullable = false)
    @NotBlank
    @Size(max = 150, message = "Breakfast must be with maximum 150 characters")
    private String breakfast;

    @Column(name = "ds_lunch", length = 150, nullable = false)
    @NotBlank
    @Size(max = 150, message = "Lunch must be with maximum 150 characters")
    private String lunch;

    @Column(name = "ds_afternoon_coffee", length = 150, nullable = true)
    @Size(max = 150, message = "Afternoon coffee must be with maximum 150 characters")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY, value = "afternoon_coffee")
    private String afternoonCoffee;

    @Column(name = "ds_dinner", length = 150, nullable = false)
    @NotBlank
    @Size(max = 150, message = "Dinner must be with maximum 150 characters")
    private String dinner;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    @NotNull
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY, value = "user")
    private User user;

}
