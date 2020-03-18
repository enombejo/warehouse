package org.example.warehouse.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "goods")
/**
 * Сущность содержащая тповары
 */
public class Goods implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;


    /**
     * Группа товара
     */
    @Column(name = "grup_id")
    private Long grupId;
    @Column(name = "price")
    private Double price;
    @Column(name = "quantity")
    private Integer quantity;

    public Goods() {
    }

    public Goods(String name, Long grupId, Double price, Integer quantity) {
        this.name = name;
        this.grupId = grupId;
        this.price = price;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getGrupId() {
        return grupId;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setGrupId(Long grupId) {
        this.grupId = grupId;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
