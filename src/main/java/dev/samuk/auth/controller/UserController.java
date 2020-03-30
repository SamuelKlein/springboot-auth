package dev.samuk.auth.controller;

import dev.samuk.auth.entity.User;
import dev.samuk.auth.repository.PhoneRepository;
import dev.samuk.auth.repository.UserRepository;
import dev.samuk.auth.valid.UniqueEmail;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Validated
@RestController
public class UserController {

    private final UserRepository repository;
    private final PhoneRepository phoneRepoitory;

    public UserController(UserRepository repository, PhoneRepository phoneRepository) {
        this.repository = repository;
        this.phoneRepoitory = phoneRepository;
    }

    @GetMapping("/users")
    public List<User> all() {
        return repository.findAll();
    }

    @GetMapping("/users/{id}")
    public Optional<User> findById(@PathVariable Long id) {
        return repository.findById(id);
    }

    @PutMapping("/users/{id}")
    public Optional<User> update(@Valid @RequestBody User user, @PathVariable Long id) {
        return repository.findById(id).map( user1 -> {
            user1.setModified(LocalDate.now());
            return user1;
        }).map(repository::save);
    }

    @PostMapping("/users")
    public Optional<User> insert(@Valid @UniqueEmail @RequestBody User user) {
        return Optional.ofNullable(user).map( user1 -> {
            user1.setCreated(LocalDate.now());
            user1.setModified(LocalDate.now());
            return user1;
        })
          .map(repository::save)
          .map(user1 -> {

              user.getPhones().forEach(phone -> {
                  phone.setUser(user1);
                  this.phoneRepoitory.save(phone);
                  phone.setUser(null);
              });
              return user1;
          });
    }

    @DeleteMapping("/users/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
