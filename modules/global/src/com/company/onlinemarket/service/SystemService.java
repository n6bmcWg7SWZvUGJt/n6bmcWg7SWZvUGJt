package com.company.onlinemarket.service;

import com.company.market.entity.Shipment;

import java.math.BigDecimal;

public interface SystemService {
    String NAME = "onlinemarket_SystemService";

    BigDecimal getShipmentAmount(Shipment shipment);

}