package com.kileydelaney.repository;

import com.kileydelaney.model.Account;
import com.kileydelaney.model.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {

    // get order details for an account
    @Query(value = "SELECT * FROM orders WHERE accountId = :accountId", nativeQuery = true)
    List<Order> findByAccountId(@Param("accountId") Long accountId);

    // list accounts with given last name
    List<Account> findByLastName(String lastName);

}
