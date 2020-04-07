package com.company.onlinemarket.web.screens.customer;

import com.company.onlinemarket.entity.Individual;
import com.company.onlinemarket.entity.LegalEntity;
import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.gui.ScreenBuilders;
import com.haulmont.cuba.gui.components.Action;
import com.haulmont.cuba.gui.components.GroupTable;
import com.haulmont.cuba.gui.screen.*;
import com.company.onlinemarket.entity.Customer;

import javax.inject.Inject;

@UiController("onlinemarket_Customer.browse")
@UiDescriptor("customer-browse.xml")
@LookupComponent("customersTable")
@LoadDataBeforeShow
public class CustomerBrowse extends StandardLookup<Customer> {
    @Inject
    private ScreenBuilders screenBuilders;
    @Inject
    private GroupTable<Customer> customersTable;
    @Inject
    private Metadata metadata;

    @Subscribe("popupButton.createIndividual")
    public void onCustomersTableCreateIndividual(Action.ActionPerformedEvent event) {
        createEditor(metadata.create(Individual.class));
    }

    @Subscribe("popupButton.createLegalEntity")
    public void onCustomersTableCreateLegalEntity(Action.ActionPerformedEvent event) {
        createEditor(metadata.create(LegalEntity.class));
    }

    private void createEditor(Customer customer) {
        screenBuilders.editor(customersTable)
                .editEntity(customer)
                .build()
                .show();
    }

}