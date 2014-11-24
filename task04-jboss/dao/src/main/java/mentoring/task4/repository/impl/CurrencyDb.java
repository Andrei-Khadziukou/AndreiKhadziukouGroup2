package mentoring.task4.repository.impl;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import mentoring.task4.domain.Currency;

@Singleton
public class CurrencyDb extends AbstractDb<Currency> {
    @PostConstruct
    public void init() {
        create(new Currency("USD"));
        create(new Currency("EUR"));
        create(new Currency("BYR"));
        create(new Currency("TUG"));
    }

    @Override
    public void update(Currency value) {
        throw new UnsupportedOperationException();
    }

}
