package org.example.warehouse.repository;

import org.example.warehouse.models.Waybill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WaybillRepository extends JpaRepository<Waybill, Long> {
}
