package ru.job4j.cars.repository.owner;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Owner;
import ru.job4j.cars.repository.CrudRepository;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HQLOwnerRepository implements OwnerRepository {

    private CrudRepository crudRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(HQLOwnerRepository.class);

    @Override
    public Collection<Owner> findAll() {
        return crudRepository.query("From Owner o ORDER BY o.id",
                Owner.class);
    }

    @Override
    public Optional<Owner> findById(int id) {
        return crudRepository.optional("FROM Owner WHERE id = :fId",
                Owner.class,
                Map.of("fId", id));
    }

    @Override
    public Optional<Owner> save(Owner owner) {
        Optional<Owner> rsl = Optional.empty();
        try {
            crudRepository.run(session -> session.persist(owner));
            rsl = Optional.of(owner);
        } catch (Exception e) {
            LOGGER.error("Error occurred while saving Owner: {}", owner, e);
        }
        return rsl;
    }

    @Override
    public boolean deleteById(int id) {
        return crudRepository.runBoolean("DELETE Owner WHERE id = :fId",
                Map.of("fId", id));
    }
}
