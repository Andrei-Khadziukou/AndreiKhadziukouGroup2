package com.epam.mentor.repository;

import com.epam.mentor.domain.BusinessException;
import com.epam.mentor.domain.Entity;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class AbstractDb<V extends Entity> implements ICrud<V> {

    protected Map<String, V> table = new HashMap<>();

    @Override
    public void create(V value) {
        if (table.values().contains(value)) throw new BusinessException("Item already exists");
        table.put(value.getId(), value);
    }

    @Override
    public V read(String key) {
        return table.get(key);
    }

    @Override
    public void update(V value) {
        table.put(value.getId(), value);
    }

    @Override
    public void delete(String key) {
        table.remove(key);
    }
}
