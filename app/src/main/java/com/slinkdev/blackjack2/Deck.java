package com.slinkdev.blackjack2;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by stapk007 on 10/8/15.
 */
public class Deck {
    private ArrayList<Card> cards = new ArrayList<>();

    public Deck() {
        resetDeck();
    }

    private void resetDeck() {
        this.cards.clear();
        for (int i = 2; i < 15; i++) {//Loop for each value
            for (int j = 0; j < 4; j++) {//Loop for each suite
                if (j==0) {
                    addCard("Hearts", i);
                } else if (j==1) {
                    addCard("Diamonds", i);
                } else if (j==2) {
                    addCard("Spades", i);
                } else if (j==3) {
                    addCard("Clubs", i);
                }
            }
        }
        shuffleDeck();
    }

    private void addCard(String suiteInput, int valueInput) {
        if (valueInput < 2) {
            Log.d("Deck.java", "valueInput < 2");
        } else if (valueInput < 11) {
            cards.add(new Card(suiteInput, valueInput, (String.valueOf(valueInput) + " of " + suiteInput)));
        } else {
            if (valueInput == 11) {
                cards.add(new Card(suiteInput, 10, ("Jack of " + suiteInput)));
            } else if (valueInput == 12) {
                cards.add(new Card(suiteInput, 10, ("Queen of " + suiteInput)));
            } else if (valueInput == 13) {
                cards.add(new Card(suiteInput, 10, ("King of " + suiteInput)));
            } else if (valueInput == 14) {
                cards.add(new Card(suiteInput, 11, ("Ace of " + suiteInput)));
            } else {
                Log.d("Deck.java", "valueInput > 14");
            }
        }
    }

    public Card drawCard() {
        Card tempCard = cards.get(0);
        cards.remove(0);
        return tempCard;
    }

    public void shuffleDeck() {
        Collections.shuffle(this.cards);
    }
}
