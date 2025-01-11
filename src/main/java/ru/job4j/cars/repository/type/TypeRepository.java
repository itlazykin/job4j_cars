package ru.job4j.cars.repository.type;

import ru.job4j.cars.model.Type;

import java.util.List;
import java.util.Optional;

public interface TypeRepository {

    Type save(Type type);

    Optional<Type> findById(Long id);

    List<Type> findAll();
}
