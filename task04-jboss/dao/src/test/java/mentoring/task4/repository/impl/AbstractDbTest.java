package mentoring.task4.repository.impl;

import mentoring.task4.domain.Entity;
import org.junit.Assert;
import org.junit.Test;


public class AbstractDbTest {

    private static final String ID = "test id";
    private static final String VALUE = "test value";
    private static final String NEW_VALUE = "test new value";

    AbstractDb<Zu> db = new AbstractDb<Zu>() {
    };

    @Test
    public void testCreateDelete() {
        db.create(getZu());
        Assert.assertFalse(db.findAll().isEmpty());
        db.delete(ID);
        Assert.assertTrue(db.findAll().isEmpty());
    }

    @Test
    public void testGetUpdate() {
        db.create(getZu());
        Zu zu = db.read(ID);
        Assert.assertEquals(VALUE, zu.getValue());
        zu.setValue(NEW_VALUE);
        db.update(zu);
        zu = db.read(ID);
        Assert.assertEquals(NEW_VALUE, zu.getValue());
    }

    private Zu getZu() {
        return new Zu(ID, VALUE);
    }

    private static class Zu extends Entity {
        private String value;

        private Zu(String id, String value) {
            setId(id);
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

}
