package com.example.crud.services;

import com.example.crud.models.entities.Product;
import com.example.crud.models.entities.Supplier;
import com.example.crud.models.repos.ProductRepos;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductService {
  private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

  @Autowired
  private ProductRepos productRepos;

  public Product save(Product product){

    return productRepos.save(product);

  }

  public Product findOne(Long id){
    Optional<Product> product = productRepos.findById(id);
    if(!product.isPresent() ){
      LOGGER.error("Product Not Found");
      return null;
    }
      return product.get();
  }

  public Iterable<Product> findAll(){

    return productRepos.findAll();

  }

  public void removeOne(Long id){
      productRepos.deleteById(id);
  }

  public List<Product> findByName(String name){
    return productRepos.findByNameContains(name);
  }

  public void addSupplier(Supplier supplier, Long productId){
    Product product = findOne(productId);
    if (product == null){
      throw new RuntimeException("Product id "+productId+ " Not found");
    }
    product.getSuppliers().add(supplier);
    save(product);
  }
}
