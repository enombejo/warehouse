package org.example.warehouse.repository;

import org.example.warehouse.models.Goods;
import org.springframework.data.repository.CrudRepository;



import java.util.List;
import java.util.Optional;


public interface GoodsRepository extends CrudRepository<Goods, Long> { //CrudRepository<Goods, Long>
    /**
     *
     * @param name
     * Поиск по имени товара
     * @return
     */
    List<Goods> findByName(String name);
    //List<Goods> findAll();
    //Optional<Goods> findById(Long id);
}
