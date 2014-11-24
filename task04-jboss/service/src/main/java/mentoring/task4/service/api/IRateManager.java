package mentoring.task4.service.api;

import mentoring.task4.domain.Currency;
import mentoring.task4.domain.Rate;

import java.util.Collection;

public interface IRateManager {
    void add(String from, String to, String rate);

    Rate get(String id);

    void update(String id, String newRate);

    void remove(String id);

    Collection<Rate> findAll();

    Collection<Rate> getAvailRates(String from);

    Collection<Currency> getAvailCurrencies(String from);

}
