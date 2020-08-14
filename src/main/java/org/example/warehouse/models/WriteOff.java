package org.example.warehouse.models;

import javax.persistence.*;
import java.sql.Date;


@Entity
@Table(name = "write_off")
/**
 * Заявки на списание товара
 */
public class WriteOff {
    @Id
    @GeneratedValue
    private Long id;

    //@Column(name = "id_product")
    /**
     * id товара на списание
     */
    //private Long idProduct;
    @ManyToOne(cascade =  CascadeType.ALL)
    @JoinColumn(name = "id_product")
    private Goods goods;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "writeOff")
    private Confirmation confirmation;

    /**
     * Текстовое описание списываемого продукта
     */
    private String product;



    @Column(name = "group_product")
    /**
     * Текстовое описание группы списываемого продукта
     */
    private String groupProduct;


    /**
     * Количество
     */
    private Long amount;



    @Column(name = "person_in_charge")
    /**
     * ФИО человека, который списывает товар
     */
    private String personInCharge;

    /**
     * Причина списания
     */
    private String cause;


    @Column(name = "application_date")
    /**
     * Дата подачи заявки на списание
     */
    private Date applicationDate;


    public WriteOff() {
    }

    public Confirmation getConfirmation() {
        return confirmation;
    }

    public Date getApplicationDate() {
        return applicationDate;
    }

    public Goods getGoods() {
        return goods;
    }

    public Long getId() {
        return id;
    }


    public String getProduct() {
        return product;
    }

    public String getGroupProduct() {
        return groupProduct;
    }

    public Long getAmount() {
        return amount;
    }

    public String getPersonInCharge() {
        return personInCharge;
    }

    public String getCause() {
        return cause;
    }


    public void setConfirmation(Confirmation confirmation) {
        this.confirmation = confirmation;
    }

    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }


    public void setProduct(String product) {
        this.product = product;
    }

    public void setGroupProduct(String groupProduct) {
        this.groupProduct = groupProduct;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public void setPersonInCharge(String personInCharge) {
        this.personInCharge = personInCharge;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }
}
