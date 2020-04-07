package com.company.onlinemarket.web.screens.marketuser;

import com.company.onlinemarket.entity.Individual;
import com.company.onlinemarket.entity.LegalEntity;
import com.company.onlinemarket.entity.MarketUser;
import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.core.global.PasswordEncryption;
import com.haulmont.cuba.gui.Dialogs;
import com.haulmont.cuba.gui.ScreenBuilders;
import com.haulmont.cuba.gui.components.Action;
import com.haulmont.cuba.gui.components.DialogAction;
import com.haulmont.cuba.gui.components.TextField;
import com.haulmont.cuba.gui.model.DataContext;
import com.haulmont.cuba.gui.screen.*;
import com.haulmont.cuba.security.entity.User;

import javax.inject.Inject;

@UiController("onlinemarket_MarketUser.edit")
@UiDescriptor("market-user-edit.xml")
@EditedEntityContainer("marketUserDc")
@LoadDataBeforeShow
public class MarketUserEdit extends StandardEditor<MarketUser> {

    @Inject
    private PasswordEncryption passwordEncryption;
    @Inject
    private TextField<String> passwordField;
    @Inject
    private ScreenBuilders screenBuilders;
    @Inject
    private Dialogs dialogs;
    @Inject
    private DataContext dataContext;
    @Inject
    private Metadata metadata;


    @Subscribe
    public void onBeforeCommitChanges(BeforeCommitChangesEvent event) {
        User user = getEditedEntity();
        String password = passwordField.getRawValue();

        String passwordHash = passwordEncryption.getPasswordHash(user.getId(), password);
        user.setPassword(passwordHash);

        if (getEditedEntity().getCustomer() == null) {
            LegalEntity legalEntity = metadata.create(LegalEntity.class);
            Individual individual = metadata.create(Individual.class);
            dialogs.createOptionDialog()
                    .withCaption("Confirm")
                    .withMessage("Create costumer?")
                    .withActions(
                            new DialogAction(DialogAction.Type.YES, Action.Status.PRIMARY).withHandler(e -> {

                                legalEntity.setMarketUser(getEditedEntity());
                                getEditedEntity().setCustomer(legalEntity);
                                screenBuilders.editor(LegalEntity.class, this)
                                        .newEntity(legalEntity)
                                        .withScreenId("onlinemarket_LegalEntity.edit")
                                        .withParentDataContext(dataContext)
                                        .withLaunchMode(OpenMode.DIALOG)
                                        .build()
                                        .show();

                            }).withCaption("new LegalEntity"),
                            new DialogAction(DialogAction.Type.YES, Action.Status.PRIMARY).withHandler(e -> {

                                individual.setMarketUser(getEditedEntity());
                                getEditedEntity().setCustomer(individual);
                                screenBuilders.editor(Individual.class, this).newEntity(individual)
                                        .withScreenId("onlinemarket_Individual.edit")
                                        .withParentDataContext(dataContext)// specific lookup screen
                                        .withLaunchMode(OpenMode.DIALOG)        // open as modal dialog
                                        .build()
                                        .show();

                            }).withCaption("new Individual"),
                            new DialogAction(DialogAction.Type.NO)
                    )
                    .show();
        }
    }

}