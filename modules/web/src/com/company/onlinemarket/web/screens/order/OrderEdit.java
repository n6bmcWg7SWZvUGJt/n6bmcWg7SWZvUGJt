package com.company.onlinemarket.web.screens.order;

import com.company.market.entity.Goods;
import com.company.market.entity.Purchase;
import com.company.market.entity.Shipment;
import com.company.onlinemarket.entity.MarketUser;
import com.company.onlinemarket.entity.Order;
import com.company.onlinemarket.service.SystemService;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.components.DataGrid;
import com.haulmont.cuba.gui.components.Label;
import com.haulmont.cuba.gui.components.TextField;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.model.CollectionPropertyContainer;
import com.haulmont.cuba.gui.model.DataContext;
import com.haulmont.cuba.gui.screen.*;
import com.haulmont.cuba.security.global.UserSession;
import org.slf4j.Logger;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@UiController("onlinemarket_Order.edit")
@UiDescriptor("order-edit.xml")
@EditedEntityContainer("orderDc")
@LoadDataBeforeShow
public class OrderEdit extends StandardEditor<Order> {
    @Inject
    private CollectionContainer<Goods> goodsesDc;
    @Inject
    private Logger log;
    @Inject
    private DataManager dataManager;
    @Inject
    private CollectionContainer<Goods> allGoodsesDc;
    @Inject
    private CollectionPropertyContainer<Purchase> purchaseCollection;

    @Inject
    private Label<String> sumLabel;
    @Inject
    private SystemService systemService;
    @Inject
    private DataContext dataContext;


    @Inject
    private UserSession userSession;
    @Inject
    private TextField<Integer> globalDiscountField;
    @Inject
    private DataGrid<Purchase> purchasesTable;


    @Subscribe
    protected void onInit(InitEvent event) {
        DataGrid.Column column = purchasesTable.addGeneratedColumn("discpunts", new DataGrid.ColumnGenerator<Purchase, String>() {
            @Override
            public String getValue(DataGrid.ColumnGeneratorEvent<Purchase> event) {
                int discount = event.getItem().getGoods().getDiscount();
                BigDecimal price = event.getItem().getGoods().getPrice();
                BigDecimal buferMinus = price.divide(BigDecimal.valueOf(100)).multiply(BigDecimal.valueOf(discount));
                BigDecimal result = price.subtract(buferMinus);
                return String.valueOf(result);
            }

            @Override
            public Class<String> getType() {
                return String.class;
            }
        }, 1);
        column.setCaption("dicsoutn");
    }

    @Subscribe
    public void onAfterShow(AfterShowEvent event) {

        int d = Integer.parseInt(new SimpleDateFormat("dd").format(new Date()));
        log.info("число месяца" + d);

        HashSet s = new HashSet();
        if (allGoodsesDc.getItems().size() > 2) {
            for (int i = 0; i < 3; i++) {
                Goods g = allGoodsesDc.getItems().get(new Random().nextInt(allGoodsesDc.getItems().size() - 1));
                g.setDiscount(d);
                s.add(g);
            }
        }
        goodsesDc.setItems(s);

        MarketUser marketUser = (MarketUser) userSession.getUser();
        dataManager.reload(marketUser, "marketUser-view");
        List<MarketUser> marketUsers = dataManager.load(MarketUser.class).view("marketUser-view").list();
        for (MarketUser mu : marketUsers) {
            if (mu.getLogin().equals(marketUser.getLogin())) {
                marketUser = mu;
                break;
            }

        }
        if (getEditedEntity().getCustomer() == null) {
            getEditedEntity().setCustomer(marketUser.getCustomer());
            Order order = getEditedEntity();
            Shipment shipment = dataManager.create(Shipment.class);
            shipment.setPurchase(new ArrayList<>());
            order.setShipment(shipment);
            dataContext.evictAll();
            dataContext.merge(order);
        }
        refreshAll();
    }

    @Subscribe
    public void onBeforeCommitChanges(BeforeCommitChangesEvent event) {
        getEditedEntity().setOrderPrice(calculatePrice());
        int discount = globalDiscountField.getValue();
        Order order = getEditedEntity();
        order.setDiscount(discount);
        event.getDataContext().merge(order);
    }


    @Subscribe("goodsDataGrid")
    public void onGoodsDataGridItemClick(DataGrid.ItemClickEvent<Goods> event) {
        Order order = getEditedEntity();
        Purchase purchase = dataManager.create(Purchase.class);
        purchase.setGoods(event.getItem());
        purchase.setCount(1);
        Shipment shipment = order.getShipment();
        purchase.setShipment(shipment);
        List<Purchase> purchaseList = shipment.getPurchase();
        if (purchaseList == null) purchaseList = new ArrayList<>();
        purchaseList.add(purchase);
        dataContext.evictAll();
        dataContext.merge(order);
        refreshAll();
    }

    @Subscribe("refreshButton")
    public void onRefreshButtonClick(Button.ClickEvent event) {
        refreshAll();
    }

    private BigDecimal calculatePrice() {
        if (globalDiscountField.getValue() == null) globalDiscountField.setValue(0);
        Shipment shipment = getEditedEntity().getShipment();
        BigDecimal b = systemService.getShipmentAmount(shipment);
        long shipmentAmount = b.longValue();
        long summ = shipmentAmount - (shipmentAmount * globalDiscountField.getValue() / 100);
        return BigDecimal.valueOf(summ);
    }

    private void refreshAll() {
        purchaseCollection.setItems(getEditedEntity().getShipment().getPurchase());
        getScreenData().loadAll();
        sumLabel.setValue(String.valueOf(calculatePrice()));
    }

}