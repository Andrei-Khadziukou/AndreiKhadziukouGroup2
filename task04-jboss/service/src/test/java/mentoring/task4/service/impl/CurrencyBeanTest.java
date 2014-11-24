package mentoring.task4.service.impl;

import mentoring.task4.repository.impl.CurrencyDb;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CurrencyBeanTest {

    private static final String CURR = "TEST";

    CurrencyManagerBean bean = new CurrencyManagerBean();

    @Before
    public void setUp() {
        CurrencyDb repository = new CurrencyDb();
        repository.init();
        bean.setRepository(repository);
    }

    @Test
    public void testCreateDelete() {
        bean.add(CURR);
        Assert.assertNotNull(bean.get(CURR));
        bean.remove(CURR);
        Assert.assertNull(bean.get(CURR));
    }

    @Test
    public void testFindAll() {
        Assert.assertFalse(bean.findAll().isEmpty());
    }

}
