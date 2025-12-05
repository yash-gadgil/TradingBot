package com.trading_bot.instrument;

public record TradeableInstrument< I > (
        String instrument,
        String description,
        I instrumentId,
        double pip
) {}

