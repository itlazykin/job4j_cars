package ru.job4j.cars.repository.car;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Car;
import ru.job4j.cars.repository.CrudRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HQLCarRepository implements CarRepository {

    CrudRepository crudRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(HQLCarRepository.class);

    @Override
    public List<Car> findAll() {
        return crudRepository.query("FROM Car c ORDER BY c.id",
                Car.class);
    }

    @Override
    public Optional<Car> findById(Long id) {
        return crudRepository.optional("FROM Car WHERE id = :fId",
                Car.class,
                Map.of("fId", id));
    }

    @Override
    public Optional<Car> save(Car car) {
        Optional<Car> rsl = Optional.empty();
        try {
            crudRepository.run(session -> session.persist(car));
            rsl = Optional.of(car);
        } catch (Exception e) {
            LOGGER.error("Error occurred while saving Car: {}", car, e);
        }
        return rsl;
    }

    @Override
    public boolean deleteById(Long id) {
        return crudRepository.runBoolean("DELETE Car WHERE id = :fId",
                Map.of("fId", id));
    }
}
