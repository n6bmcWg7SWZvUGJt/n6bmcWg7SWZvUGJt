package com.company.onlinemarket.web.screens.customer;

import com.haulmont.cuba.gui.screen.*;
import com.company.onlinemarket.entity.Customer;

@UiController("onlinemarket_Customer.edit")
@UiDescriptor("customer-edit.xml")
@EditedEntityContainer("customerDc")
@LoadDataBeforeShow
public class CustomerEdit extends StandardEditor<Customer> {
}