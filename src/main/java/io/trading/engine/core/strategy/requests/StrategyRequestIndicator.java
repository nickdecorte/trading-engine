package io.trading.engine.core.strategy.requests;

import java.util.Map;

public record StrategyRequestIndicator(
    String ref,
    String type,
    Map<String, Object> values
) {}