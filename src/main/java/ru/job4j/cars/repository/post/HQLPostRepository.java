package ru.job4j.cars.repository.post;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Post;
import ru.job4j.cars.repository.CrudRepository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Repository
@AllArgsConstructor
public class HQLPostRepository implements PostRepository {

    private CrudRepository crudRepository;

    @Override
    public Post save(Post post) {
        crudRepository.run(session -> session.save(post));
        return post;
    }

    @Override
    public Collection<Post> findPostsByToday() {
        LocalDateTime today = LocalDateTime.now().minusDays(1).truncatedTo(ChronoUnit.DAYS);
        return crudRepository.query(
                "FROM Post p WHERE p.created > :today",
                Post.class,
                Map.of("today", today)
        );
    }

    @Override
    public Collection<Post> findAllWithPhotos() {
        return crudRepository.query("FROM Post p WHERE SIZE(p.fileList) > 0 ORDER BY p.id",
                Post.class);
    }

    @Override
    public Collection<Post> findPostsByBrand(String brand) {
        return crudRepository.query("FROM Post p WHERE p.type.name = :fType",
                Post.class,
                Map.of("fType", brand));
    }

    @Override
    public List<Post> findAll() {
        return crudRepository.query("FROM Post p ORDER BY p.created ASC",
                Post.class);
    }
}
