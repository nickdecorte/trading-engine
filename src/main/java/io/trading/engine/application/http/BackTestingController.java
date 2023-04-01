package io.trading.engine.application.http;

import java.util.List;

import io.trading.engine.application.http.responses.TestResult;
import io.trading.engine.application.http.responses.TestResultFactory;
import io.trading.engine.core.backtest.BacktestFacade;
import io.trading.engine.core.backtest.requests.BacktestRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/testing")
@RestController
public class BackTestingController {

    private BacktestFacade backtestFacade;
    private TestResultFactory testResultFactory;

    public BackTestingController(BacktestFacade backtestFacade, TestResultFactory testResultFactory) {
        this.backtestFacade = backtestFacade;
        this.testResultFactory = testResultFactory;
    }

    @PostMapping
    public List<TestResult> test(@RequestBody BacktestRequest backtestRequest) throws Exception {
        return backtestFacade.execute(backtestRequest).stream()
            .map(t -> testResultFactory.make(t))
            .toList();
    }

}
