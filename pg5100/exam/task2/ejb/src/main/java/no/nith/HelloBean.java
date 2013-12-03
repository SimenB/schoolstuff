package no.nith;

import javax.ejb.Stateless;

/**
 * @author Simen Bekkhus
 */
@Stateless(name = "HelloEJB")
public class HelloBean {
    private String name;

    public HelloBean() {
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }
}
