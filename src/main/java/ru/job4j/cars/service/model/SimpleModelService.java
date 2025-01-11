package ru.job4j.cars.service.model;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.CarModel;
import ru.job4j.cars.repository.model.HQLCarModelRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SimpleModelService implements ModelService {

    private HQLCarModelRepository hqlCarModelRepository;

    @Override
    public Optional<CarModel> save(CarModel carModel) {
        return hqlCarModelRepository.save(carModel);
    }

    @Override
    public List<CarModel> findAll() {
        return hqlCarModelRepository.findAll();
    }

    @Override
    public Optional<CarModel> findById(Long id) {
        return hqlCarModelRepository.findById(id);
    }

    @Override
    public boolean deleteById(Long id) {
        return hqlCarModelRepository.deleteById(id);
    }
}