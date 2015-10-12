package com.slinkdev.blackjack2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Main extends AppCompatActivity {

    private Deck mainDeck = new Deck();
    private Hand userHand = new Hand();
    private Hand cpuHand = new Hand();
    private int gamesWon;
    private int gamesPlayed;
    private TextView gamesWonValue;
    private TextView gamesPlayedValue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView userScoreView = (TextView) findViewById(R.id.userValue);
        TextView cpuScoreView = (TextView) findViewById(R.id.cpuValue);
        TextView userCardView = (TextView) findViewById(R.id.userCard);
        TextView cpuCardView = (TextView) findViewById(R.id.cpuCard);
        gamesWonValue = (TextView) findViewById(R.id.gamesWonValue);
        gamesPlayedValue = (TextView) findViewById(R.id.gamesPlayedValue);
        Button hitMeButton = (Button) findViewById(R.id.hitMeButton);
        Button stayButton = (Button) findViewById((R.id.stayButton));
        Button dealButton = (Button) findViewById(R.id.dealButton);

        userHand.setCardsView(userCardView);
        userHand.setValueView(userScoreView);
        cpuHand.setCardsView(cpuCardView);
        cpuHand.setValueView(cpuScoreView);
        gamesWonValue.setText("0");
        gamesPlayedValue.setText("0");
        userScoreView.setText(String.valueOf(userHand.getHandValue()));
        cpuScoreView.setText(String.valueOf(cpuHand.getHandValue()));

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

        gamesPlayed = 0;
        gamesWon = 0;
        resetTable();
    }

    private void resetTable() {
        mainDeck = new Deck();
        mainDeck.shuffleDeck();
        userHand.emptyHand();
        cpuHand.emptyHand();
        userHand.clearCardsView();
        userHand.clearValueView();
        cpuHand.clearCardsView();
        cpuHand.clearValueView();
        Card card1 = mainDeck.drawCard();
        Card card2 = mainDeck.drawCard();
        Card card3 = mainDeck.drawCard();
        userHand.addCard(card1);
        addCardToTextView(card1, userHand);
        userHand.addCard(card2);
        addCardToTextView(card2, userHand);
        cpuHand.addCard(card3);
        addCardToTextView(card3, cpuHand);
        userHand.refreshValueView();
        cpuHand.refreshValueView();
        enableHitMe();
        enableStay();
        disableDeal();
    }

    private void hitMe(Hand x) {
        Card cardToAdd = mainDeck.drawCard();
        x.addCard(cardToAdd);
        addCardToTextView(cardToAdd, x);
        x.refreshValueView();
        if (isScoreOver21(x)) {
            disableHitMe();
            disableStay();
        }
    }

    private void addCardToTextView(Card cardInput, Hand handInput) {
        handInput.getCardsView().append("\n" + cardInput.getFullName());
    }

    private boolean isScoreOver21(Hand x) {
        return (x.getHandValue() > 21);
    }

    private boolean isScoreOver16(Hand x) {
        return (x.getHandValue() > 16);
    }

    private void userStays() {
        disableHitMe();
        disableStay();
        while (!isScoreOver16(cpuHand)) {
            hitMe(cpuHand);
        }
        Hand winner = whoWon(userHand, cpuHand);
        if (winner == userHand) {
            //User Won
            gamesWon++;
            //Display pop up saying congrats
        } else {
            //CPU won
            //Display pop up saying sorry
        }
        gamesPlayed++;
        gamesWonValue.setText(String.valueOf(gamesWon));
        gamesPlayedValue.setText(String.valueOf(gamesPlayed));
        enableDeal();
    }

    private Hand whoWon(Hand user, Hand cpu) {
        if (user.getHandValue() > 21) {
            return cpu;
        } else if (cpu.getHandValue() > 21) {
            return user;
        } else if (cpu.getHandValue() >= user.getHandValue()) {
            return cpu;
        } else if (user.getHandValue() > cpu.getHandValue()) {
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
}
