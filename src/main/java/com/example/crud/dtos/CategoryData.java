package com.example.crud.dtos;

import jakarta.validation.constraints.NotEmpty;

public class CategoryData {

  @NotEmpty(message = "Name Is Required")
  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
