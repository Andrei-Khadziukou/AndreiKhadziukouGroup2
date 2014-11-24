package mentoring.task4.domain;

import org.junit.Assert;
import org.junit.Test;

import java.util.UUID;


public class ToStringTest {

    public static final String ID = "test id";
    public static final String PASSP = "test passport number";
    public static final String PHONE = "test 19723-12";
    public static final String NAME = "test name";
    public static final String ADDR = "test address";
    public static final String USER = "test user";
    public static final String CURR = "BYR";
    public static final String CURR_TO = "TUG";
    public static final String SUMM = "10";
    public static final String RATE = "2";

    @Test
    public void testEntity() {
        Entity entity = new Entity();
        entity.setId(ID);
        String s = entity.toString();
        Assert.assertTrue(s.contains(ID));
    }

    @Test
    public void testDeposit() {
        Deposit deposit = new Deposit(CURR, SUMM);
        String s = deposit.toString();
        Assert.assertTrue(s.contains(CURR));
        Assert.assertTrue(s.contains(SUMM));
    }

    @Test
    public void testCurrency() {
        Currency currency = new Currency(CURR);
        String s = currency.toString();
        Assert.assertTrue(s.contains(CURR));
    }

    @Test
    public void testRate() {
        Rate rate = new Rate(CURR, CURR_TO, RATE);
        String s = rate.toString();
        Assert.assertTrue(s.contains(CURR + "-" + CURR_TO + ":"));
        Assert.assertTrue(s.contains(RATE));
    }

    @Test
    public void testAccount() {
        Account account = new Account();
        account.setId(ID);
        account.setPassp(PASSP);
        account.setUserId(USER);
        account.setAddress(ADDR);
        account.setName(NAME);
        account.setPhone(PHONE);
        String s = account.toString();
        Assert.assertTrue(s.contains(ID));
        Assert.assertTrue(s.contains(PASSP));
        Assert.assertTrue(s.contains(USER));
        Assert.assertTrue(s.contains(ADDR));
        Assert.assertTrue(s.contains(NAME));
        Assert.assertTrue(s.contains(PHONE));
    }
}
