package com.company.onlinemarket.service;

import com.company.market.entity.Goods;
import com.company.market.entity.Purchase;
import com.company.market.entity.Shipment;
import com.company.market.service.UtilsService;
import com.company.onlinemarket.entity.Customer;
import com.company.onlinemarket.entity.Order;
import com.haulmont.cuba.core.EntityManager;
import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.Transaction;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.Metadata;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Service(RestAPIService.NAME)
public class RestAPIServiceBean implements RestAPIService {

    @Inject
    private Persistence persistence;
    @Inject
    private DataManager dataManager;
    @Inject
    private UtilsService utilsService;
    @Inject
    private Metadata metadata;

    @Override
    public String getGoodsList(String shop) {
        String output = "null";
        try (Transaction tx = persistence.createTransaction()) {
            List<Goods> gl = dataManager.load(Goods.class).list();
            output = utilsService.collectionToString(gl);
        }

        return output;

    }

    @Override
    public String createCustomer(String name) {
        try (Transaction tx = persistence.createTransaction()) {
            EntityManager em = persistence.getEntityManager();

            Customer customer = metadata.create(Customer.class);
            customer.setFullName(name);

            em.merge(customer);
            tx.commit();
        }
        return null;
    }

    @Override
    public String createOrder(String shop, String goodsName, String count) {
        try (Transaction tx = persistence.createTransaction()) {
            EntityManager em = persistence.getEntityManager();

            Order order = metadata.create(Order.class);
            order.setShipment(metadata.create(Shipment.class));
            List<Purchase> pl = new ArrayList<>();
            Goods goods = null;
            for (Goods g : dataManager.load(Goods.class).list()) {
                if (g.getProduct().getName().equals(goodsName)) {
                    goods = g;
                    break;
                }
            }
            Purchase purchase = metadata.create(Purchase.class);
            purchase.setGoods(goods);
            pl.add(purchase);
            order.getShipment().setPurchase(pl);

            em.merge(order);
            tx.commit();
        }
        return null;
    }

    @Override
    public String deleteOrder(String orderNo) {
        try (Transaction tx = persistence.createTransaction()) {
            EntityManager em = persistence.getEntityManager();

            Order order = null;
            for (Order o : dataManager.load(Order.class).list()) {
                if (o.getNo().equals(orderNo)) {
                    order = o;
                    break;
                }
            }

            if (order != null) em.remove(order);
            tx.commit();
        }

        return null;
    }
}