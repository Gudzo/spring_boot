package com.project.showCase.services;

import com.project.showCase.models.Product;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

@Service
public class ProductService {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("spring-boot-persistence");
    EntityManager em = emf.createEntityManager();

    public List<Product> listAll() {
        return em
                .createQuery("Select p from Product p order by id asc", Product.class)
                .getResultList();
    }

    public void save(Product product) {
        em.getTransaction().begin();
        Product p = new Product();
        p.setName(product.getName());
        p.setPrice(product.getPrice());
        p.setDescription(product.getDescription());
        p.setSeller(product.getSeller());

        System.out.println("COMIITING");
        em.persist(p);
        em.getTransaction().commit();
    }

    public Product get(Integer id) {
        System.out.println(em.find(Product.class, id));
        Product product = em.find(Product.class, id);
        return product;
    }

    public void delete(Integer id) {
        Product p = em.find(Product.class, id);
        em.getTransaction().begin();
        em.remove(p);
        em.getTransaction().commit();
    }
}
