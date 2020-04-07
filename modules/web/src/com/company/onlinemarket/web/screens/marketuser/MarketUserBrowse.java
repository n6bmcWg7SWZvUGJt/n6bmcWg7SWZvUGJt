package com.company.onlinemarket.web.screens.marketuser;

import com.haulmont.cuba.gui.screen.*;
import com.company.onlinemarket.entity.MarketUser;

@UiController("onlinemarket_MarketUser.browse")
@UiDescriptor("market-user-browse.xml")
@LookupComponent("marketUsersTable")
@LoadDataBeforeShow
public class MarketUserBrowse extends StandardLookup<MarketUser> {
}