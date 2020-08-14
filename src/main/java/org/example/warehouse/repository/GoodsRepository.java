package org.example.warehouse.repository;

import org.example.warehouse.models.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;



import java.util.List;
import java.util.Optional;


public interface GoodsRepository extends JpaRepository<Goods, Long> { //CrudRepository<Goods, Long>
    /**
     *
     * @param name
     * Поиск по имени товара
     *
     */
    List<Goods> findByName(String name);
    //List<Goods> findAll();
    //Optional<Goods> findById(Long id);
}
