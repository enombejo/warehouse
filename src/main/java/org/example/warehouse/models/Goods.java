package org.example.warehouse.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

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

    @Column(name = "price")
    private Double price;
    @Column(name = "quantity")
    private Long quantity;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private Group group;

    @OneToMany(mappedBy = "goodsId", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Waybill> waybills;

    @OneToMany(mappedBy = "goods",cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private Set<WriteOff> writeOffs;

    @Column(name = "status")
    private String status;

    public Goods() {
    }

    public Set<WriteOff> getWriteOffs() {
        return writeOffs;
    }

    public void setWriteOffs(Set<WriteOff> writeOffs) {
        this.writeOffs = writeOffs;
    }

    public Set<Waybill> getWaybills() {
        return waybills;
    }

    public void setWaybills(Set<Waybill> waybills) {
        this.waybills = waybills;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }



    public Double getPrice() {
        return price;
    }

    public Long getQuantity() {
        return quantity;
    }


    public void setName(String name) {
        this.name = name;
    }


    public void setPrice(Double price) {
        this.price = price;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }


    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
