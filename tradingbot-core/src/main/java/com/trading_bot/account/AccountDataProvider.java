package com.trading_bot.account;

import java.util.Collection;

public interface AccountDataProvider< A > {

    Account< A > getLatestAccountInfo(A accountID);

    Collection< Account< A > > getLatestAccountInfo();

}
