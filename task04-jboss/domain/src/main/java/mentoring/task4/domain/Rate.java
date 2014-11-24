package mentoring.task4.domain;

import java.math.BigDecimal;

public class Rate extends Entity {
    //id = from-to construction
    private String from;
    private String to;
    private BigDecimal rate;

    public Rate() {
    }

    public Rate(String from, String to, String rate) {
        this.from = from;
        this.to = to;
        this.rate = new BigDecimal(rate);
        constructId();
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public String constructId() {
        String id = from + '-' + to;
        setId(id);
        return id;
    }

    @Override
    public String toString() {
        return "Rate{" + getId() + ':' + rate + '}';
    }

}
