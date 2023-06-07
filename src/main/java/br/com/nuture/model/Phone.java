package br.com.nuture.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "NTR_T_PHONE")
@SequenceGenerator(name = "phone", sequenceName = "SQ_PHONE", allocationSize = 1)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "phone")
    @Column(name = "id_phone")
    private Long id;

    @Column(name = "nr_ddi", nullable = false)
    @NotNull
    @Positive
    private int ddi;

    @Column(name = "nr_ddd", nullable = false)
    @NotNull
    @Positive
    private int ddd;

    @Column(name = "nr_phone", nullable = false)
    @NotNull
    @Positive
    @JsonProperty(value = "phone_number")
    private int phoneNumber;

    @OneToOne(mappedBy = "phone")
    @JsonIgnore
    private User user;

}
