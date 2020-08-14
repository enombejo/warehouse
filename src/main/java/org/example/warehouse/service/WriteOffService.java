package org.example.warehouse.service;


import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Objects;


@Service
/**
 * Подача заявок на списание товара
 */
public class WriteOffService {
    public WriteOffService() {
    }

    /**
     * Номер товара в базе данных склада
     */
    private long idProduct;

    /**
     * Количество
     */
    private int amount;

    /**
     * ФИО заявителя
     */
    private String fullName;


    /**
     * Причина списания
     */
    private String cause;


    /**
     * Дата заявки
     */
    private Date date;


    public WriteOffService(long idProduct, int amount, String fullName) {
        this.idProduct = idProduct;
        this.amount = amount;
        this.fullName = fullName;
    }


    public String getCause() {
        return cause;
    }

    public Date getDate() {
        return date;
    }

    public long getIdProduct() {
        return idProduct;
    }

    public int getAmount() {
        return amount;
    }

    public String getFullName() {
        return fullName;
    }



    public void setCause(String cause) {
        this.cause = cause;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setIdProduct(long idProduct) {
        this.idProduct = idProduct;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WriteOffService that = (WriteOffService) o;
        return idProduct == that.idProduct &&
                amount == that.amount &&
                Objects.equals(fullName, that.fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProduct, amount, fullName);
    }

    @Override
    public String toString() {
        return "\nWriteOffService{" +
                "idProduct=" + idProduct +
                ", amount=" + amount +
                ", fullName='" + fullName + '\'' +
                '}';
    }
}
