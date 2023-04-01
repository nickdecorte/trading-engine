package io.trading.engine.core.strategy;

import java.util.Map;

import io.trading.engine.core.strategy.requests.StrategyRequest;

import org.springframework.stereotype.Component;
import org.ta4j.core.BarSeries;
import org.ta4j.core.BaseStrategy;
import org.ta4j.core.Indicator;
import org.ta4j.core.Rule;
import org.ta4j.core.Strategy;

@Component
public class StrategyFactory {

    public Strategy make(BarSeries barSeries, StrategyRequest strategyRequest) {
        IndicatorFactory indicatorFactory = new IndicatorFactory(barSeries);
        RuleFactory ruleFactory = new RuleFactory(barSeries);

        Map<String, Indicator> indicators = indicatorFactory.makeByTradeStrategy(strategyRequest.indicators());

        Rule entryRule = ruleFactory.make(strategyRequest.rules().entry(), indicators).stream()
            .reduce(Rule::and)
            .get();

        Rule exitRule = ruleFactory.make(strategyRequest.rules().exit(), indicators).stream()
            .reduce(Rule::and)
            .get();

        return new BaseStrategy(entryRule, exitRule);
    }

}
