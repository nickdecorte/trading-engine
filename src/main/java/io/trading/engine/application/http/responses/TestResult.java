package io.trading.engine.application.http.responses;

public record TestResult(
    double totalLoss,
    double totalProfit,
    double totalProfitLoss,
    double totalProfitLossPercentage,
    double lossCount,
    double profitCount,
    double breakEvenCount
) {

}
