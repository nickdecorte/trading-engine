package io.trading.engine.core.barseries.requests;

public record BarSeriesRequest(
    String product,
    String interval
) {
}
