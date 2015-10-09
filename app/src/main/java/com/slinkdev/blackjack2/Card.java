package com.slinkdev.blackjack2;

/**
 * Created by stapk007 on 10/8/15.
 */
public class Card {
    private String suite;
    private int value;

    public Card(String suiteInput, int valueInput) {
        this.suite = suiteInput;
        this.value = valueInput;
    }

    public String getSuite() {
        return suite;
    }
    public int getValue() {
        return value;
    }
}
