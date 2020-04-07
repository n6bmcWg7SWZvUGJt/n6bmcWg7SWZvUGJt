package com.company.onlinemarket.entity;

import com.company.market.entity.Shipment;
import com.haulmont.chile.core.annotations.Composition;
import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.PublishEntityChangedEvents;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import java.math.BigDecimal;



@PublishEntityChangedEvents
@Table(name = "ONLINEMARKET_ORDER")
@Entity(name = "onlinemarket_Order")
public class Order extends StandardEntity {
    private static final long serialVersionUID = 1353569766713595709L;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER_ID")
    protected Customer customer;

    @Column(name = "DISCOUNT")
    protected Integer discount;

    @Column(name = "ORDER_PRICE")
    protected BigDecimal orderPrice;

    @Column(name = "NO_")
    protected String no;

    @Transient
    @MetaProperty
    protected String status;

    @Composition
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "SHIPMENT_ID")
    protected Shipment shipment;

    public Shipment getShipment() {
        return shipment;
    }

    public void setShipment(Shipment shipment) {
        this.shipment = shipment;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @PostConstruct
    public void postConstruct() {
        setShipment(new Shipment());
    }
}