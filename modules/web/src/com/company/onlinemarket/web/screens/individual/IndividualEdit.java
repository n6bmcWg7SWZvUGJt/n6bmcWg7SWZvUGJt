package com.company.onlinemarket.web.screens.individual;

import com.company.onlinemarket.entity.MarketUser;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.gui.ScreenBuilders;
import com.haulmont.cuba.gui.model.DataContext;
import com.haulmont.cuba.gui.screen.*;
import com.company.onlinemarket.entity.Individual;

import javax.inject.Inject;

@UiController("onlinemarket_Individual.edit")
@UiDescriptor("individual-edit.xml")
@EditedEntityContainer("individualDc")
@LoadDataBeforeShow
public class IndividualEdit extends StandardEditor<Individual> {
    @Inject
    private DataManager dataManager;
    @Inject
    private ScreenBuilders screenBuilders;
    @Inject
    private DataContext dataContext;
    @Inject
    private Metadata metadata;

    @Subscribe
    public void onBeforeCommitChanges(BeforeCommitChangesEvent event) {
        try {
        getEditedEntity().getMarketUser().setCustomer(getEditedEntity());
        dataManager.commit(getEditedEntity().getMarketUser());
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (getEditedEntity().getMarketUser() == null) {

            MarketUser marketUser = metadata.create(MarketUser.class);
            marketUser.setCustomer(getEditedEntity());
            marketUser.setLogin("");
            marketUser.setLoginLowerCase("");

            getEditedEntity().setMarketUser(marketUser);
            screenBuilders.editor(MarketUser.class, this)
                    .newEntity(marketUser)
                    .withScreenId("onlinemarket_MarketUser.edit")
                    .withParentDataContext(dataContext)
                    .withLaunchMode(OpenMode.DIALOG)
                    .build()
                    .show();
        }
    }


}