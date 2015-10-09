package com.slinkdev.blackjack2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by stapk007 on 10/8/15.
 */
public class Deck {
    private ArrayList<Card> cards = new ArrayList<Card>();

    public Deck() {
        resetDeck();
    }

    private void resetDeck() {
        this.cards.clear();
        for (int i = 2; i < 15; i++) {
            for (int j = 0; j < 4; j++) {
                if (j==0) {
                    cards.add(new Card("Heart", i));
                } else if (j==1) {
                    cards.add(new Card("Diamond", i));
                } else if (j==2) {
                    cards.add(new Card("Spade", i));
                } else if (j==3) {
                    cards.add(new Card("Clubs", i));
                }
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
