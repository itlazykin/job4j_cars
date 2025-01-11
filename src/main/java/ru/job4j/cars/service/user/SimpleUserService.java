package ru.job4j.cars.service.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.User;
import ru.job4j.cars.repository.user.HQLUserRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SimpleUserService implements UserService   {

    private HQLUserRepository hqlUserRepository;

    @Override
    public User create(User user) {
        return hqlUserRepository.create(user);
    }

    @Override
    public void update(User user) {
hqlUserRepository.update(user);
    }

    @Override
    public boolean deleteById(Long id) {
        return hqlUserRepository.deleteById(id);
    }

    @Override
    public List<User> findAllOrderById() {
        return hqlUserRepository.findAllOrderById();
    }

    @Override
    public Optional<User> findById(Long id) {
        return hqlUserRepository.findById(id);
    }

    @Override
    public List<User> findByLikeLogin(String key) {
        return hqlUserRepository.findByLikeLogin(key);
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return hqlUserRepository.findByLogin(login);
    }
}