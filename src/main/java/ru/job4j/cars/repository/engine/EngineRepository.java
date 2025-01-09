package ru.job4j.cars.repository.engine;

import ru.job4j.cars.model.Engine;

import java.util.Collection;
import java.util.Optional;

public interface EngineRepository {
    Collection<Engine> findAll();

    Optional<Engine> findById(int id);

    Optional<Engine> save(Engine engine);

    boolean deleteById(int id);
}
