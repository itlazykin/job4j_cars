package ru.job4j.cars.repository.type;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.job4j.cars.model.Type;
import ru.job4j.cars.repository.CrudRepository;

import static org.assertj.core.api.Assertions.*;

class HQLTypeRepositoryTest {

    private static HQLTypeRepository carTypeRepository;

    private static SessionFactory sf;

    @BeforeAll
    public static void initRepository() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        carTypeRepository = new HQLTypeRepository(new CrudRepository(sf));
    }

    @AfterEach
    void cleanDatabase() {
        try (var session = sf.openSession()) {
            session.beginTransaction();
            session.createQuery("DELETE FROM Type").executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Test
    void whenSaveMethodThanOk() {
        var type = new Type();
        type.setName("test");
        assertThat(carTypeRepository.save(type)).isEqualTo(type);
    }

    @Test
    void whenFindByIdThanOk() {
        var type = new Type();
        type.setName("test");
        carTypeRepository.save(type);
        var rsl = carTypeRepository.findById(type.getId()).get();
        assertThat(rsl).isEqualTo(type);
    }

    @Test
    void whenFindAllThanOk() {
        var type = new Type();
        carTypeRepository.save(type);
        assertThat(carTypeRepository.findAll()).contains(type);
    }
}