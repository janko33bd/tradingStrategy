package at.rcb.tradingStrategy.srvc;

import at.rcb.tradingStrategy.ents.Configuration;
import at.rcb.tradingStrategy.ents.MarketData;
import at.rcb.tradingStrategy.ents.MarketOrder;
import at.rcb.tradingStrategy.ents.State;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TradingStrategyService {

    private final List<Mono<MarketOrder>> savedOrder = new ArrayList<>();

    private final MarketOrderRepository marketOrderRepository;

    private final Map<Character, Integer> triggerMap = new HashMap<>();

    public TradingStrategyService(MarketOrderRepository marketOrderRepository) {
        this.marketOrderRepository = marketOrderRepository;
        triggerMap.put('<', -1);
        triggerMap.put('>', 1);
        triggerMap.put('=', 0);
    }

    public void traderStrategy(Flux<MarketData> marketDataStream, Configuration configuration) {
        AtomicLong count = new AtomicLong(0);
        AtomicLong previousPriceAt = new AtomicLong(-1);
        marketDataStream.subscribe(marketDta -> {
            if (count.incrementAndGet() == configuration.getNumbersOfOrdersCreated()) {
                generateOrder();
            }
            if (marketDta.getTimestamp().equals(configuration.getTimeOfDay())) {
                generateOrder();
            }
            long previousPrice = previousPriceAt.getAndSet(marketDta.getPrice());
            if (previousPrice != -1) {
                if (marketTriggered(configuration.getPriceTriggers(), previousPrice, marketDta.getPrice())) {
                    generateOrder();
                }
            }

        });
    }

    private boolean marketTriggered(Character priceTrigger, long previousPrice, long currentPrice) {
        int compared = Long.compare(previousPrice, currentPrice);
        Integer trgr = triggerMap.get(priceTrigger);
        if(trgr != null && compared == trgr){
            return true;
        }
        return false;
    }

    private void generateOrder() {
        MarketOrder ordr = new MarketOrder();
        ordr.setFinancialInstrumentId(3);
        ordr.setPrice(17);
        ordr.setState(State.Filled.ordinal());
        savedOrder.add(this.marketOrderRepository.save(ordr));
    }

    public List<Mono<MarketOrder>> getSavedOrder() {
        return savedOrder;
    }
}
