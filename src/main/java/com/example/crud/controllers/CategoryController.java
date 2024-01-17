package com.example.crud.controllers;

import com.example.crud.dtos.CategoryData;
import com.example.crud.dtos.ResponseData;
import com.example.crud.models.entities.Category;
import com.example.crud.services.CategoryService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<ResponseData<Category>> create(@Valid @RequestBody CategoryData categoryData, Errors errors){
      ResponseData<Category> responseData = new ResponseData<>();
      if (errors.hasErrors()){
        for (ObjectError error: errors.getAllErrors()){
          responseData.getMessage().add(error.getDefaultMessage());
        }
        responseData.setStatus(false);
        responseData.setPayload(null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
      }
      Category category= modelMapper.map(categoryData, Category.class);

      responseData.setStatus(true);
      responseData.setPayload(categoryService.save(category));

      return ResponseEntity.ok(responseData);
    }

    @GetMapping("/{id}")
    public Category findOne(@PathVariable("id") Long id){
      return categoryService.findOne(id);
    }

    @GetMapping
    public Iterable<Category> findAll(){
      return categoryService.findAll();
    }

    @PutMapping
  public ResponseEntity<ResponseData<Category>> update(@Valid @RequestBody CategoryData categoryData, Errors errors){
      ResponseData<Category> responseData =  new ResponseData<>();
      if(errors.hasErrors()){
        for (ObjectError error: errors.getAllErrors()){
          responseData.getMessage().add(error.getDefaultMessage());
        }
        responseData.setStatus(false);
        responseData.setPayload(null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
      }
      Category category= modelMapper.map(categoryData, Category.class);

      responseData.setStatus(true);
      responseData.setPayload(categoryService.save(category));

      return ResponseEntity.ok(responseData);

    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id){
         categoryService.deleteById(id);
         return "Berhasil di hapus";
    }

}
