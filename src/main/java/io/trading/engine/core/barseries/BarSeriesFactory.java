package io.trading.engine.core.barseries;

import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;

import io.trading.engine.core.barseries.requests.BarSeriesRequest;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.Interval;

import org.springframework.stereotype.Component;
import org.ta4j.core.BarSeries;
import org.ta4j.core.BaseBarSeriesBuilder;

@Component
public class BarSeriesFactory {

    public BarSeries make(BarSeriesRequest barSeriesRequestBarSeries) throws IOException {
        Stock stock = fetchStockTicks(barSeriesRequestBarSeries.product(), barSeriesRequestBarSeries.interval());

        BarSeries series = new BaseBarSeriesBuilder()
            .withName(barSeriesRequestBarSeries.product())
            .build();

        stock.getHistory().stream().forEach(q -> {
            series.addBar(
                ZonedDateTime.ofInstant(q.getDate().toInstant(), ZoneId.systemDefault()),
                q.getOpen(),
                q.getHigh(),
                q.getLow(),
                q.getClose(),
                q.getVolume()
            );
        });

        return series;
    }

    private Stock fetchStockTicks(String product, String interval) throws IOException {
        Calendar from = Calendar.getInstance();
        Calendar to = Calendar.getInstance();
        from.add(Calendar.YEAR, -5);

        return YahooFinance.get(product, from, to, Interval.valueOf(interval));
    }

}
