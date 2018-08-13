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

    // get all orders from an account, ordered by order date
    @Query(value = "SELECT orders FROM accounts WHERE accountId = :accountId", nativeQuery = true)
    List<Order> findAllOrders(@Param("accountId") Long accountId);

    // get details for all orders from an account
    @Query(value = "SELECT orders FROM accounts WHERE accountId = :accountId SORT BY ", nativeQuery = true)
    List<Order> findAllOrders(@Param("accountId") Long accountId);

    // list accounts with given last name
    List<Account> findByLastName(String lastName);

}
