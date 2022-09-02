package com.redhat.cloudnative.catalog.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUCT")
public class Product  implements Serializable{

private static final long serialVersionUID = 1L;
@Id
private String itemId;
private String name;
private String description;
private double price;


public Product(String itemId, String name, String description, double price) {
    this.itemId = itemId;
    this.name = name;
    this.description = description;
    this.price = price;
}


public Product() {
}


public String getItemId() {
    return itemId;
}


public String getName() {
    return name;
}


public String getDescription() {
    return description;
}


public double getPrice() {
    return price;
}


@Override
public String toString() {
    return "Product [description=" + description + ", itemId=" + itemId + ", name=" + name + ", price=" + price + "]";
}






    
}
