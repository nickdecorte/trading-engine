package io.trading.engine.core.backtest.requests;

import java.util.List;

import io.trading.engine.core.costmodel.requests.CostModelRequest;
import io.trading.engine.core.strategy.requests.StrategyRequest;
import io.trading.engine.core.barseries.requests.BarSeriesRequest;

public record BacktestRequest(
    BarSeriesRequest barSeries,
    CostModelRequest costModel,
    List<StrategyRequest> strategies

) {
}
