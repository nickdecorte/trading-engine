package io.trading.engine.core.costmodel.requests;

public record CostModelRequest(
    String type,
    double fee
) {
}
