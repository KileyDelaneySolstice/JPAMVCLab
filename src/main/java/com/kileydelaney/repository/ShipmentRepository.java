package com.kileydelaney.repository;

import com.kileydelaney.model.Shipment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ShipmentRepository extends CrudRepository<Shipment, Long> {

    // get all shipments for an account, ordered by delivery date
    @Query(value = "SELECT * FROM shipments WHERE account.accountId = :accountId ORDER BY deliveryDate", nativeQuery = true)
    List<Shipment> findAllByAccountIdOrderByDeliveryDate(@Param("accountId") Long accountId);

}
