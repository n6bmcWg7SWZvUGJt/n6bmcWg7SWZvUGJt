package com.company.onlinemarket.entity;

import com.company.market.entity.Address;
import com.haulmont.chile.core.annotations.Composition;
import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.*;

@DiscriminatorColumn(name = "sdtype", discriminatorType = DiscriminatorType.STRING)
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorValue("CUSTOMER")
@NamePattern("%s|marketUser")
@Table(name = "ONLINEMARKET_CUSTOMER")
@Entity(name = "onlinemarket_Customer")
public class Customer extends StandardEntity {
    private static final long serialVersionUID = 3305083777164490901L;

    @Composition
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.PERSIST)
    protected MarketUser marketUser;

    @Column(name = "STYPE")
    protected String stype;

    @MetaProperty
    @Column(name = "FULL_NAME")
    protected String fullName;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ADDRESS_ID")
    protected Address address;

    @Column(name = "EMAIL")
    protected String email;

    public String getStype() {
        return stype;
    }

    public void setStype(String type) {
        this.stype = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public MarketUser getMarketUser() {
        return marketUser;
    }

    public void setMarketUser(MarketUser marketUser) {
        this.marketUser = marketUser;
    }
}