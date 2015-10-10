package com.slinkdev.blackjack2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.concurrent.TimeUnit;

public class Main extends AppCompatActivity {

    private Deck mainDeck = new Deck();
    private Hand userHand = new Hand();
    private Hand cpuHand = new Hand();
    private int userScore;
    private int cpuScore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView userTextView = (TextView) findViewById(R.id.userScore);
        TextView cpuTextView = (TextView) findViewById(R.id.cpuScore);
        TextView userCardView = (TextView) findViewById(R.id.userCard);
        TextView cpuCardView = (TextView) findViewById(R.id.cpuCard);
        Button hitMeButton = (Button) findViewById(R.id.hitMeButton);
        Button stayButton = (Button) findViewById((R.id.stayButton));
        Button dealButton = (Button) findViewById(R.id.dealButton);

        userHand.setCardsView(userCardView);
        cpuHand.setCardsView(cpuCardView);
        userTextView.setText(String.valueOf(userScore));
        cpuTextView.setText(String.valueOf(cpuScore));

        hitMeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hitMe(userHand);
                if (isScoreOver21(userHand)) {
                    userStays();
                }
            }
        });
        stayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userStays();
            }
        });
        dealButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTable();
            }
        });

        userScore = 0;
        cpuScore = 0;
        resetTable();
    }

    private void resetTable() {
        mainDeck = new Deck();
        mainDeck.shuffleDeck();
        userHand.emptyHand();
        cpuHand.emptyHand();
        userHand.clearTextView();
        cpuHand.clearTextView();
        Card card1 = mainDeck.drawCard();
        Card card2 = mainDeck.drawCard();
        Card card3 = mainDeck.drawCard();
        Card card4 = mainDeck.drawCard();
        userHand.addCard(card1);
        addCardToTextView(card1, userHand);
        userHand.addCard(card2);
        addCardToTextView(card2, userHand);
        cpuHand.addCard(card3);
        addCardToTextView(card3, cpuHand);
        cpuHand.addCard(card4);
        addCardToTextView(card4, cpuHand);
        enableHitMe();
        enableStay();
        disableDeal();
    }

    private void hitMe(Hand x) {
        Card cardToAdd = mainDeck.drawCard();
        x.addCard(cardToAdd);
        addCardToTextView(cardToAdd, x);
        if (isScoreOver21(x)) {
            disableHitMe();
            disableStay();
        }
    }

    private void addCardToTextView(Card cardInput, Hand handInput) {
        handInput.getCardsView().append("\n" + cardInput.getFullName());
    }

    private boolean isScoreOver21(Hand x) {
        if (x.getScore() > 21) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isScoreOver15(Hand x) {
        if (x.getScore() > 15) {
            return true;
        } else {
            return false;
        }
    }

    private void userStays() {
        disableHitMe();
        disableStay();
        while (!isScoreOver15(cpuHand)) {
            hitMe(cpuHand);
        }
        Hand winner = whoWon(userHand, cpuHand);
        if (winner == userHand) {
            //User Won
            //Add Score to user
            //Display pop up saying congrats
        } else {
            //CPU won
            //Add Score to CPU
            //Display pop up saying sorry
        }
        enableDeal();
    }

    private Hand whoWon(Hand user, Hand cpu) {
        if (user.getScore() > 21) {
            return cpu;
        } else if (cpu.getScore() > 21) {
            return user;
        } else if (cpu.getScore() >= user.getScore()) {
            return cpu;
        } else if (user.getScore() > cpu.getScore()) {
            return user;
        } else {
            Log.d("Main.java", "Can't decide who won");
            return cpu;
        }
    }

    private void disableHitMe() {
        Button hitMeButton = (Button) findViewById(R.id.hitMeButton);
        hitMeButton.setEnabled(false);
    }
    private void enableHitMe() {
        Button hitMeButton = (Button) findViewById(R.id.hitMeButton);
        hitMeButton.setEnabled(true);
    }
    private void disableStay() {
        Button stayButton = (Button) findViewById((R.id.stayButton));
        stayButton.setEnabled(false);
    }
    private void enableStay() {
        Button stayButton = (Button) findViewById((R.id.stayButton));
        stayButton.setEnabled(true);
    }
    private void disableDeal() {
        Button dealButton = (Button) findViewById(R.id.dealButton);
        dealButton.setEnabled(false);
    }
    private void enableDeal(){
        Button dealButton = (Button) findViewById(R.id.dealButton);
        dealButton.setEnabled(true);
    }

    private void addScoretoUser(){
        userScore += 1;
    }

    private void addScoreToCpu() {
        cpuScore += 1;
    }
}
