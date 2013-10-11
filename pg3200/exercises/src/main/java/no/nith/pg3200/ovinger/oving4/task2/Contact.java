package no.nith.pg3200.ovinger.oving4.task2;

import java.util.List;

public class Contact {
    private String name;
    private List<String> numbers, emails;

    public Contact(final String name, List<String> numbers, List<String> emails) {
        this.name = name;
        this.numbers = numbers;
        this.emails = emails;
    }

    public String getName() {
        return name;
    }

    public List<String> getNumbers() {
        return numbers;
    }

    public List<String> getEmails() {
        return emails;
    }

    @Override
    public String toString() {
        final String number = numbers != null && numbers.size() > 0 ? " - " + numbers.get(0) : "",
                email = emails != null && emails.size() > 0 ? " - " + emails.get(0) : "";

        return this.name + number + email;
    }
}
