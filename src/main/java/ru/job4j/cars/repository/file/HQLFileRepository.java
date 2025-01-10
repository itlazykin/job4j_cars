package ru.job4j.cars.repository.file;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.File;
import ru.job4j.cars.repository.CrudRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HQLFileRepository implements FileRepository {

    private CrudRepository crudRepository;

    @Override
    public File save(File file) {
        crudRepository.run(session -> session.save(file));
        return file;
    }

    @Override
    public Optional<File> findById(int id) {
        return crudRepository.optional(
                "FROM File WHERE id = :fId",
                File.class,
                Map.of("fId", id)
        );
    }

    @Override
    public List<File> findAll() {
        return crudRepository.query("From File f ORDER BY f.id",
                File.class);
    }

    @Override
    public boolean deleteById(int id) {
        return crudRepository.runBoolean("DELETE File WHERE id = :fId",
                Map.of("fId", id)
        );
    }
}
