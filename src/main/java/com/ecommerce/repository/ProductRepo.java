package com.ecommerce.repository;

import com.ecommerce.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product,Long> {
    List<Product> findAllByCategoriesId(Long id);
    List<Product> findAllByUserId(Long id);
    @Query("SELECT p FROM Product p JOIN p.categories c WHERE c.id IN :categoryIds ORDER BY p.name ASC")
    List<Product> findAllByCategories(@Param("categoryIds") List<Long> categoryIds);

    // Custom query to filter products by category and sort by price (ascending)
    @Query("SELECT p FROM Product p JOIN p.categories c WHERE c.id IN :categoryIds ORDER BY p.price ASC")
    List<Product> findAllByCategoriesSortedByPrice(@Param("categoryIds") List<Long> categoryIds);

}
