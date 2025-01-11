package ru.job4j.cars.service.owner;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.Owner;
import ru.job4j.cars.repository.owner.HQLOwnerRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SimpleOwnerService implements OwnerService {

    private HQLOwnerRepository hqlOwnerRepository;

    @Override
    public List<Owner> findAll() {
        return hqlOwnerRepository.findAll();
    }

    @Override
    public Optional<Owner> findById(Long id) {
        return hqlOwnerRepository.findById(id);
    }

    @Override
    public Optional<Owner> save(Owner owner) {
        return hqlOwnerRepository.save(owner);
    }

    @Override
    public boolean deleteById(Long id) {
        return hqlOwnerRepository.deleteById(id);
    }
}