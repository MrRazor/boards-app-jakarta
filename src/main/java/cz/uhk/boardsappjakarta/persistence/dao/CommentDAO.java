package cz.uhk.boardsappjakarta.persistence.dao;

import cz.uhk.boardsappjakarta.persistence.entity.Comment;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.TypedQuery;

import java.util.List;

@ApplicationScoped
public class CommentDAO extends AbstractJpaDAO<Comment,Long> {
    public CommentDAO() {
        setClazz(Comment.class);
    }

    public Long createAndReturnId(Comment comment) {
        entityManager.persist(comment);
        entityManager.flush();
        return comment.getId();
    }

    public List<Comment> findVisibleCommentsByPostId(Long postId) {
        return getVisibleCommentsByPostIdSelectQuery(postId)
                .getResultList();
    }

    public List<Comment> findVisibleCommentsByPostId(Long postId, int pageNumber, int pageSize) {
        return getVisibleCommentsByPostIdSelectQuery(postId)
                .setFirstResult((pageNumber-1)*pageSize)
                .setMaxResults(pageSize)
                .getResultList();
    }

    @Override
    public Comment findOne(Long id) {
        return entityManager.createQuery("select c from Comments c join fetch c.author join fetch c.post where c.id=:idParam", Comment.class)
                .setParameter("idParam", id)
                .getSingleResult();
    }

    private TypedQuery<Comment> getVisibleCommentsByPostIdSelectQuery(Long postId) {
        return entityManager
                .createQuery("select c from Comments c join fetch c.author where c.post.id=:postIdParam and c.removed=false order by c.createdAt asc", Comment.class)
                .setParameter("postIdParam", postId);
    }
}