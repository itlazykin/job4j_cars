package ru.job4j.cars.service.category;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.Category;
import ru.job4j.cars.repository.category.HQLCategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SimpleCategoryService implements CategoryService {

    private HQLCategoryRepository hqlCategoryRepository;

    @Override
    public Optional<Category> save(Category category) {
        return hqlCategoryRepository.save(category);
    }

    @Override
    public List<Category> findAll() {
        return hqlCategoryRepository.findAll();
    }

    @Override
    public Optional<Category> findById(Long id) {
        return hqlCategoryRepository.findById(id);
    }

    @Override
    public boolean deleteById(Long id) {
        return hqlCategoryRepository.deleteById(id);
    }
}