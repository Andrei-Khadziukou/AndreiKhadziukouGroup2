package mentoring.task4.service.impl;

import mentoring.task4.domain.Account;
import mentoring.task4.domain.Currency;
import mentoring.task4.domain.Deposit;
import mentoring.task4.domain.Rate;
import mentoring.task4.repository.impl.AccountDb;
import mentoring.task4.service.api.IRateManagerLocal;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Collection;


public class AccountBeanTest {

    private static final String PASSP = "TEST passp";
    private static final String PASSP_NEW = "new TEST passp";
    private static final String NAME = "TEST name";
    private static final String NAME_NEW = "new TEST name";
    private static final String USER = "TEST user";
    private static final String USER_NEW = "new TEST user";
    private static final String PHONE = "TEST phone";
    private static final String PHONE_NEW = "new TEST phone";
    private static final String ADDR = "TEST address";
    private static final String ADDR_NEW = "new TEST address";
    private static final String RATE = "0.8";
    private static final String RATE_NEW = "10400";

    AccountManagerBean bean = new AccountManagerBean();

    @Before
    public void setUp() {
        AccountDb repository = new AccountDb();
        repository.init();
        bean.setRepository(repository);
    }

    @Test
    public void testCreateDelete() {
        bean.add(PASSP, USER, NAME, PHONE, ADDR);
        Account acc = bean.findByUser(USER);
        Assert.assertNotNull(acc);
        String id = acc.getId();
        bean.remove(id);
        Assert.assertNull(bean.get(id));
    }

    @Test
    public void testUpdate() {
        bean.add(PASSP, USER, NAME, PHONE, ADDR);
        Account acc = bean.findByUser(USER);
        String id = acc.getId();
        Assert.assertEquals(PASSP, acc.getPassp());
        Assert.assertEquals(NAME, acc.getName());
        Assert.assertEquals(USER, acc.getUserId());
        Assert.assertEquals(PHONE, acc.getPhone());
        Assert.assertEquals(ADDR, acc.getAddress());
        bean.update(id, PASSP_NEW, USER_NEW, NAME_NEW, PHONE_NEW, ADDR_NEW);
        acc = bean.get(id);
        Assert.assertEquals(PASSP_NEW, acc.getPassp());
        Assert.assertEquals(NAME_NEW, acc.getName());
        Assert.assertEquals(USER_NEW, acc.getUserId());
        Assert.assertEquals(PHONE_NEW, acc.getPhone());
        Assert.assertEquals(ADDR_NEW, acc.getAddress());
    }

    @Test
    public void testUpdateNotFound() {
        bean.add(PASSP, USER, NAME, PHONE, ADDR);
        Account acc = bean.findByUser(USER);
        String id = acc.getId();
        bean.update("non existed", PASSP_NEW, USER_NEW, NAME_NEW, PHONE_NEW, ADDR_NEW);
        acc = bean.get(id);
        Assert.assertEquals(PASSP, acc.getPassp());
        Assert.assertEquals(NAME, acc.getName());
        Assert.assertEquals(USER, acc.getUserId());
        Assert.assertEquals(PHONE, acc.getPhone());
        Assert.assertEquals(ADDR, acc.getAddress());
    }

    @Test
    public void testFindAll() {
        Assert.assertFalse(bean.findAll().isEmpty());
    }

    @Test
    public void testFindDeposit() {
        Collection<Account> all = bean.findAll();
        Account acc = all.iterator().next();
        Deposit usd = bean.findDeposit(acc, "USD");
        Deposit nonExisted = bean.findDeposit(acc, "non existed");
        Assert.assertNotNull(usd);
        Assert.assertNull(nonExisted);
    }

    @Test
    public void testExchange() {
        setMock(new Rate("USD", "EUR", RATE));
        Collection<Account> all = bean.findAll();
        Account acc = all.iterator().next();
        String id = acc.getId();
        BigDecimal usd = bean.findDeposit(acc, "USD").getAmount();
        BigDecimal eur = bean.findDeposit(acc, "EUR").getAmount();
        BigDecimal summ = BigDecimal.TEN;
        bean.doExchange(acc, "USD", "EUR", summ);
        acc = bean.get(id);
        BigDecimal usdNew = bean.findDeposit(acc, "USD").getAmount();
        BigDecimal eurNew = bean.findDeposit(acc, "EUR").getAmount();
        Assert.assertEquals(usd.subtract(summ), usdNew);
        Assert.assertEquals(eur.add(summ.multiply(new BigDecimal(RATE))), eurNew);
    }

    @Test
    public void testExchangeToNewOne() {
        setMock(new Rate("USD", "BYR", RATE_NEW));
        Collection<Account> all = bean.findAll();
        Account acc = all.iterator().next();
        String id = acc.getId();
        BigDecimal usd = bean.findDeposit(acc, "USD").getAmount();
        Assert.assertNull(bean.findDeposit(acc, "BYR"));
        BigDecimal summ = BigDecimal.TEN;
        bean.doExchange(acc, "USD", "BYR", summ);
        acc = bean.get(id);
        BigDecimal usdNew = bean.findDeposit(acc, "USD").getAmount();
        BigDecimal byr = bean.findDeposit(acc, "BYR").getAmount();
        Assert.assertEquals(usd.subtract(summ), usdNew);
        Assert.assertEquals(summ.multiply(new BigDecimal(RATE_NEW)), byr);
    }

    private void setMock(final Rate rate) {
        bean.setRateManagerLocal(new IRateManagerLocal() {
            @Override
            public void add(String from, String to, String rate) {

            }

            @Override
            public Rate get(String id) {
                return rate;
            }

            @Override
            public void update(String id, String newRate) {

            }

            @Override
            public void remove(String id) {

            }

            @Override
            public Collection<Rate> findAll() {
                return null;
            }

            @Override
            public Collection<Rate> getAvailRates(String from) {
                return null;
            }

            @Override
            public Collection<Currency> getAvailCurrencies(String from) {
                return null;
            }
        });
    }

}
