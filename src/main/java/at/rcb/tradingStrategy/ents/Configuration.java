package at.rcb.tradingStrategy.ents;

import java.time.LocalDateTime;

public class Configuration {
    private Character priceTriggers;
    private LocalDateTime timeOfDay;
    private long numbersOfOrdersCreated;

    public Character getPriceTriggers() {
        return priceTriggers;
    }

    public void setPriceTriggers(Character priceTriggers) {
        this.priceTriggers = priceTriggers;
    }

    public LocalDateTime getTimeOfDay() {
        return timeOfDay;
    }

    public void setTimeOfDay(LocalDateTime timeOfDay) {
        this.timeOfDay = timeOfDay;
    }

    public long getNumbersOfOrdersCreated() {
        return numbersOfOrdersCreated;
    }

    public void setNumbersOfOrdersCreated(long numbersOfOrdersCreated) {
        this.numbersOfOrdersCreated = numbersOfOrdersCreated;
    }
}
