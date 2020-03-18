package org.example.warehouse.repository;

import org.example.warehouse.models.Purchases;
import org.springframework.data.repository.CrudRepository;

public interface PurchasesRepository extends CrudRepository<Purchases, Long> {

}
