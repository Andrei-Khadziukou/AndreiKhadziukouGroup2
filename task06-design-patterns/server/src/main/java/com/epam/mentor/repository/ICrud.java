package com.epam.mentor.repository;

import com.epam.mentor.domain.Entity;

public interface ICrud<V extends Entity> {
    void create(V value);

    V read(String key);

    void update(V value);

    void delete(String key);
}
