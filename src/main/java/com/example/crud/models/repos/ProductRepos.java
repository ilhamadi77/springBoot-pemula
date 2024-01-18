package com.example.crud.models.repos;

import com.example.crud.models.entities.Product;
import com.example.crud.models.entities.Supplier;
import jakarta.websocket.server.PathParam;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepos extends CrudRepository<Product, Long> {
     @Query("SELECT p FROM Product p WHERE p.name = :name")
     Product findByName(@PathParam("name") String name);

     @Query("SELECT p FROM Product p WHERE p.name LIKE :name")
     List<Product> findProductByNameLike(@PathParam("name") String name);

     @Query("SELECT p FROM Product p WHERE p.category.id = :categoryId")
     List<Product> findProductByCategory(@PathParam("categoryId") Long categoryId);

     @Query("SELECT p FROM Product p WHERE :supplier MEMBER OF p.suppliers")
     List<Product> findBySupplier (@PathParam("supplier") Supplier supplier);

}
