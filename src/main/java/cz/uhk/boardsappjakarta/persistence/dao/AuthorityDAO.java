package cz.uhk.boardsappjakarta.persistence.dao;

import cz.uhk.boardsappjakarta.persistence.entity.Authority;
import cz.uhk.boardsappjakarta.persistence.entity.model.AuthoritiesId;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AuthorityDAO extends AbstractJpaDAO<Authority, AuthoritiesId> {
    public AuthorityDAO() {
        setClazz(Authority.class);
    }
}
