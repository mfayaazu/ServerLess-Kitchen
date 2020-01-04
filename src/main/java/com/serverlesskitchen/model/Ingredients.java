package com.serverlesskitchen.model;


import java.util.Objects;

public class Ingredients {

  private String name;
  private int quantity;

  public Ingredients(String name, int quantity) {
    this.name = name;
    this.quantity = quantity;
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

  @Override
  public String toString() {
    return "Ingredients{" +
            "name='" + name + '\'' +
            ", quantity=" + quantity +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Ingredients)) return false;
    Ingredients that = (Ingredients) o;
    return getQuantity() == that.getQuantity() &&
            getName().equals(that.getName());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getName(), getQuantity());
  }
}
