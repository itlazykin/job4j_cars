package ru.job4j.cars.repository.category;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Category;
import ru.job4j.cars.repository.CrudRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HQLCategoryRepository implements CategoryRepository {

    private CrudRepository crudRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(HQLCategoryRepository.class);

    @Override
    public Optional<Category> save(Category category) {
        Optional<Category> rsl = Optional.empty();
        try {
            crudRepository.run(session -> session.persist(category));
            rsl = Optional.of(category);
        } catch (Exception e) {
            LOGGER.error("Error occurred while saving Category: {}", category, e);
        }
        return rsl;
    }

    @Override
    public List<Category> findAll() {
        return crudRepository.query("FROM Category c ORDER BY c.id",
                Category.class);
    }

    @Override
    public Optional<Category> findById(Long id) {
        return crudRepository.optional("FROM Category WHERE id = :fId",
                Category.class,
                Map.of("fId", id));
    }

    @Override
    public boolean deleteById(Long id) {
        return crudRepository.runBoolean("DELETE FROM Category WHERE id = :fId",
                Map.of("fId", id));
    }
}
