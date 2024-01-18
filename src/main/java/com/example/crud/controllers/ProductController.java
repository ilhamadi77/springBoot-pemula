package com.example.crud.controllers;

import com.example.crud.dtos.ResponseData;
import com.example.crud.dtos.SearchData;
import com.example.crud.models.entities.Product;
import com.example.crud.models.entities.Supplier;
import com.example.crud.services.ProductService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
  private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

  @Autowired
  private ProductService productService;

  @PostMapping
  public ResponseEntity<ResponseData<Product>> create(@Valid @RequestBody Product product, Errors errors){
    ResponseData<Product> responseData = new ResponseData<>();

    if(errors.hasErrors()){
        for (ObjectError error: errors.getAllErrors()){
          responseData.getMessage().add(error.getDefaultMessage());
        }
        LOGGER.error("Error 404");
        responseData.setStatus(false);
        responseData.setPayload(null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
    }
    responseData.setStatus(true);
    responseData.setPayload(productService.save(product));
    return ResponseEntity.ok(responseData);
  }

  @GetMapping
  public Iterable<Product> getAllData(){
    return productService.findAll();
  }

  @GetMapping("{id}")
  public Product getById(@PathVariable("id") Long id){
    return productService.findOne(id);
  }
  @PutMapping
  public ResponseEntity<ResponseData<Product>> updateById(@Valid @RequestBody Product product, Errors errors){
    ResponseData<Product> responseData = new ResponseData<>();

    if(errors.hasErrors()){
      for (ObjectError error: errors.getAllErrors()){
        responseData.getMessage().add(error.getDefaultMessage());
      }
      responseData.setStatus(false);
      responseData.setPayload(null);
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
    }
    responseData.setStatus(true);
    responseData.setPayload(productService.save(product));
    return ResponseEntity.ok(responseData);

  }

  @DeleteMapping("{id}")
  public void deleteById(@PathVariable("id") long id){
      productService.removeOne(id);
  }

  @PostMapping("/{id}")
  public void addSuplier(@RequestBody Supplier supplier, @PathVariable("id") Long productId){
    productService.addSupplier(supplier,productId);
  }

  @PostMapping("/search/name")
  Product findByName(@RequestBody SearchData searchData){
    return productService.findByName(searchData.getSearchKey());

  }

  @PostMapping("/search/nameLike")
  List<Product> findByNameLike(@RequestBody SearchData searchData ){
    return productService.findByNameLike(searchData.getSearchKey());
  }

  @GetMapping("/search/name/{categoryId}")
  List<Product> findProducByCategory(@PathVariable("categoryId") Long categoryId){
    return productService.findProductByCategory(categoryId);
  }

  @GetMapping("/search/name/{supplierId}")
  List<Product> findBySupplier(@PathVariable("supplierId") Long supplierId){
    return productService.findBySuppliers(supplierId);
  }



}
