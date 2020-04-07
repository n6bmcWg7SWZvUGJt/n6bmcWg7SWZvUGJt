package com.company.onlinemarket.listeners;

import com.company.onlinemarket.entity.Order;
import com.haulmont.cuba.core.TransactionalDataManager;
import com.haulmont.cuba.core.app.UniqueNumbersAPI;
import com.haulmont.cuba.core.app.events.EntityChangedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import javax.inject.Inject;
import java.util.UUID;

@Component("onlinemarket_OrderChangedListener")
public class OrderChangedListener {

    @Inject
    private TransactionalDataManager txDm;

    @Inject
    private UniqueNumbersAPI uniqueNumbers;


    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void beforeCommit(EntityChangedEvent<Order, UUID> event) {
        try {
            Order order = txDm.load(event.getEntityId()).view("order-view").one();
            if (order.getNo() == null) {
                order.setNo(String.valueOf(uniqueNumbers.getNextNumber("mySequence")));
                txDm.save(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void afterCommit(EntityChangedEvent<Order, UUID> event) {

    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_ROLLBACK)
    public void afterRollback(EntityChangedEvent<Order, UUID> event) {

    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMPLETION)
    public void afterCompletion(EntityChangedEvent<Order, UUID> event) {

    }
}