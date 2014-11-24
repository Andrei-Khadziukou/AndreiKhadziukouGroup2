package mentoring.task4.domain;

import java.util.List;

public class Account extends Entity {
    private String passp;
    private String name;
    private String phone;
    private String address;
    private String userId;
    private List<Deposit> deposits;

    public String getPassp() {
        return passp;
    }

    public void setPassp(String passp) {
        this.passp = passp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<Deposit> getDeposits() {
        return deposits;
    }

    public void setDeposits(List<Deposit> deposits) {
        this.deposits = deposits;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Account{" +
                "passport='" + getId() + '\'' +
                ", passport='" + passp + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
