package io.trading.engine.core.strategy.requests;

import java.util.List;

public record StrategyRequestRules(
    List<StrategyRequestRule> entry,
    List<StrategyRequestRule> exit
) {}