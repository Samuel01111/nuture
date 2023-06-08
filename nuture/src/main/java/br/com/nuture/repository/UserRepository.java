package br.com.nuture.repository;

import br.com.nuture.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository 
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select (count(u) > 0) from User u where u.email = :email")
    boolean existsByEmail(String email);

    @Query("select (count(u) > 0) from User u where u.cpf = :cpf")
    boolean existsByCpf(Long cpf);

    @Query("SELECT user FROM User user WHERE user.email = :email")
    Optional<User> findByEmail(@Param("email") String email);

}
