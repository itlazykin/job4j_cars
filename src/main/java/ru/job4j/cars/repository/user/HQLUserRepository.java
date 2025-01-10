package ru.job4j.cars.repository.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.User;
import ru.job4j.cars.repository.CrudRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@Repository
public class HQLUserRepository {

    private final CrudRepository crudRepository;

    /**
     * Сохранить в базе.
     *
     * @param user пользователь.
     * @return пользователь с id.
     */
    public User create(User user) {
        crudRepository.run(session -> session.persist(user));
        return user;
    }

    /**
     * Обновить в базе пользователя.
     *
     * @param user пользователь.
     */
    public void update(User user) {
        crudRepository.run(session -> session.merge(user));
    }

    /**
     * Удалить пользователя по id.
     *
     * @param id ID
     */
    public boolean deleteById(Long id) {
        return crudRepository.runBoolean("DELETE FROM User WHERE id = :fId",
                Map.of("fId", id));
    }

    /**
     * Список пользователь отсортированных по id.
     *
     * @return список пользователей.
     */
    public List<User> findAllOrderById() {
        return crudRepository.query("FROM User ORDER BY id ASC", User.class);
    }

    /**
     * Найти пользователя по ID
     *
     * @return пользователь.
     */
    public Optional<User> findById(Long userId) {
        return crudRepository.optional(
                "FROM User WHERE id = :fId", User.class,
                Map.of("fId", userId)
        );
    }

    /**
     * Список пользователей по login LIKE %key%
     *
     * @param key key
     * @return список пользователей.
     */
    public List<User> findByLikeLogin(String key) {
        return crudRepository.query(
                "FROM User WHERE login LIKE :fKey", User.class,
                Map.of("fKey", "%" + key + "%")
        );
    }

    /**
     * Найти пользователя по login.
     *
     * @param login login.
     * @return Optional or user.
     */
    public Optional<User> findByLogin(String login) {
        return crudRepository.optional(
                "FROM User WHERE login = :fLogin", User.class,
                Map.of("fLogin", login)
        );
    }
}
