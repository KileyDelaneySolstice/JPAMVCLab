package com.kileydelaney.repository;

import com.kileydelaney.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {



}
