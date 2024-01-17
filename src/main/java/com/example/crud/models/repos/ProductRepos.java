package com.example.crud.models.repos;

import com.example.crud.models.entities.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepos extends CrudRepository<Product, Long> {
    List<Product> findByNameContains(String name);
}
