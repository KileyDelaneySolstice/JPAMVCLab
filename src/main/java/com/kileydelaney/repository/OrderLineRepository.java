package com.kileydelaney.repository;


import com.kileydelaney.model.OrderLine;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderLineRepository extends CrudRepository<OrderLine, Long> {



}
