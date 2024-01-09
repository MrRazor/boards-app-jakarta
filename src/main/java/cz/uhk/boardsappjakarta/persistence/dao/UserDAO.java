package cz.uhk.boardsappjakarta.persistence.dao;

import cz.uhk.boardsappjakarta.persistence.entity.User;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserDAO extends AbstractJpaDAO<User,String>{
    public UserDAO() {
        setClazz(User.class);
    }

    @Override
    public User findOne(String id) {
        return entityManager.createQuery("select u from Users u join fetch u.authorities where u.username=:usernameParam", User.class)
        .setParameter("usernameParam", id)
        .getSingleResult();
    }
}