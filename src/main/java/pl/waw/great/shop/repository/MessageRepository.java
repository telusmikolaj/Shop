package pl.waw.great.shop.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.waw.great.shop.model.Message;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MessageRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Message create(Message message) {
        message.setCreated();
        message.setUpdated();
        this.entityManager.persist(message);
        return message;
    }

    @Transactional
    public boolean deleteAll() {
        this.entityManager.createQuery("DELETE FROM Message").executeUpdate();
        this.entityManager.flush();
        return true;
    }
}
