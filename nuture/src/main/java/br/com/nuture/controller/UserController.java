package br.com.nuture.controller;

import br.com.nuture.exception.RestNotFoundException;
import br.com.nuture.model.Credencial;
import br.com.nuture.model.User;
import br.com.nuture.repository.UserRepository;
import br.com.nuture.service.TokenService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
@RestController
@RequestMapping("/nuture/users")
public class UserController {

    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserRepository repository;

    @Autowired
    AuthenticationManager manager;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    TokenService tokenService;

    @PostMapping
    public ResponseEntity<User> create(@RequestBody @Valid User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        log.info("creating user: " + user);
        repository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PutMapping("{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody @Valid User user) {
        log.info("updating user with id " + id);
        getUser(id);
        user.setId(id);
        user.setPassword(getUser(id).getPassword());
        repository.save(user);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<User> destroy(@PathVariable Long id) {
        log.info("deleting user with id " + id);
        repository.delete(getUser(id));
        return ResponseEntity.noContent().build();
    }

    @GetMapping("{id}")
    public ResponseEntity<User> show(@PathVariable Long id) {
        log.info("searching user with id " + id);
        return ResponseEntity.ok(getUser(id));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<User> getByEmail(@PathVariable String email) {
        log.info("searching user with email " + email);
        return ResponseEntity.ok(getUser(email));
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Valid Credencial credencial){
        log.info("logging in");
        manager.authenticate(credencial.toAuthentication());
        var token = tokenService.generateToken(credencial);
        return ResponseEntity.ok(token);
    }

    private User getUser(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RestNotFoundException("user not found"));
    }

    private User getUser(String email) {
        return repository.findByEmail(email)
                .orElseThrow(() -> new RestNotFoundException("user not found"));
    }
}
