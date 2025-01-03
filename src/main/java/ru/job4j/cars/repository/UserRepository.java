package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.job4j.cars.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class UserRepository {
    private final SessionFactory sf;

    /**
     * Сохранить в базе.
     *
     * @param user пользователь.
     * @return пользователь с id.
     */
    public User create(User user) {
        var session = sf.openSession();
        try {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return user;
    }

    /**
     * Обновить в базе пользователя.
     *
     * @param user пользователь.
     */
    public void update(User user) {
        var session = sf.openSession();
        try {
            session.beginTransaction();
            session.createQuery("UPDATE User SET login = :login AND password = :password WHERE id = :fid")
                    .setParameter("login", user.getLogin())
                    .setParameter("password", user.getPassword())
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    /**
     * Удалить пользователя по id.
     *
     * @param userId ID
     */
    public void delete(int userId) {
        var session = sf.openSession();
        try {
            session.beginTransaction();
            session.createQuery(
                            "DELETE User WHERE id = :fId")
                    .setParameter("fId", userId)
                    .executeUpdate();
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    /**
     * Список пользователь отсортированных по id.
     *
     * @return список пользователей.
     */
    public List<User> findAllOrderById() {
        var session = sf.openSession();
        List<User> result = new ArrayList<>();
        try {
            session.beginTransaction();
            session.createQuery(
                    "FROM User ORDER BY id", User.class).getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return result;
    }

    /**
     * Найти пользователя по ID
     *
     * @return пользователь.
     */
    public Optional<User> findById(int userId) {
        var session = sf.openSession();
        Optional<User> userOptional = Optional.empty();
        try {
            session.beginTransaction();
            Query<User> query = session.createQuery(
                    "FROM User AS u WHERE u.id = :fId", User.class);
            query.setParameter("fId", userId);
            userOptional = Optional.of(query.getSingleResult());
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return userOptional;
    }

    /**
     * Список пользователей по login LIKE %key%
     *
     * @param key key
     * @return список пользователей.
     */
    public List<User> findByLikeLogin(String key) {
        var session = sf.openSession();
        List<User> result = new ArrayList<>();
        try {
            session.beginTransaction();
            Query<User> query = session.createQuery(
                    "FROM User AS u WHERE u.login LIKE :fKey", User.class);
            query.setParameter("fKey", "%" + key + "%");
            result = query.getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return result;
    }

    /**
     * Найти пользователя по login.
     *
     * @param login login.
     * @return Optional or user.
     */
    public Optional<User> findByLogin(String login) {
        var session = sf.openSession();
        Optional<User> result = Optional.empty();
        try {
            session.beginTransaction();
            result = session.createQuery("FROM User AS u WHERE u.login = :fLogin", User.class)
                    .setParameter("fLogin", login)
                    .uniqueResultOptional();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return result;
    }
}
