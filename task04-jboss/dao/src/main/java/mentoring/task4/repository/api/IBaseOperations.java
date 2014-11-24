package mentoring.task4.repository.api;

import mentoring.task4.domain.Entity;


public interface IBaseOperations<V extends Entity> {
    java.util.Collection<V> findAll();
}
