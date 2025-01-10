package ru.job4j.cars.repository.brand;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Brand;
import ru.job4j.cars.repository.CrudRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HQLBrandRepository implements BrandRepository {

    private CrudRepository crudRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(HQLBrandRepository.class);

    @Override
    public Optional<Brand> save(Brand brand) {
        Optional<Brand> rsl = Optional.empty();
        try {
            crudRepository.run(session -> session.persist(brand));
            rsl = Optional.of(brand);
        } catch (Exception e) {
            LOGGER.error("Error occurred while saving Brand: {}", brand, e);
        }
        return rsl;
    }

    @Override
    public List<Brand> findAll() {
        return crudRepository.query("FROM Brand b ORDER BY b.id",
                Brand.class);
    }

    @Override
    public Optional<Brand> findById(Long id) {
        return crudRepository.optional("FROM Brand WHERE id = :fId",
                Brand.class,
                Map.of("fId", id));
    }

    @Override
    public boolean deleteById(Long id) {
        return crudRepository.runBoolean("DELETE FROM Brand WHERE id = :fId",
                Map.of("fId", id));
    }
}
