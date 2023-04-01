package io.trading.engine.core.strategy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.trading.engine.core.common.ClassReflectionFactory;
import io.trading.engine.core.strategy.requests.StrategyRequestIndicator;

import org.ta4j.core.BarSeries;
import org.ta4j.core.Indicator;

class IndicatorFactory extends ClassReflectionFactory<Indicator> {

    IndicatorFactory(BarSeries barSeries) {
        super(barSeries);
    }

    Map<String, Indicator> makeByTradeStrategy(List<StrategyRequestIndicator> strategyRequestIndicators) {
        Map<String, Indicator> indicators = new HashMap<>();

        for(StrategyRequestIndicator strategyRequestIndicator : strategyRequestIndicators) {
            Indicator indicator = makeByTradeStrategyIndicator(strategyRequestIndicator, indicators);

            indicators.put(strategyRequestIndicator.ref(), indicator);
        }

        return indicators;
    }

    private Indicator makeByTradeStrategyIndicator(StrategyRequestIndicator strategyRequestIndicator, Map<String, Indicator> indicators){
        return makeInstance(strategyRequestIndicator.type(), strategyRequestIndicator.values(), indicators);
    }

}
