package dev.samuk.auth.repository;

import dev.samuk.auth.entity.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneRepository extends JpaRepository<Phone, Long> {

}
