package ru.job4j.cars.repository.type;

import ru.job4j.cars.model.Type;

import java.util.List;
import java.util.Optional;

public interface CarTypeRepository {

    Type save(Type type);

    Optional<Type> findById(int id);

    List<Type> findAll();
}
