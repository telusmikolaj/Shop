package pl.waw.great.shop.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.waw.great.shop.model.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;

@Repository
public class CommentRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Comment create(Comment comment) {
        comment.setCreated(LocalDateTime.now());
        comment.setUpdated(LocalDateTime.now());
        this.entityManager.persist(comment);
        return comment;
    }

    @Transactional
    public boolean delete(Long id) {
        Comment commentToDelete = this.get(id);
        this.entityManager.remove(commentToDelete);
        return true;
    }

    public Comment get(Long id) {
        return this.entityManager.find(Comment.class, id);
    }

    @Transactional
    public boolean deleteAll() {
        this.entityManager.createQuery("delete from Comment ").executeUpdate();
        this.entityManager.flush();
        return true;
    }
}
