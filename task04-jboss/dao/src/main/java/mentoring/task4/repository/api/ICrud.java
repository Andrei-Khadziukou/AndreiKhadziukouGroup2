package mentoring.task4.repository.api;

import mentoring.task4.domain.Entity;

public interface ICrud<V extends Entity> {
    void create(V value);

    V read(String key);

    void update(V value);

    void delete(String key);
}
