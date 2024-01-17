package com.example.crud.services;

import com.example.crud.models.entities.Category;
import com.example.crud.models.repos.CategoryRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class CategoryService {

  @Autowired
  private CategoryRepo categoryRepo;

  public Category save(Category category){
    return categoryRepo.save(category);
  }

  public Category findOne(Long id){
    Optional<Category> category= categoryRepo.findById(id);
    if (!category.isPresent()){
      return null;
    }
    return category.get();
  }

  public Iterable<Category> findAll(){
    return categoryRepo.findAll();
  }

  public void deleteById(Long id){
    categoryRepo.deleteById(id);
  }
}
