package ru.job4j.cars.repository.engine;

import ru.job4j.cars.model.Engine;

import java.util.List;
import java.util.Optional;

public interface EngineRepository {

    List<Engine> findAll();

    Optional<Engine> findById(Long id);

    Optional<Engine> save(Engine engine);

    boolean deleteById(Long id);
}
