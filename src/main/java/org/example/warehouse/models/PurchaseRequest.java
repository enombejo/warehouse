package org.example.warehouse.models;

import javax.persistence.*;

@Entity
@Table(name = "purchase_request")
/**
 * Заявка на покупку
 */
public class PurchaseRequest {
    @Id
    @GeneratedValue
    private Long id;
    /**
     * Количество заказываемого товара
     */
    private Long amount;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_product")
    /**
     * Номер товара
     */
    private Goods goods;



    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_purchases")
    /**
     * Номер покупки
     */
    private Purchases purchases;


    /**
     * Общая цена
     */
    private Double price;

    public PurchaseRequest() {
    }

    public Long getId() {
        return id;
    }

    public Long getAmount() {
        return amount;
    }

    public Goods getGoods() {
        return goods;
    }

    public Purchases getPurchases() {
        return purchases;
    }


    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public void setPurchases(Purchases purchases) {
        this.purchases = purchases;
    }
}
