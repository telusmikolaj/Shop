package pl.waw.great.shop.repository;

import org.springframework.stereotype.Repository;
import pl.waw.great.shop.model.Product;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
public class ProductRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Product create(Product product) {
        this.entityManager.persist(product);
        return product;
    }
}
