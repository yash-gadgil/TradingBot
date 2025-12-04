package com.trading_bot.account;

import net.jacobpeterson.alpaca.openapi.trader.ApiException;
import net.jacobpeterson.alpaca.rest.trader.AlpacaTraderAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Service
public class AlpacaAccountDataProviderService implements AccountDataProvider<String> {

    private final Map<String, AlpacaTraderAPI> apiByAccount;

    @Autowired
    public AlpacaAccountDataProviderService(Map<String, AlpacaTraderAPI> apiByAccount) {
        this.apiByAccount = apiByAccount;
    }

    /**
     *
     * @param accountID
     *      uses the account_number field from Alpaca Markets v2/account endpoint
     * @return
     *      an {@link Account} object
     */
    @Override
    public Account<String> getLatestAccountInfo(String accountID) {

        try {
            AlpacaTraderAPI alpacaTraderAPI = apiByAccount.get(accountID);

            net.jacobpeterson.alpaca.openapi.trader.model.Account
                    alpacaAccount = alpacaTraderAPI.accounts().getAccount();

            System.out.println("Account: "); //
            System.out.println(alpacaAccount); //

            return accountFromRaw(alpacaAccount);
        } catch (ApiException e) {

            System.out.println(e.getMessage()); //
            return null;
        }
    }

    @Override
    public Collection<Account<String>> getLatestAccountInfo() {
        return List.of();
    }

    private static Account<String> accountFromRaw(net.jacobpeterson.alpaca.openapi.trader.model.Account alpacaAccount) {

        double totalBalance = alpacaAccount.getEquity() == null ?
                0.0 : Double.parseDouble( alpacaAccount.getEquity() );

        double marginAvailable = alpacaAccount.getInitialMargin() == null ?
                0.0 : Double.parseDouble( alpacaAccount.getInitialMargin() );

        double marginRate = alpacaAccount.getMultiplier() == null ?
                0.0 : Double.parseDouble( alpacaAccount.getMultiplier() );

        return new Account<>(
                alpacaAccount.getAccountNumber(),
                alpacaAccount.getCurrency(),
                totalBalance,
                marginAvailable,
                marginRate
        );
    }
}

