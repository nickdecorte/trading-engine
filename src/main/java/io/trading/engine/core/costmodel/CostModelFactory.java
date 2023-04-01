package io.trading.engine.core.costmodel;

import io.trading.engine.core.costmodel.requests.CostModelRequest;

import org.springframework.stereotype.Component;
import org.ta4j.core.analysis.cost.CostModel;
import org.ta4j.core.analysis.cost.FixedTransactionCostModel;
import org.ta4j.core.analysis.cost.LinearTransactionCostModel;

@Component
public class CostModelFactory {

    public CostModel make(CostModelRequest costModelRequest) {
        if (costModelRequest.type().equals("fixed")) {
            return new FixedTransactionCostModel(costModelRequest.fee());
        }

        if (costModelRequest.type().equals("linear")) {
            return new LinearTransactionCostModel(costModelRequest.fee());
        }

        throw new RuntimeException("Given cost model not found");
    }

}
