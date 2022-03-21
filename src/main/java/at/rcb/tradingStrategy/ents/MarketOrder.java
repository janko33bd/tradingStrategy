package at.rcb.tradingStrategy.ents;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table
public class MarketOrder {

    @Id
    private Long id;
    private long financialInstrumentId;
    private long price;
    private long volume;
    private Integer state;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getFinancialInstrumentId() {
        return financialInstrumentId;
    }

    public void setFinancialInstrumentId(long financialInstrumentId) {
        this.financialInstrumentId = financialInstrumentId;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public long getVolume() {
        return volume;
    }

    public void setVolume(long volume) {
        this.volume = volume;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
