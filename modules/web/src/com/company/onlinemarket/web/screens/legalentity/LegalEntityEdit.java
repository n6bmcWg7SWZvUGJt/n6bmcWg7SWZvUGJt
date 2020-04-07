package com.company.onlinemarket.web.screens.legalentity;

import com.company.onlinemarket.entity.Individual;
import com.company.onlinemarket.entity.MarketUser;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.gui.ScreenBuilders;
import com.haulmont.cuba.gui.components.Action;
import com.haulmont.cuba.gui.components.DialogAction;
import com.haulmont.cuba.gui.model.DataContext;
import com.haulmont.cuba.gui.screen.*;
import com.company.onlinemarket.entity.LegalEntity;

import javax.inject.Inject;

@UiController("onlinemarket_LegalEntity.edit")
@UiDescriptor("legal-entity-edit.xml")
@EditedEntityContainer("legalEntityDc")
@LoadDataBeforeShow
public class LegalEntityEdit extends StandardEditor<LegalEntity> {
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

        try {
            LegalEntity legalEntity = getEditedEntity();
            MarketUser marketUser = legalEntity.getMarketUser();
            marketUser.setCustomer(legalEntity);
            //getEditedEntity().getMarketUser().setCustomer(getEditedEntity());
            dataManager.commit(getEditedEntity().getMarketUser());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}