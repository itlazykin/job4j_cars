package ru.job4j.cars.repository.engine;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Engine;
import ru.job4j.cars.repository.CrudRepository;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HQLEngineRepository implements EngineRepository {

    private CrudRepository crudRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(HQLEngineRepository.class);

    @Override
    public Collection<Engine> findAll() {
        return crudRepository.query("FROM Engine e ORDER BY e.id",
                Engine.class);
    }

    @Override
    public Optional<Engine> findById(Long id) {
        return crudRepository.optional("FROM Engine WHERE id = :fId",
                Engine.class,
                Map.of("fId", id));

    }

    @Override
    public Optional<Engine> save(Engine engine) {
        Optional<Engine> rsl = Optional.empty();
        try {
            crudRepository.run(session -> session.persist(engine));
            rsl = Optional.of(engine);
        } catch (Exception e) {
            LOGGER.error("Error occurred while saving Engine: {}", engine, e);
        }
        return rsl;
    }

    @Override
    public boolean deleteById(Long id) {
        return crudRepository.runBoolean("DELETE Engine WHERE id = :fId",
                Map.of("fId", id));
    }
}
