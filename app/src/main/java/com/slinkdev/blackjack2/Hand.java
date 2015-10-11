package com.slinkdev.blackjack2;

import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by stapk007 on 10/8/15.
 */
public class Hand {
    private TextView cardsView;
    private TextView scoreView;
    private ArrayList<Card> cards = new ArrayList<>();

    public Hand() {
        cards.clear();
    }

    public void emptyHand() {
        cards.clear();
    }

    public TextView getCardsView() {
        return cardsView;
    }

    public void setCardsView(TextView cardsView) {
        this.cardsView = cardsView;
    }

    public void addCard(Card x) {
        cards.add(x);
    }

    public void clearCardsView() {
        cardsView.setText("");
    }

    public void clearScoreView() {
        scoreView.setText("");
    }

    //Calculates what the best score is to return automatically (Only matters if there is an ace)
    public int getScore() {
        int numOfAces = 0;
        for (int i = 0; i < cards.size(); i++) {//Loop through each card checking for ace
            if (cards.get(i).getValue() == 11) {//if value == 14 then it is an ace
                numOfAces++;
            }
        }
        return calcWithXNumOfAces(numOfAces);
    }

    private int calcWithXNumOfAces(int acesInput) {
        int aces = acesInput;
        int score = 0;
        for (int i=0; i < cards.size(); i++) {
            score += cards.get(i).getValue();
        }
        while (aces > 0) {
            if (score > 21) {
                score -= 10;
                aces--;
            } else {
                break;
            }
        }
        return score;
    }

    public void setScoreView(TextView scoreView) {
        this.scoreView = scoreView;
    }

    public void refreshScoreView() {
        this.scoreView.setText(String.valueOf(getScore()));
    }
}
