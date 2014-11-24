package mentoring.task4.service.impl;

import mentoring.task4.domain.Currency;
import mentoring.task4.repository.impl.CurrencyDb;
import mentoring.task4.service.api.ICurrencyManagerLocal;
import mentoring.task4.service.api.ICurrencyManagerRemote;
import org.apache.log4j.Logger;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.Collection;


@Stateless
@DeclareRoles({"admin", "client"})
public class CurrencyManagerBean implements ICurrencyManagerLocal, ICurrencyManagerRemote {

    private Logger logger = Logger.getLogger(this.getClass());

    @EJB
    private CurrencyDb repository;

    @Override
    @RolesAllowed("admin")
    public void add(String cur) {
        logger.debug("Adding currency: " + cur);
        repository.create(new Currency(cur));
    }

    @Override
    @RolesAllowed({"admin", "client"})
    public Currency get(String cur) {
        return repository.read(cur);
    }

    @Override
    @RolesAllowed("admin")
    public void remove(String cur) {
        logger.debug("Removing currency: " + cur);
        repository.delete(cur);
    }

    @Override
    @RolesAllowed({"admin", "client"})
    public Collection<Currency> findAll() {
        return repository.findAll();
    }

    void setRepository(CurrencyDb repository) {
        this.repository = repository;
    }
}
