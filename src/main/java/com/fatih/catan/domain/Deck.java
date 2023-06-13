package com.fatih.catan.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private final List<Card> cards = new ArrayList<>();

    public Deck() {
        for(int i = 0; i < 14; i++) {
            this.cards.add(new Card(CardType.KNIGHT));
        }
        for(int i = 0; i < 5; i++) {
            this.cards.add(new Card(CardType.VICTORYPOINT));
        }
        for(int i = 0; i < 2; i++) {
            this.cards.add(new Card(CardType.YEAROFPLENTY));
            this.cards.add(new Card(CardType.MONOPOLY));
            this.cards.add(new Card(CardType.ROADBUILDING));
        }
        Collections.shuffle(cards);
    }

    public List<Card> getCards() {
        return cards;
    }

    public Card drawCard(){
        return cards.remove(0);
    }
}
