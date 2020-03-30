package dev.samuk.auth.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "phone")
public class Phone {

    @Id
    @GeneratedValue
    private long id;

    private String number;

    private String ddd;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="id_user")
    private User user;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", ddd='" + ddd + '\'' +
                ", user=" + user +
                '}';
    }
}
