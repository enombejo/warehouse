package org.example.warehouse.models;


import javax.persistence.*;
import java.util.Date;

@Entity
/**
 * Подтверждение заявок на списание
 */
public class Confirmation {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_write_off")
    private WriteOff writeOff;

    private String applicant;

    @Column(name = "yes_no")
    private Boolean yesNo;

    @Column(name = "confirmation_date")
    /**
     * Дата подтверждения списания
     */
    private Date confirmationDate;


    public Confirmation() {
    }

    public Date getConfirmationDate() {
        return confirmationDate;
    }

    public Long getId() {
        return id;
    }

    public WriteOff getWriteOff() {
        return writeOff;
    }

    public String getApplicant() {
        return applicant;
    }

    public Boolean getYesNo() {
        return yesNo;
    }


    public void setConfirmationDate(Date confirmationDate) {
        this.confirmationDate = confirmationDate;
    }

    public void setWriteOff(WriteOff writeOff) {
        this.writeOff = writeOff;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    public void setYesNo(Boolean yesNo) {
        this.yesNo = yesNo;
    }
}
