package ru.job4j.cars.repository.model;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.CarModel;
import ru.job4j.cars.repository.CrudRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HQLCarModelRepository implements CarModelRepository {

    private CrudRepository crudRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(HQLCarModelRepository.class);

    @Override
    public Optional<CarModel> save(CarModel carModel) {
        Optional<CarModel> rsl = Optional.empty();
        try {
            crudRepository.run(session -> session.persist(carModel));
            rsl = Optional.of(carModel);
        } catch (Exception e) {
            LOGGER.error("Error occurred while saving CarModel: {}", carModel, e);
        }
        return rsl;
    }

    @Override
    public List<CarModel> findAll() {
        return crudRepository.query("FROM CarModel cm ORDER BY cm.id",
                CarModel.class);
    }

    @Override
    public Optional<CarModel> findById(Long id) {
        return crudRepository.optional("From CarModel WHERE id = :fId",
                CarModel.class,
                Map.of("fId", id));
    }

    @Override
    public boolean deleteById(Long id) {
        return crudRepository.runBoolean("DELETE FROM CarModel WHERE id = :fId",
                Map.of("fId", id));
    }
}
