package no.nith.beans;

import javax.ejb.CreateException;
import javax.ejb.Init;
import javax.ejb.Remote;
import javax.ejb.Stateful;
import javax.ejb.TransactionManagement;

/**
 * @author Simen Bekkhus
 */
@Stateful
@TransactionManagement
@Remote(Counter.class)
public class CounterEJB implements Counter {
    private long startTime;

    @Override
    public long getStartTime() {
        return startTime;
    }

    @Override
    public void start() {
        this.startTime = System.currentTimeMillis();
    }

    @Override
    public void reset() {
        this.startTime = System.currentTimeMillis();
    }

    @Override
    public long getElapsedTime() {
        return System.currentTimeMillis() - this.startTime;
    }

    @Init("create")
    public void ejbCreate() throws CreateException {
    }
}
