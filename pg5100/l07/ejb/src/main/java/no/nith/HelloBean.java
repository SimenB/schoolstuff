package no.nith;

import javax.ejb.Stateless;

/**
 * @author Simen Bekkhus
 */
@Stateless(name = "HelloBeanEJB")
public class HelloBean {
    public HelloBean() {
    }

    public String Hello() {
        return "Martin greier ingenting";
    }
}
