package com.company.onlinemarket.web.screens.shipment;

import com.haulmont.cuba.gui.screen.*;
import com.company.market.entity.Shipment;

@UiController("market_Shipment.edit")
@UiDescriptor("shipment-edit.xml")
@EditedEntityContainer("shipmentDc")
@LoadDataBeforeShow
public class ShipmentEdit extends StandardEditor<Shipment> {
}