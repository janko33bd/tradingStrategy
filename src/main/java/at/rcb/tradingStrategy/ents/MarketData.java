package at.rcb.tradingStrategy.ents;

import java.time.LocalDateTime;

public class MarketData {

    private FinancialInstrument financialInstrument;
    private long Price;
    private LocalDateTime Timestamp;

    public FinancialInstrument getFinancialInstrument() {
        return financialInstrument;
    }

    public void setFinancialInstrument(FinancialInstrument financialInstrument) {
        this.financialInstrument = financialInstrument;
    }

    public long getPrice() {
        return Price;
    }

    public void setPrice(long price) {
        Price = price;
    }

    public LocalDateTime getTimestamp() {
        return Timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        Timestamp = timestamp;
    }
}
