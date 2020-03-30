package dev.samuk.auth.controller;

import dev.samuk.auth.entity.Phone;
import dev.samuk.auth.repository.PhoneRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PhoneController {

    private final PhoneRepository repository;

    public PhoneController(PhoneRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/phones")
    public List<Phone> all() {
        return repository.findAll();
    }

    @GetMapping("/phones/{id}")
    public Optional<Phone> findById(@PathVariable Long id) {
        return repository.findById(id);
    }

    @PutMapping("/phones/{id}")
    public Optional<Phone> update(Phone user, @PathVariable Long id) {
        return repository.findById(id).map(user1 -> repository.save(user));
    }

    @PostMapping("/phones")
    public Phone insert(Phone user) {
        return repository.save(user);
    }

    @DeleteMapping("/phones/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
