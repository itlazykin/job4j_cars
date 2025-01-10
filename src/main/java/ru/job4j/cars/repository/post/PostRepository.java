package ru.job4j.cars.repository.post;

import ru.job4j.cars.model.Post;

import java.util.Collection;

public interface PostRepository {

    Post save(Post post);

    Collection<Post> findPostsByToday();

    Collection<Post> findAllWithPhotos();

    Collection<Post> findPostsByCarBrand(String brand);
}
