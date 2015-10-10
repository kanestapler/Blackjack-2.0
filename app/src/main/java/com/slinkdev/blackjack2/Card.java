package com.slinkdev.blackjack2;

/**
 * Created by stapk007 on 10/8/15.
 */
public class Card {
    private String suite;
    private int value;
    private String fullName;

    public Card(String suiteInput, int valueInput, String fullNameInput) {
        this.suite = suiteInput;
        this.value = valueInput;
        this.fullName = fullNameInput;
    }

    public String getSuite() {
        return suite;
    }
    public int getValue() {
        return value;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
