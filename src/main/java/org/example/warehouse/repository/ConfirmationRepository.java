package org.example.warehouse.repository;

import org.example.warehouse.models.Confirmation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfirmationRepository extends JpaRepository<Confirmation, Long> {
}
