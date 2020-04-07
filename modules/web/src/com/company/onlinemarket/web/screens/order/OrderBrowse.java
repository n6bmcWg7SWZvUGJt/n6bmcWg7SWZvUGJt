package com.company.onlinemarket.web.screens.order;

import com.haulmont.cuba.gui.screen.*;
import com.company.onlinemarket.entity.Order;

@UiController("onlinemarket_Order.browse")
@UiDescriptor("order-browse.xml")
@LookupComponent("ordersTable")
@LoadDataBeforeShow
public class OrderBrowse extends StandardLookup<Order> {
}