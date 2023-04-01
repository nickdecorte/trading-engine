package io.trading.engine.core.strategy.requests;

import java.util.Map;

public record StrategyRequestRule(
    String type,
    Map<String, Object> values
) {}