package ru.job4j.cars.service.car;

import ru.job4j.cars.model.Car;

import java.util.List;
import java.util.Optional;

public interface CarService {

    List<Car> findAll();

    Optional<Car> findById(Long id);

    Optional<Car> save(Car car);

    boolean deleteById(Long id);
}