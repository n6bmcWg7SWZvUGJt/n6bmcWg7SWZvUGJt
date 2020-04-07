package com.company.onlinemarket.entity;

import javax.persistence.*;


@Table(name = "dawd")
@PrimaryKeyJoinColumn(name = "ID", referencedColumnName = "ID")
@DiscriminatorValue("INDIVIDUAL")
@Entity(name = "onlinemarket_Individual")
public class Individual extends Customer {
    private static final long serialVersionUID = -4591882548083210026L;

    @Column(name = "FIRST_NAME")
    protected String firstName;

    @Column(name = "LAST_NAME")
    protected String lastName;

    @Column(name = "PATRONYMIC")
    protected String patronymic;

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getFullName() {
        return getFirstName() + " " + getLastName() + " " + getPatronymic();
    }
}