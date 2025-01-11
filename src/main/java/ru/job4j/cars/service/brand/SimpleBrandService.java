package ru.job4j.cars.service.brand;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.Brand;
import ru.job4j.cars.repository.brand.BrandRepository;
import ru.job4j.cars.repository.brand.HQLBrandRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SimpleBrandService implements BrandRepository {

    private HQLBrandRepository hqlBrandRepository;

    @Override
    public Optional<Brand> save(Brand brand) {
        return hqlBrandRepository.save(brand);
    }

    @Override
    public List<Brand> findAll() {
        return hqlBrandRepository.findAll();
    }

    @Override
    public Optional<Brand> findById(Long id) {
        return hqlBrandRepository.findById(id);
    }

    @Override
    public boolean deleteById(Long id) {
        return hqlBrandRepository.deleteById(id);
    }
}