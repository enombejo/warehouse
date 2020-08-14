package org.example.warehouse.service;

import org.springframework.stereotype.Service;

import java.util.Date;
@Service
/**
 * Для работы с подтверждением списания
 */
public class ConfirmationService {

    /**
     * Номер заявки на списание
     */
    private long number;

    /**
     * ФИО подтверждающего
     */
    private String applicant;


    /**
     * Поле подтверждения списания
     */
    private Boolean yesNo;


    /**
     * Дата подтверждения списания
     */
    private Date confirmationDate;

    public ConfirmationService() {
        confirmationDate = new Date();
    }


    public long getNumber() {
        return number;
    }

    public String getApplicant() {
        return applicant;
    }

    public Boolean getYesNo() {
        return yesNo;
    }

    public Date getConfirmationDate() {
        return confirmationDate;
    }


    public void setNumber(long number) {
        this.number = number;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    public void setYesNo(Boolean yesNo) {
        this.yesNo = yesNo;
    }

    public void setConfirmationDate(Date confirmationDate) {
        this.confirmationDate = confirmationDate;
    }
}
