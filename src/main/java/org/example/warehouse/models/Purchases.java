package org.example.warehouse.models;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

@Entity
/**
 * Сущность покупок
 */
public class Purchases implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /**
     * Дата покупки
     */
    private Date date;
    private String name;
    private Double price;
    private Long quantity;

    @OneToMany(mappedBy = "purchases", cascade = CascadeType.ALL)
    private Set<PurchaseRequest> purchaseRequestsSet;

    public Purchases() {
    }


    public Set<PurchaseRequest> getPurchaseRequestsSet() {
        return purchaseRequestsSet;
    }

    public void setPurchaseRequestsSet(Set<PurchaseRequest> purchaseRequestsSet) {
        this.purchaseRequestsSet = purchaseRequestsSet;
    }

    public Long getId() {
        return id;
    }



    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
