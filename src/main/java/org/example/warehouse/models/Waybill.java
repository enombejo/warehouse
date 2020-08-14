package org.example.warehouse.models;

import javax.persistence.*;
import java.util.Date;
//import java.io.Serializable;

@Entity
/**
 * Накладная
 */
public class Waybill {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String product;

    private Long amount;
    @Column(name = "group_product")
    private String groupProduct;

    private String supplier;

    private Double price;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "id_product")
    private Goods goodsId;

    @Column(name = "waybill_date")
    /**
     * Дата составления накладной
     */
    private Date waybillDate;


    public Waybill() {
    }

    public Long getId() {
        return id;
    }


    public Date getWaybillDate() {
        return waybillDate;
    }

    public void setWaybillDate(Date waybillDate) {
        this.waybillDate = waybillDate;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getGroupProduct() {
        return groupProduct;
    }

    public void setGroupProduct(String groupProduct) {
        this.groupProduct = groupProduct;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Goods getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Goods goodsId) {
        this.goodsId = goodsId;
    }
}
