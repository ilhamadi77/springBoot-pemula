package com.example.crud.services;

import com.example.crud.models.entities.Supplier;
import com.example.crud.models.repos.SupplierRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class SupplierService {

  @Autowired
  private SupplierRepo supplierRepo;

    public Supplier findOne(Long id){
      Optional<Supplier> supplier = supplierRepo.findById(id);
      if (!supplier.isPresent()){
        return null;
      }
      return supplier.get();
    }

    public Supplier save(Supplier supplier){
      return supplierRepo.save(supplier);
    }
    public Iterable<Supplier> findAll(){
      return supplierRepo.findAll();
    }

    public void removeOne(Long id){
      supplierRepo.deleteById(id);
    }
}
