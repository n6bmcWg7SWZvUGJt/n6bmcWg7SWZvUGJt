package com.company.onlinemarket.entity;

import javax.persistence.*;


@Table(name = "dawdd")
@PrimaryKeyJoinColumn(name = "ID", referencedColumnName = "ID")
@DiscriminatorValue("LEGALENTITY")
@Entity(name = "onlinemarket_LegalEntity")
public class LegalEntity extends Customer {
    private static final long serialVersionUID = 4600077913553855823L;

    @Column(name = "LEGAL_NAME")
    protected String legalName;

    public String getLegalName() {
        return legalName;
    }

    public void setLegalName(String legalName) {
        this.legalName = legalName;
    }

    @Override
    public String getFullName() {
        return getLegalName();
    }
}