package mentoring.task4.repository.impl;

import mentoring.task4.domain.Entity;
import mentoring.task4.repository.api.IBaseOperations;
import mentoring.task4.repository.api.ICrud;
import org.apache.log4j.Logger;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class AbstractDb<V extends Entity> implements ICrud<V>, IBaseOperations<V> {

    private Logger logger = Logger.getLogger(this.getClass());

    protected Map<String, V> table = new ConcurrentHashMap<>();

    @Override
    public void create(V value) {
        table.put(value.getId(), value);
        logger.debug("New value to db added: " + value);
    }

    @Override
    public V read(String key) {
        return table.get(key);
    }

    @Override
    public void update(V value) {
        table.put(value.getId(), value);
        logger.debug("Value is updated: " + value);
    }

    @Override
    public void delete(String key) {
        table.remove(key);
        logger.debug("Value removed: " + key);
    }

    @Override
    public Collection<V> findAll() {
        return table.values();
    }
}
