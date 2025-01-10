package ru.job4j.cars.repository.brand;

import ru.job4j.cars.model.Brand;

import java.util.List;
import java.util.Optional;

public interface BrandRepository {

    Optional<Brand> save(Brand brand);

    List<Brand> findAll();

    Optional<Brand> findById(Long id);

    boolean deleteById(Long id);
}
