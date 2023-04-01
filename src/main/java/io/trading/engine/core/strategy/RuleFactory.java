package io.trading.engine.core.strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.trading.engine.core.common.ClassReflectionFactory;
import io.trading.engine.core.strategy.requests.StrategyRequestRule;

import org.ta4j.core.BarSeries;
import org.ta4j.core.Indicator;
import org.ta4j.core.Rule;

class RuleFactory extends ClassReflectionFactory<Rule> {

    RuleFactory(BarSeries barSeries) {
        super(barSeries);
    }

    List<Rule> make(List<StrategyRequestRule> strategyRequestRules, Map<String, Indicator> indicators) {
        List<Rule> rules = new ArrayList<>();

        for (StrategyRequestRule strategyRequestRule : strategyRequestRules) {
            rules.add(makeRule(strategyRequestRule, indicators));
        }

        return rules;
    }

    private Rule makeRule(StrategyRequestRule strategyRequestRule, Map<String, Indicator> indicators){
        return makeInstance(strategyRequestRule.type(), strategyRequestRule.values(), indicators);
    }

}
