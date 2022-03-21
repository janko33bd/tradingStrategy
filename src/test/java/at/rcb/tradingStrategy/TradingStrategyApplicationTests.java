package at.rcb.tradingStrategy;

import at.rcb.tradingStrategy.ents.Configuration;
import at.rcb.tradingStrategy.ents.FinancialInstrument;
import at.rcb.tradingStrategy.ents.MarketData;
import at.rcb.tradingStrategy.ents.MarketOrder;
import at.rcb.tradingStrategy.srvc.MarketOrderRepository;
import at.rcb.tradingStrategy.srvc.TradingStrategyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TradingStrategyApplicationTests {

	@Autowired
	private MarketOrderRepository marketOrderRepository;

	@Autowired
	private TradingStrategyService tradingStrategyService;

	@Test
	void testAllMarketTriggers() {

		MarketData ringMarket = createMarket(3, LocalDateTime.of(2022, 3, 19, 0,0));
		MarketData eventMarket = createMarket(4, LocalDateTime.of(2022, 3, 19, 10, 0));
		MarketData endMarket = createMarket(4, LocalDateTime.of(2022, 3, 19, 10, 0));

		MarketData[] theMarket = {
				ringMarket,
				eventMarket,
				endMarket,
		};

		Flux<MarketData> marketDataSteam = Flux.fromArray(theMarket);

		Configuration config = new Configuration();
		config.setPriceTriggers('>');
		config.setTimeOfDay(LocalDateTime.of(2022, 3, 19, 10, 0));
		config.setNumbersOfOrdersCreated(2);
		
		tradingStrategyService.traderStrategy(marketDataSteam, config);

		//wait for it to be written to DB
		List<Mono<MarketOrder>> toBeSavedOrder = tradingStrategyService.getSavedOrder();
		if(toBeSavedOrder != null && !toBeSavedOrder.isEmpty()) {
			toBeSavedOrder.forEach(Mono::block);
		}

		List<MarketOrder> marketOrders = marketOrderRepository.findAll().toStream().collect(Collectors.toList());
		// all 3 triggered the order creation
		assertThat(marketOrders.size()).isEqualTo(3);

	}

	private MarketData createMarket(long price, LocalDateTime tradeTime) {
		MarketData startMarketData = new MarketData();
		FinancialInstrument instrument = new FinancialInstrument();
		instrument.setISIN("trada");
		startMarketData.setFinancialInstrument(instrument);
		startMarketData.setPrice(price);
		startMarketData.setTimestamp(tradeTime);
		return startMarketData;
	}

}
