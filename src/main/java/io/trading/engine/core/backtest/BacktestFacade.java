package io.trading.engine.core.backtest;

import java.io.IOException;
import java.util.List;

import io.trading.engine.core.barseries.BarSeriesFactory;
import io.trading.engine.core.costmodel.CostModelFactory;
import io.trading.engine.core.strategy.StrategyFactory;
import io.trading.engine.core.backtest.requests.BacktestRequest;

import org.springframework.stereotype.Component;
import org.ta4j.core.BacktestExecutor;
import org.ta4j.core.BarSeries;
import org.ta4j.core.Strategy;
import org.ta4j.core.analysis.cost.CostModel;
import org.ta4j.core.analysis.cost.ZeroCostModel;
import org.ta4j.core.num.DecimalNum;
import org.ta4j.core.reports.TradingStatement;

@Component
public class BacktestFacade {

    private BarSeriesFactory barSeriesFactory;
    private CostModelFactory costModelFactory;
    private StrategyFactory strategyFactory;

    public BacktestFacade(
        BarSeriesFactory barSeriesFactory,
        CostModelFactory costModelFactory,
        StrategyFactory strategyFactory
    ) {
        this.barSeriesFactory = barSeriesFactory;
        this.costModelFactory = costModelFactory;
        this.strategyFactory = strategyFactory;
    }

    public List<TradingStatement> execute(BacktestRequest backtestRequest) throws IOException {
        BarSeries barSeries = barSeriesFactory.make(backtestRequest.barSeries());

        CostModel costModel = costModelFactory.make(backtestRequest.costModel());

        List<Strategy> strategies = backtestRequest.strategies().stream()
            .map(strategy -> strategyFactory.make(barSeries, strategy))
            .toList();

        BacktestExecutor backtestExecutor = new BacktestExecutor(barSeries, costModel, new ZeroCostModel());

        return backtestExecutor.execute(strategies, DecimalNum.valueOf(500));
    }

}
