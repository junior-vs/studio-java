package com.redhat.cloudnative.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "INVENTORY")
public class Inventory extends PanacheEntity {

    int quantity;


    @Override
    public String toString() {
        return "Inventory{" +
                "quantity=" + quantity +
                ", id=" + id +
                '}';
    }

    public int getQuantity() {
        return quantity;
    }
}
