package ru.job4j.cars.repository.owner;

import ru.job4j.cars.model.Owner;

import java.util.Collection;
import java.util.Optional;

public interface OwnerRepository {

    Collection<Owner> findAll();

    Optional<Owner> findById(int id);

    Optional<Owner> save(Owner owner);

    boolean deleteById(int id);
}
