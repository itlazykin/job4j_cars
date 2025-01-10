package ru.job4j.cars.repository.type;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Type;
import ru.job4j.cars.repository.CrudRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HQLCarTypeRepository implements CarTypeRepository {

    private CrudRepository crudRepository;

    @Override
    public Type save(Type type) {
        crudRepository.run(session -> session.save(type));
        return type;
    }

    @Override
    public Optional<Type> findById(int id) {
        return crudRepository.optional(
                "FROM Type WHERE id = :fId",
                Type.class,
                Map.of("fId", id)
        );
    }

    @Override
    public List<Type> findAll() {
        return crudRepository.query("From Type ORDER BY id",
                Type.class);
    }
}
