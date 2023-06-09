package com.fatih.catan.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private final List<DevelopmentCard> deck;
    public Deck() {
        deck = new ArrayList<>();
        for(int i = 0; i < 14; i++){
            deck.add(DevelopmentCard.KNIGHT);
        }
        for(int i = 0; i < 5; i++) {
            deck.add(DevelopmentCard.VICTORYPOINT);
        }
        deck.add(DevelopmentCard.YEAROFPLENTY);
        deck.add(DevelopmentCard.YEAROFPLENTY);
        deck.add(DevelopmentCard.MONOPOLY);
        deck.add(DevelopmentCard.MONOPOLY);
        deck.add(DevelopmentCard.ROADBUILDING);
        deck.add(DevelopmentCard.ROADBUILDING);
        Collections.shuffle(deck);
    }

    public List<DevelopmentCard> getDeck() {
        return deck;
    }

    public DevelopmentCard drawCard(){
        return deck.remove(0);
    }
}
