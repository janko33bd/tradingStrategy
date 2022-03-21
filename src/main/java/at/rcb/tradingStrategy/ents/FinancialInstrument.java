package at.rcb.tradingStrategy.ents;

public class FinancialInstrument {
    private String ISIN ;
    private String ID;

    public String getISIN() {
        return ISIN;
    }

    public void setISIN(String ISIN) {
        this.ISIN = ISIN;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
