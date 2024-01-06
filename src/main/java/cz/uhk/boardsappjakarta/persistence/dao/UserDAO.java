package cz.uhk.boardsappjakarta.persistence.dao;

import cz.uhk.boardsappjakarta.persistence.entity.User;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserDAO extends AbstractJpaDAO<User,String>{
    public UserDAO() {
        setClazz(User.class);
    }

}