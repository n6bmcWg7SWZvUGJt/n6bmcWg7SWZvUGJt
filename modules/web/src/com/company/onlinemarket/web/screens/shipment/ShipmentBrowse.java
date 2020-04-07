package com.company.onlinemarket.web.screens.shipment;

import com.haulmont.cuba.gui.screen.*;
import com.company.market.entity.Shipment;

@UiController("market_Shipment.browse")
@UiDescriptor("shipment-browse.xml")
@LookupComponent("shipmentsTable")
@LoadDataBeforeShow
public class ShipmentBrowse extends StandardLookup<Shipment> {
}