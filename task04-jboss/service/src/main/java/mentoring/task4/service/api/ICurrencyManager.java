package mentoring.task4.service.api;

import java.util.Collection;

import mentoring.task4.domain.Currency;


public interface ICurrencyManager {
    void add(String cur);

    Currency get(String cur);

    void remove(String cur);

    Collection<Currency> findAll();
}
