package org.example.warehouse.repository;

import org.example.warehouse.models.Goods;
import org.example.warehouse.models.WriteOff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WriteOffRepository extends JpaRepository<WriteOff, Long> {
    List<WriteOff> findByGoods(Goods goods);
}
