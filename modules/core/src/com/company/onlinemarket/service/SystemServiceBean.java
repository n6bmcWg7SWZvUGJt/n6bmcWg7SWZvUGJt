package com.company.onlinemarket.service;

import com.company.market.entity.Purchase;
import com.company.market.entity.Shipment;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service(SystemService.NAME)
public class SystemServiceBean implements SystemService {


    @Override
    public BigDecimal getShipmentAmount(Shipment shipment) {

        List<Purchase> purchases = shipment.getPurchase();
        BigDecimal globalPriceSum = BigDecimal.valueOf(0);

        for (Purchase purchase : purchases) {
            BigDecimal priceQuantity = purchase.getGoods().getPrice().multiply(BigDecimal.valueOf(purchase.getCount()));
            BigDecimal procentminus = priceQuantity;
            procentminus = procentminus.multiply(BigDecimal.valueOf(purchase.getGoods().getDiscount()));
            procentminus = procentminus.divide(BigDecimal.valueOf(100));
            priceQuantity = priceQuantity.subtract(procentminus);
            globalPriceSum = globalPriceSum.add(priceQuantity);
        }

        return globalPriceSum;
    }
}