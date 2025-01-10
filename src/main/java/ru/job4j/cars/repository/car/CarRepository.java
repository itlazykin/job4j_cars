package ru.job4j.cars.repository.car;

import ru.job4j.cars.model.Car;

import java.util.List;
import java.util.Optional;

public interface CarRepository {

    List<Car> findAll();

    Optional<Car> findById(Long id);

    Optional<Car> save(Car car);

    boolean deleteById(Long id);
}
