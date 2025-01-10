package ru.job4j.cars.repository.post;

import ru.job4j.cars.model.Post;

import java.util.Collection;
import java.util.List;

public interface PostRepository {

    Post save(Post post);

    List<Post> findAll();

    Collection<Post> findPostsByToday();

    Collection<Post> findAllWithPhotos();

    Collection<Post> findPostsByBrand(String brand);
}
