package mentoring.task4.domain;

import java.math.BigDecimal;

public class Deposit {
    private String type;
    private BigDecimal amount;

    public Deposit() {
    }

    public Deposit(String type, String amount) {
        this.type = type;
        this.amount = new BigDecimal(amount);
    }

    public String getType() {
        return type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Deposit{" +
                "type='" + type + '\'' +
                ", amount=" + amount +
                '}';
    }
}
