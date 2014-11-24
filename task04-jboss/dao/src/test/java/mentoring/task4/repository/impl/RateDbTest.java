package mentoring.task4.repository.impl;

import org.junit.Assert;
import org.junit.Test;


public class RateDbTest {

    private RateDb db = new RateDb();

    @Test
    public void testInit() {
        Assert.assertTrue(db.findAll().isEmpty());
        db.init();
        Assert.assertFalse(db.findAll().isEmpty());
    }

}
