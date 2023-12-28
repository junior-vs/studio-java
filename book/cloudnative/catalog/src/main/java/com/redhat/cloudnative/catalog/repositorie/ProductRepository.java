package com.redhat.cloudnative.catalog.repositorie;

import org.springframework.data.repository.CrudRepository;

import com.redhat.cloudnative.catalog.model.Product;

public interface ProductRepository extends CrudRepository<Product, String>{
    
}
