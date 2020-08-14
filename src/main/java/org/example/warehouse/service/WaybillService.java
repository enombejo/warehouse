package org.example.warehouse.service;


import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

@Service
/**
 * Сервис по работе с накладной
 */
public class WaybillService {
    //private Long id;

    private String product;

    private Long amount;

    private String groupProduct;

    private String supplier;

    private double price;

    private Date waybillDate = new Date();

//    public WaybillService(String product, Long amount, String groupProduct, String supplier, Double price) {
//        this.product = product;
//        this.amount = amount;
//        this.groupProduct = groupProduct;
//        this.supplier = supplier;
//        this.price = price;
//        //this.waybillDate = new Date();
//    }

    public WaybillService() {
        waybillDate = new Date();
    }

    public String getProduct() {
        return product;
    }

    public Long getAmount() {
        return amount;
    }

    public String getGroupProduct() {
        return groupProduct;
    }

    public String getSupplier() {
        return supplier;
    }

    public Double getPrice() {
        return price;
    }

    public Date getWaybillDate() {
        return waybillDate;
    }


    public void setProduct(String product) {
        this.product = product;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public void setGroupProduct(String groupProduct) {
        this.groupProduct = groupProduct;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setWaybillDate(Date waybillDate) {
        this.waybillDate = waybillDate;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WaybillService that = (WaybillService) o;
        return Objects.equals(product, that.product) &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(groupProduct, that.groupProduct) &&
                Objects.equals(supplier, that.supplier) &&
                Objects.equals(price, that.price) &&
                Objects.equals(waybillDate, that.waybillDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, amount, groupProduct, supplier, price, waybillDate);
    }

    @Override
    public String toString() {
        return "\nWaybillServise{" +
                "product='" + product + '\'' +
                ", amount=" + amount +
                ", groupProduct='" + groupProduct + '\'' +
                ", supplier='" + supplier + '\'' +
                ", price=" + price +
                ", waybillDate=" + waybillDate +
                '}';
    }
}
