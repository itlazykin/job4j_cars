package ru.job4j.cars.repository.model;

import ru.job4j.cars.model.CarModel;

import java.util.List;
import java.util.Optional;

public interface CarModelRepository {

    Optional<CarModel> save(CarModel carModel);

    List<CarModel> findAll();

    Optional<CarModel> findById(Long id);

    boolean deleteById(Long id);
}
