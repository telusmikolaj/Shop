package pl.waw.great.shop.repository;

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
public class ProductRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Product createProduct(Product product) {
        this.entityManager.persist(product);
        return product;
    }
    @Transactional
    public Product getProduct(Long id) {
        Product product = this.entityManager.find(Product.class, id);
        if (product == null) {
            throw new ProductWithGivenIdNotExistsException(id);
        }
        return product;
    }
    @Transactional
    public boolean deleteProduct(Long id) {
        Product productToDelete = this.getProduct(id);
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

    public Long getProductsQuantity() {
        return this.entityManager.createQuery("select count(1) from Product", Long.class).getSingleResult();
    }

}
