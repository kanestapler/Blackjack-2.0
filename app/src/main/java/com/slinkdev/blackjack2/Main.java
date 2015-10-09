package com.slinkdev.blackjack2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Main extends AppCompatActivity {

    private Deck mainDeck;
    private Hand userHand;
    private Hand cpuHand;
    private int userScore;
    private int cpuScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    private void resetTable() {
        mainDeck = new Deck();
        userHand.addCard();
        userHand.addCard();
        cpuHand.addCard();
        cpuHand.addCard();
    }

    private boolean hitMe(Hand x) {
        x.addCard();
        if (x.getScore() > 21) {
            return false;
        } else {
            return true;
        }
    }

    private Hand calculatingEndGame() {
        if (userHand.getScore() > 21) {
            return cpuHand;
        } else if (cpuHand.getScore() > 21) {
            return userHand;
        } else if (cpuHand.getScore() >= userHand.getScore()) {
            return cpuHand;
        } else if (userHand.getScore() >)
    }

    private void addScoretoUser(){
        userScore += 1;
    }

    private void addScoreToCpu() {
        cpuScore += 1;
    }
}
