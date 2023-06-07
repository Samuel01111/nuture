package br.com.nuture.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "NTR_T_USER")
@SequenceGenerator(name = "user", sequenceName = "SQ_USER", allocationSize = 1)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user")
    @Column(name = "id_user")
    private Long id;

    @Column(name = "nm_user", length = 100, nullable = false)
    @NotBlank
    private String name;

    @Column(name = "nr_cpf", nullable = false, unique = true, length = 11)
    @NotNull
    @Min(10000000000L)
    @Max(99999999999L)
    private long cpf;

    @Column(name = "ds_email", nullable = false, unique = true)
    @NotBlank
    @Email
    private String email;

    @Column(name = "ds_weight", nullable = false)
    @NotBlank
    private String weight;

    @Column(name = "ds_height", nullable = false)
    @NotBlank
    private String height;

    @Column(name = "dt_birthday", nullable = false)
    @NotNull
    @JsonDeserialize
    private LocalDate birthday;

    @Column(name = "ds_sex", nullable = false, length = 10)
    @NotBlank
    @Size(max = 10, message = "Sex has to be maximum 10 characters")
    private String sex;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "ds_food_freq", nullable = false)
    @NotNull
    @JsonProperty(value = "food_frequency")
    private FoodFrequency foodFrequency;

    @Column(name = "ds_password", length = 60)
    @Size(min = 8, message = "Password must be minimum 8 characters")
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_phone")
    @NotNull
    @Valid
    private Phone phone;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Diet> diets;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Recipe> recipes;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USUARIO"));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cpf=" + cpf +
                ", email='" + email + '\'' +
                ", weight='" + weight + '\'' +
                ", height='" + height + '\'' +
                ", birthday=" + birthday +
                ", sex='" + sex + '\'' +
                ", foodFrequency=" + foodFrequency +
                ", password='" + password + '\'' +
                ", phone=" + phone +
                ", diets=" + diets +
                ", recipes=" + recipes +
                '}';
    }
}
