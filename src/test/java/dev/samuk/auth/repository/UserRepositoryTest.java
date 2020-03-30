package dev.samuk.auth.repository;

import dev.samuk.auth.entity.Phone;
import dev.samuk.auth.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.validation.UnexpectedTypeException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void test_example_save() {
        User user = new User();
        Phone phone = new Phone();
        User userSave = create(user, phone);

        assertEquals(user.getName(), userSave.getName());
        assertEquals(user.getPassword(), userSave.getPassword());
        assertEquals(user.getEmail(), userSave.getEmail());
        assertEquals(user.getPhones().get(0).getDdd(), phone.getDdd());
        assertEquals(user.getPhones().get(0).getNumber(), phone.getNumber());
    }

    private User create(User user, Phone phone) {
        user.setName("João da Silva");
        user.setEmail("joao@silva.org");
        user.setPassword("hunter2");
        user.setPhones(new ArrayList<>());
        phone.setDdd("21");
        phone.setNumber("987654321");
        user.getPhones().add(phone);
        User userSave = this.userRepository.save(user);
        phone.setUser(userSave);
        return userSave;
    }

    @Test
    public void test_update() {
        User user = new User();
        Phone phone = new Phone();
        User userSave = create(user, phone);
        userSave.setName("Alterado");
        User userupdate = this.userRepository.save(userSave);
        assertEquals(userSave.getName(), userupdate.getName());
    }

    @Test
    public void test_delete(){
        User user = new User();
        Phone phone = new Phone();
        User userSave = create(user, phone);
        this.userRepository.delete(userSave);
        assertNull(this.userRepository.findById(userSave.getId()).orElse(null));
    }


    @Test
    public void test_example_save_valid_email(){
        User user = new User();
        Phone phone = new Phone();
        create(user, phone);
        user = new User();
        phone = new Phone();
        create(user, phone);
        try {
            this.userRepository.findAll();
        } catch (UnexpectedTypeException e) {
            assertNotNull(e);
        }
    }



}