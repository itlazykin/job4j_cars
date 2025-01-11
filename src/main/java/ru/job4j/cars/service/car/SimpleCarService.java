package ru.job4j.cars.service.car;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.Car;
import ru.job4j.cars.repository.car.HQLCarRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SimpleCarService implements CarService {

    private HQLCarRepository hqlCarRepository;

    @Override
    public List<Car> findAll() {
        return hqlCarRepository.findAll();
    }

    @Override
    public Optional<Car> findById(Long id) {
        return hqlCarRepository.findById(id);
    }

    @Override
    public Optional<Car> save(Car car) {
        return hqlCarRepository.save(car);
    }

    @Override
    public boolean deleteById(Long id) {
        return hqlCarRepository.deleteById(id);
    }
}