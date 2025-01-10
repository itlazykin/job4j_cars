package ru.job4j.cars.repository.owner;

import ru.job4j.cars.model.Owner;

import java.util.List;
import java.util.Optional;

public interface OwnerRepository {

    List<Owner> findAll();

    Optional<Owner> findById(Long id);

    Optional<Owner> save(Owner owner);

    boolean deleteById(Long id);
}
