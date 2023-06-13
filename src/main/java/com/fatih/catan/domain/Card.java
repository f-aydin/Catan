package com.fatih.catan.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cardID;
    private CardType type;

    public Card() {
    }

    public Card(CardType type) {
        this.type = type;
    }

    public Card(int cardID, CardType type) {
        this.cardID = cardID;
        this.type = type;
    }

    public int getCardID() {
        return cardID;
    }

    public CardType getType() {
        return type;
    }
}
