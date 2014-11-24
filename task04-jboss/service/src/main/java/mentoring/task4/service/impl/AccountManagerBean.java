package mentoring.task4.service.impl;

import mentoring.task4.domain.Account;
import mentoring.task4.domain.Deposit;
import mentoring.task4.domain.Rate;
import mentoring.task4.repository.impl.AccountDb;
import mentoring.task4.service.api.IAccountManagerLocal;
import mentoring.task4.service.api.IAccountManagerRemote;
import mentoring.task4.service.api.IRateManagerLocal;
import org.apache.log4j.Logger;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;


@Stateless
@DeclareRoles({"admin", "client"})
public class AccountManagerBean implements IAccountManagerLocal, IAccountManagerRemote {

    private Logger logger = Logger.getLogger(this.getClass());

    @EJB
    private AccountDb repository;
    @EJB
    private IRateManagerLocal rateManagerLocal;

    @Override
    @RolesAllowed("admin")
    public void add(String passp, String userId, String name, String phone, String addr) {
        logger.debug("Adding new acc for user: " + userId);
        Account account = new Account();
        account.setId(UUID.randomUUID().toString());
        account.setPassp(passp);
        account.setUserId(userId);
        account.setName(name);
        account.setPhone(phone);
        account.setAddress(addr);
        repository.create(enrichWithDeposit(account));
    }

    private Account enrichWithDeposit(Account account) {
        List<Deposit> deposits = new LinkedList<>();
        Deposit deposit = new Deposit("USD", "100");
        deposits.add(deposit);
        account.setDeposits(deposits);
        return account;
    }

    @Override
    @RolesAllowed("admin")
    public Account get(String id) {
        return repository.read(id);
    }

    @Override
    @RolesAllowed("admin")
    public void update(String id, String passp, String userId, String name, String phone, String addr) {
        logger.debug("Updating account: " + id);
        Account acc = get(id);
        if (acc != null) {
            acc.setPassp(passp);
            acc.setUserId(userId);
            acc.setName(name);
            acc.setPhone(phone);
            acc.setAddress(addr);
            repository.update(acc);
        }
    }

    @Override
    @RolesAllowed("admin")
    public void remove(String id) {
        logger.debug("Removing account: " + id);
        repository.delete(id);
    }

    @Override
    @RolesAllowed("admin")
    public Collection<Account> findAll() {
        return repository.findAll();
    }

    @Override
    @RolesAllowed({"client"})
    public Account findByUser(String userId) {
        return repository.findByUser(userId);
    }

    @Override
    @RolesAllowed({"client"})
    public void doExchange(Account account, String from, String to, BigDecimal summ) {
        logger.debug("Doing exchange for account: " + account.getId());
        String id = new Rate(from, to, "1").constructId();
        Rate rate = rateManagerLocal.get(id);
        List<Deposit> deposits = account.getDeposits();
        Deposit fromDeposit = findDeposit(account, from);
        Deposit toDeposit = findDeposit(account, to);
        if (toDeposit == null) {
            toDeposit = new Deposit(to, "0");
            deposits.add(toDeposit);
        }
        fromDeposit.setAmount(fromDeposit.getAmount().subtract(summ));
        BigDecimal result = summ.multiply(rate.getRate());
        toDeposit.setAmount(toDeposit.getAmount().add(result));
        repository.update(account);
    }

    @Override
    @RolesAllowed({"client"})
    public Deposit findDeposit(Account account, String from) {
        for (Deposit deposit : account.getDeposits()) {
            if (deposit.getType().equalsIgnoreCase(from)) {
                return deposit;
            }
        }
        return null;
    }

    void setRepository(AccountDb repository) {
        this.repository = repository;
    }

    void setRateManagerLocal(IRateManagerLocal rateManagerLocal) {
        this.rateManagerLocal = rateManagerLocal;
    }
}
