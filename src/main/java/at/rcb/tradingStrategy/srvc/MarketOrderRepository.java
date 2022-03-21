package at.rcb.tradingStrategy.srvc;

import at.rcb.tradingStrategy.ents.MarketOrder;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarketOrderRepository extends ReactiveCrudRepository<MarketOrder, Long> {
}
