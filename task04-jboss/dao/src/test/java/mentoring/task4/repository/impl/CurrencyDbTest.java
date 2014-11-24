package mentoring.task4.repository.impl;

import mentoring.task4.domain.Currency;
import org.junit.Assert;
import org.junit.Test;

public class CurrencyDbTest {

    private CurrencyDb db = new CurrencyDb();

    @Test
    public void testInit() {
        Assert.assertTrue(db.findAll().isEmpty());
        db.init();
        Assert.assertFalse(db.findAll().isEmpty());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testUpdate() {
        db.update(new Currency());
    }

}
