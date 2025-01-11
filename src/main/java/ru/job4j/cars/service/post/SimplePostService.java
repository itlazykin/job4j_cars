package ru.job4j.cars.service.post;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.Post;
import ru.job4j.cars.repository.post.HQLPostRepository;

import java.util.Collection;
import java.util.List;

@Service
@AllArgsConstructor
public class SimplePostService implements PostService {

    private HQLPostRepository hqlPostRepository;

    @Override
    public Post save(Post post) {
        return hqlPostRepository.save(post);
    }

    @Override
    public List<Post> findAll() {
        return hqlPostRepository.findAll();
    }

    @Override
    public Collection<Post> findPostsByToday() {
        return hqlPostRepository.findPostsByToday();
    }

    @Override
    public Collection<Post> findAllWithPhotos() {
        return hqlPostRepository.findAllWithPhotos();
    }

    @Override
    public Collection<Post> findPostsByBrand(String brand) {
        return hqlPostRepository.findPostsByBrand(brand);
    }
}