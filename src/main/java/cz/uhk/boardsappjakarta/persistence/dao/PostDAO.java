package cz.uhk.boardsappjakarta.persistence.dao;

import cz.uhk.boardsappjakarta.persistence.entity.Post;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.TypedQuery;

import java.util.List;

@ApplicationScoped
public class PostDAO extends AbstractJpaDAO<Post,Long> {
    public PostDAO() {
        setClazz(Post.class);
    }

    public Long createAndReturnId(Post post) {
        entityManager.persist(post);
        entityManager.flush();
        return post.getId();
    }

    public List<Post> findVisiblePosts() {
        return getVisiblePostsSelectQuery()
                .getResultList();
    }

    public List<Post> findVisiblePosts(int pageNumber, int pageSize) {
        return getVisiblePostsSelectQuery()
                .setFirstResult((pageNumber-1)*pageSize)
                .setMaxResults(pageSize)
                .getResultList();
    }

    public boolean existsById(Long id) {
        Long countResult = entityManager.createQuery("select count(id) from Posts p where id=:id and p.removed=false", Long.class)
                .setParameter("id", id)
                .getSingleResult();
        return !countResult.equals(0L);
    }

    @Override
    public Post findOne(Long id) {
        return entityManager.createQuery("select p from Posts p join fetch p.author where p.id=:idParam", Post.class)
                .setParameter("idParam", id)
                .getSingleResult();
    }

    private TypedQuery<Post> getVisiblePostsSelectQuery() {
        return entityManager
                .createQuery("select p from Posts p join fetch p.author where p.removed=false order by p.createdAt desc", Post.class);
    }
}