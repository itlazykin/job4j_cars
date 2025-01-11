package ru.job4j.cars.repository.car;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.job4j.cars.model.Car;
import ru.job4j.cars.model.HistoryOwners;
import ru.job4j.cars.repository.CrudRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

class HQLCarRepositoryTest {

    private static HQLCarRepository carRepository;

    private static SessionFactory sf;

    private static Set<HistoryOwners> historyOwners = new HashSet<>();

    @BeforeAll
    public static void initRepository() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        carRepository = new HQLCarRepository(new CrudRepository(sf));
    }

    @AfterEach
    void cleanDatabase() {
        try (var session = sf.openSession()) {
            session.beginTransaction();
            session.createQuery("DELETE FROM Car").executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Test
    void whenSaveMethodThanOk() {
        var car = new Car();
        car.setHistoryOwners(historyOwners);
        assertThat(carRepository.save(car)).isEqualTo(Optional.of(car));
    }

    @Test
    void whenFindByIdThanOk() {
        var car = new Car();
        car.setHistoryOwners(historyOwners);
        carRepository.save(car);
        assertThat(carRepository.findById(car.getId())).isEqualTo(Optional.of(car));
    }

    @Test
    void whenFindAllThanOk() {
        var car = new Car();
        car.setHistoryOwners(historyOwners);
        carRepository.save(car);
        assertThat(carRepository.findAll()).contains(car);
    }

    @Test
    void whenDeleteByIdThanOk() {
        var car = new Car();
        car.setHistoryOwners(historyOwners);
        carRepository.save(car);
        carRepository.deleteById(car.getId());
        assertThat(carRepository.findAll()).doesNotContain(car);
    }
}