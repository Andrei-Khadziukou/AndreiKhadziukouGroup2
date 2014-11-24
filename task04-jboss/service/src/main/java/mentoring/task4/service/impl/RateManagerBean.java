package mentoring.task4.service.impl;

import mentoring.task4.domain.Currency;
import mentoring.task4.domain.Rate;
import mentoring.task4.repository.impl.RateDb;
import mentoring.task4.service.api.ICurrencyManagerLocal;
import mentoring.task4.service.api.IRateManagerLocal;
import mentoring.task4.service.api.IRateManagerRemote;
import org.apache.log4j.Logger;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedList;


@Stateless
@DeclareRoles({"admin", "client"})
public class RateManagerBean implements IRateManagerLocal, IRateManagerRemote {

    private Logger logger = Logger.getLogger(this.getClass());

    @EJB
    private RateDb repository;
    @EJB
    private ICurrencyManagerLocal currencyManagerLocal;

    @Override
    @RolesAllowed("admin")
    public void add(String from, String to, String rate) {
        Rate value = new Rate(from, to, rate);
        logger.debug("Adding rate:" + value);
        repository.create(value);
    }

    @Override
    @RolesAllowed({"admin", "client"})
    public Rate get(String id) {
        return repository.read(id);
    }

    @Override
    @RolesAllowed("admin")
    public void update(String id, String newRate) {
        logger.debug("Removing rate: " + id);
        Rate rate = get(id);
        if (rate != null) {
            rate.setRate(new BigDecimal(newRate));
            repository.update(rate);
        }
    }

    @Override
    @RolesAllowed("admin")
    public void remove(String id) {
        repository.delete(id);
    }

    @Override
    @RolesAllowed({"admin", "client"})
    public Collection<Rate> findAll() {
        return repository.findAll();
    }

    @Override
    @RolesAllowed("client")
    public Collection<Rate> getAvailRates(String from) {
        Collection<Rate> ratesAvail = new LinkedList<>();
        Collection<Rate> rates = findAll();
        for (Rate rate : rates) {
            if (rate.getFrom().equalsIgnoreCase(from)) {
                ratesAvail.add(rate);
            }
        }
        return ratesAvail;
    }

    @Override
    @RolesAllowed("client")
    public Collection<Currency> getAvailCurrencies(String from) {
        Collection<Currency> currsAvail = new LinkedList<>();
        Collection<Rate> rates = findAll();
        for (Rate rate : rates) {
            if (rate.getFrom().equalsIgnoreCase(from)) {
                currsAvail.add(currencyManagerLocal.get(rate.getTo()));
            }
        }
        return currsAvail;
    }

    void setRepository(RateDb repository) {
        this.repository = repository;
    }

    void setCurrencyManagerLocal(ICurrencyManagerLocal currencyManagerLocal) {
        this.currencyManagerLocal = currencyManagerLocal;
    }
}
