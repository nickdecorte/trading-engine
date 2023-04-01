package io.trading.engine.application.http.responses;

import org.springframework.stereotype.Component;
import org.ta4j.core.reports.TradingStatement;

@Component
public class TestResultFactory {

    public TestResult make(TradingStatement tradingStatement) {
        return new TestResult(
            tradingStatement.getPerformanceReport().getTotalLoss().doubleValue(),
            tradingStatement.getPerformanceReport().getTotalProfit().doubleValue(),
            tradingStatement.getPerformanceReport().getTotalProfitLoss().doubleValue(),
            tradingStatement.getPerformanceReport().getTotalProfitLossPercentage().doubleValue(),
            tradingStatement.getPositionStatsReport().getLossCount().doubleValue(),
            tradingStatement.getPositionStatsReport().getProfitCount().doubleValue(),
            tradingStatement.getPositionStatsReport().getBreakEvenCount().doubleValue()
        );
    }

}
