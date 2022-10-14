package pl.waw.great.shop.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.waw.great.shop.exception.CategoryWithGivenNameNotExistsException;
import pl.waw.great.shop.model.Category;
import pl.waw.great.shop.model.Product;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Repository
public class CategoryRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Category addProductToCategory(Product product, Category category) {
        category.addProduct(product);
        this.entityManager.persist(category);

        return category;
    }

    @Transactional
    public Category findCategoryByName(String name) {
        TypedQuery<Category> query = this.entityManager.createQuery("SELECT c FROM Category c WHERE c.name=:name", Category.class);
        query.setParameter("name", name);
        return query.getResultStream()
                .findFirst()
                .orElseThrow(() -> new CategoryWithGivenNameNotExistsException(name));
    }

}
