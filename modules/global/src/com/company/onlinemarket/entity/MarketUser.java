package com.company.onlinemarket.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.security.entity.User;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@NamePattern("%s|login")
@Entity(name = "onlinemarket_MarketUser")
public class MarketUser extends User {
    private static final long serialVersionUID = 3058989577638420942L;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER_ID")
    protected Customer customer;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}