package no.nith.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import java.io.Serializable;

/**
 * @author Simen Bekkhus
 */
@Entity
public class Count implements Serializable {
    @Id
    @GeneratedValue
    private long id;
    private long time;

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public long getTime() {
        return time;
    }

    public void setTime(final long time) {
        this.time = time;
    }
}
