package com.example.crud.models.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "tbl_product")
public class Product implements Serializable {

  private static final long serialVersionUID=1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotEmpty(message = "name is required")
  @Column(name = "product_name", length = 100)
  private String name;

  @NotEmpty(message = "Description is required")
  @Column(name="pruduct_description", length = 500)
  private String description;

  private Double price;

  @ManyToOne
  private Category category;

  @ManyToMany
  @JoinTable(name = "tbl_product_supplier",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "supplier_id"))
  @JsonManagedReference
  private Set<Supplier> suppliers;

  public Product(){}

  public Product(Long id, String name, String description, Double price){
    this.id= id;
    this.name= name;
    this.description= description;
    this.price= price;
  }

  /**
   * jika pake project lombo
   * */

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  public Set<Supplier> getSuppliers() {
    return suppliers;
  }

  public void setSuppliers(Set<Supplier> suppliers) {
    this.suppliers = suppliers;
  }
}
