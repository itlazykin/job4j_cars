package ru.job4j.cars.service.engine;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.Engine;
import ru.job4j.cars.repository.engine.HQLEngineRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SimpleEngineService implements EngineService {

    private HQLEngineRepository hqlEngineRepository;

    @Override
    public List<Engine> findAll() {
        return hqlEngineRepository.findAll();
    }

    @Override
    public Optional<Engine> findById(Long id) {
        return hqlEngineRepository.findById(id);
    }

    @Override
    public Optional<Engine> save(Engine engine) {
        return hqlEngineRepository.save(engine);
    }

    @Override
    public boolean deleteById(Long id) {
        return hqlEngineRepository.deleteById(id);
    }
}