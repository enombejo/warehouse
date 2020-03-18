package org.example.warehouse.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import org.example.warehouse.models.Goods;
import org.example.warehouse.repository.GoodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class MainController {
    /**
     * Экземпляр репозитория товара
     */
    private GoodsRepository repoGoods;
    @Autowired
    public MainController(GoodsRepository repoGoods) {
        this.repoGoods = repoGoods;
    }
    public MainController() {
    }


    @PostMapping("/goods")
    //@ApiOperation()
    /**
     * Создание нового продукта
     */
    public ResponseEntity<?> create(@RequestBody Goods goods) {
        repoGoods.save(goods);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping("/goods")
    /**
     * Запрос всех продуктов
     */
    public ResponseEntity<?> readAll() {
        Iterable<Goods> goods = repoGoods.findAll();
        return  goods != null
                ? new ResponseEntity<>(goods, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/goods/{id}")
    /**
     * Запрос продукта по id
     */
    public ResponseEntity<?> getById(@PathVariable(name = "id") Long id) {
        Optional<Goods> goods = repoGoods.findById(id);
        return goods.isPresent()
                ? new ResponseEntity<>(goods.get(), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/goods/name/{name}")
    /**
     * поиск по имени продукта
     * @param name метод принимает наименование продукта
     */
    public ResponseEntity<?> getByName(@PathVariable(name = "name") String name) {
        List<Goods> goods = repoGoods.findByName(name);
        //return repoGoods.findByName(name).orElseThrow(() -> new NotFoundException(name));

        return goods != null
                ? new ResponseEntity<>(goods, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PutMapping(value = "/goods/{id}")
    /**
     * Изменение продукта
     */
    public ResponseEntity<?> update(@PathVariable(name = "id") long id, @RequestBody Goods goods) {
        repoGoods.save(goods);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/goods/{id}")
    /**
     * Удаление из базы данных
     */
    public ResponseEntity<?> delete(@PathVariable(name = "id") long id) {
        Optional<Goods> goods = repoGoods.findById(id);
        repoGoods.delete(goods.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
