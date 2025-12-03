package com.trading_bot.account;

public record Account< A > (
    A accountId,
    String currency,
    double totalBalance,
    double unrealisedPnl,
    double realisedPnl,
    double marginUsed,
    double marginAvailable,
    double marginRate,
    long openTrades,
    long openOrders
) {
    public double amountAvailableRatio() {
        return totalBalance == 0 ? 0 : marginAvailable / totalBalance;
    }
    public double netAssetValue() {
        return marginUsed + marginAvailable;
    }
    public Account(
            A accountId,
            String currency,
            double totalBalance,
            double marginAvailable,
            double marginRate
    ) {
        this(
                accountId,
                currency,
                totalBalance,
                0.0,
                0.0,
                0.0,
                marginAvailable,
                marginRate,
                0L,
                0L
        );
    }
}