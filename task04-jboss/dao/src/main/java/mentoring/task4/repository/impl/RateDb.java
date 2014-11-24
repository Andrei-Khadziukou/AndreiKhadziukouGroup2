package mentoring.task4.repository.impl;

import mentoring.task4.domain.Rate;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;

@Singleton
public class RateDb extends AbstractDb<Rate> {

    @PostConstruct
    public void init() {
        create(new Rate("USD", "EUR", "0.8"));
        create(new Rate("USD", "BYR", "10500"));
        create(new Rate("EUR", "USD", "1.25"));
        create(new Rate("EUR", "BYR", "14000"));
    }

}
