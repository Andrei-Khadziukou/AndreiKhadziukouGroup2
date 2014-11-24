package mentoring.task4.repository.impl;

import org.junit.Assert;
import org.junit.Test;


public class AccountDbTest {

    private AccountDb db = new AccountDb();

    @Test
    public void testInit() {
        Assert.assertTrue(db.findAll().isEmpty());
        db.init();
        Assert.assertFalse(db.findAll().isEmpty());
    }

    @Test
    public void testFind() {
        db.init();
        Assert.assertNotNull(db.findByUser("q"));
    }

    @Test
    public void testNotFound() {
        db.init();
        Assert.assertNull(db.findByUser("non existed"));
    }

}
