package mentoring.task4.service.impl;

import mentoring.task4.domain.Currency;
import mentoring.task4.repository.impl.RateDb;
import mentoring.task4.service.api.ICurrencyManagerLocal;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Collection;


public class RateBeanTest {

    private static final String CURR = "TEST";
    private static final String CURR_TO = "TOT";
    private static final String ID = "TEST-TOT";
    private static final String RATE = "5";
    private static final String NEW_RATE = "7";

    RateManagerBean bean = new RateManagerBean();

    @Before
    public void setUp() {
        RateDb repository = new RateDb();
        repository.init();
        bean.setRepository(repository);
    }

    @Test
    public void testCreateDelete() {
        bean.add(CURR, CURR_TO, RATE);
        Assert.assertNotNull(bean.get(ID));
        bean.remove(ID);
        Assert.assertNull(bean.get(ID));
    }

    @Test
    public void testUpdate() {
        bean.add(CURR, CURR_TO, RATE);
        Assert.assertEquals(new BigDecimal(RATE), bean.get(ID).getRate());
        bean.update(ID, NEW_RATE);
        bean.update("unexisted", NEW_RATE);
        Assert.assertEquals(new BigDecimal(NEW_RATE), bean.get(ID).getRate());
    }

    @Test
    public void testAvailRatesAndCurrs() {
        ICurrencyManagerLocal mock = new ICurrencyManagerLocal() {
            @Override
            public void add(String cur) {

            }

            @Override
            public Currency get(String cur) {
                return new Currency(cur);
            }

            @Override
            public void remove(String cur) {

            }

            @Override
            public Collection<Currency> findAll() {
                return null;
            }
        };
        bean.setCurrencyManagerLocal(mock);
        Assert.assertFalse(bean.getAvailRates("USD").isEmpty());
        Assert.assertFalse(bean.getAvailCurrencies("USD").isEmpty());
    }

    @Test
    public void testFindAll() {
        Assert.assertFalse(bean.findAll().isEmpty());
    }

}
