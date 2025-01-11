package ru.job4j.cars.service.type;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.Type;
import ru.job4j.cars.repository.type.HQLTypeRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SimpleTypeService implements TypeService {

    private HQLTypeRepository hqlTypeRepository;

    @Override
    public Type save(Type type) {
        return hqlTypeRepository.save(type);
    }

    @Override
    public Optional<Type> findById(Long id) {
        return hqlTypeRepository.findById(id);
    }

    @Override
    public List<Type> findAll() {
        return hqlTypeRepository.findAll();
    }
}