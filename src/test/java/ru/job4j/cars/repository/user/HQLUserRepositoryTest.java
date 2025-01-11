package ru.job4j.cars.repository.user;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.job4j.cars.model.User;
import ru.job4j.cars.repository.CrudRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class HQLUserRepositoryTest {

    private static HQLUserRepository userRepository;

    private static SessionFactory sf;

    @BeforeAll
    public static void initRepository() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        userRepository = new HQLUserRepository(new CrudRepository(sf));
    }

    @AfterEach
    void cleanDatabase() {
        try (var session = sf.openSession()) {
            session.beginTransaction();
            session.createQuery("DELETE FROM User").executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Test
    void whenCreateUserThanOk() {
        var user = new User();
        user.setLogin("user");
        user.setPassword("test");
        assertThat(userRepository.create(user)).isEqualTo(user);
        assertThat(userRepository.findById(user.getId())).isEqualTo(Optional.of(user));
    }

    @Test
    void whenFindByIdThanOk() {
        var user = new User();
        user.setLogin("user3");
        user.setPassword("test3");
        userRepository.create(user);
        assertThat(userRepository.findById(user.getId())).isEqualTo(Optional.of(user));
    }

    @Test
    void whenFindByLikeLoginThanOk() {
        var user = new User();
        user.setLogin("user4");
        user.setPassword("test4");
        userRepository.create(user);
        assertThat(userRepository.findByLikeLogin("user4")).contains(user);
    }

    @Test
    void whenFindByLoginThanOk() {
        var user = new User();
        user.setLogin("user5");
        user.setPassword("test5");
        userRepository.create(user);
        var rsl = userRepository.findByLoginAndPassword(user.getLogin(), user.getPassword()).get();
        assertThat(rsl).isEqualTo(user);
    }
}