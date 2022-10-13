package pl.waw.great.shop.repository;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.waw.great.shop.exception.ProductWithGivenIdNotExistsException;
import pl.waw.great.shop.model.Product;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepositoryImpl{
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Product createProduct(Product product) {
        this.entityManager.persist(product);
        return product;
    }

    @Transactional
    public Optional<Product> getProduct(Long id) {
        Session session = this.entityManager.unwrap(Session.class);
        return session.byId(Product.class).loadOptional(id);
    }

    @Transactional
    public boolean deleteProduct(Long id) {
        Product productToDelete = this.getProduct(id)
                .orElseThrow(() -> new ProductWithGivenIdNotExistsException(id));

        this.entityManager.remove(productToDelete);

        return true;
    }

    @Transactional
    public Product updateProduct(Product product) {
        return this.entityManager.merge(product);
    }

    @Transactional
    public boolean deleteAll() {
        this.entityManager.createQuery("delete from Product").executeUpdate();
        this.entityManager.flush();
        return true;
    }

    @Transactional
    public List<Product> findAllProducts() {
        return this.entityManager.createQuery("SELECT a FROM  Product a", Product.class).getResultList();
    }

    @Transactional
    public Optional<Product> findProductByTitle(String title) {
        TypedQuery<Product> query = this.entityManager.createQuery("SELECT p FROM Product p WHERE p.title=:title", Product.class);
        query.setParameter("title", title);
        return query.getResultStream()
                .findFirst();
    }

    @Transactional
    public Long getProductsQuantity() {
        return this.entityManager.createQuery("select count(1) from Product", Long.class).getSingleResult();
    }

}
