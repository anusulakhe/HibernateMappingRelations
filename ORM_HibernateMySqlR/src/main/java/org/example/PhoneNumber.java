package org.example;

import jakarta.persistence.Embeddable;

@Embeddable
public class PhoneNumber {

    private String type;
    private String number;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
