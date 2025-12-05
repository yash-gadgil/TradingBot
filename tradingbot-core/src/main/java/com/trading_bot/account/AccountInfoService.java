package com.trading_bot.account;

import com.trading_bot.config.BaseTradingConfig;
import com.trading_bot.utils.ProviderHelper;

public class AccountInfoService< A, I > {

    private final AccountDataProvider<A> accountDataProvider;

    private final BaseTradingConfig baseTradingConfig;

    private final ProviderHelper providerHelper;

    public AccountInfoService(
            AccountDataProvider<A> accountDataProvider,
            BaseTradingConfig baseTradingConfig,
            ProviderHelper providerHelper
    ) {

        this.accountDataProvider = accountDataProvider;
        this.baseTradingConfig = baseTradingConfig;
        this.providerHelper = providerHelper;
    }
}
