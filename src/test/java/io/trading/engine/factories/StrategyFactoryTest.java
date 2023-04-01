package io.trading.engine.factories;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import io.trading.engine.core.strategy.StrategyFactory;
import io.trading.engine.core.strategy.requests.StrategyRequest;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.ta4j.core.BarSeries;
import org.ta4j.core.Strategy;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.mockito.Mockito.mock;

class StrategyFactoryTest {

    private final List<String> strategyKeys = Arrays.asList("strategy2", "strategy1");

    @Test
    void test() throws Exception {
        StrategyFactory strategyFactory = new StrategyFactory();

        for (String strategyKey: strategyKeys) {
            Strategy strategy = strategyFactory.make(mock(BarSeries.class), getStrategyRequestByFileName(strategyKey));

            System.out.println("Successfully created strategy: " + strategyKey);
        }
    }

    private StrategyRequest getStrategyRequestByFileName(String fileName) throws IOException {
        ClassPathResource classPathResource = new ClassPathResource("strategies/" + fileName + ".json");

        return (new ObjectMapper()).readValue(classPathResource.getContentAsString(UTF_8), StrategyRequest.class);
    }

}
