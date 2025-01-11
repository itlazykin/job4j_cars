package ru.job4j.cars.service.model;

import ru.job4j.cars.model.CarModel;

import java.util.List;
import java.util.Optional;

public interface ModelService {

    Optional<CarModel> save(CarModel carModel);

    List<CarModel> findAll();

    Optional<CarModel> findById(Long id);

    boolean deleteById(Long id);
}