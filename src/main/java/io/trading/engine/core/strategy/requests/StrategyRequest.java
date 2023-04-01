package io.trading.engine.core.strategy.requests;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record StrategyRequest(
    String name,
    List<StrategyRequestIndicator> indicators,
    StrategyRequestRules rules
) {}