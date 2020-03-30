package dev.samuk.auth.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;

@Entity()
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue
    private long id;

    @NotBlank(message = "Name obrigatório")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "E-mail obrigatório")
    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    private LocalDate created;

    private LocalDate modified;

    @OneToMany(mappedBy="user",cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Phone> phones;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public LocalDate getModified() {
        return modified;
    }

    public void setModified(LocalDate modified) {
        this.modified = modified;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", created=" + created +
                ", modified=" + modified +
                ", phones=" + phones +
                '}';
    }
}
