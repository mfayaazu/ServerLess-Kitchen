package com.serverlesskitchen.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(schema = "serverless_kitchen", name = "ingredents")
public class Ingredients {

  private String name;
  private int quantity;

  public Ingredients(String sugar, int i) {
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }
}
