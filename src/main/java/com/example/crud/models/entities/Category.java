package com.example.crud.models.entities;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "tbl_categories")
public class Category implements Serializable {

  private static final long serialVersionUID=1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 100, nullable = false, unique = true)
  private String name;

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
}
