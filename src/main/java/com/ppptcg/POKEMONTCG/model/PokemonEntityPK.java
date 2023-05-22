package com.ppptcg.POKEMONTCG.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class PokemonEntityPK implements Serializable {
    @Column(name = "Card_Set")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String cardSet;
    @Column(name = "Card_Number")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private short cardNumber;
    @Column(name = "Card_Reverse_Holofoil?")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private boolean cardReverseHolofoil;

    public String getCardSet() {
        return cardSet;
    }

    public void setCardSet(String cardSet) {
        this.cardSet = cardSet;
    }

    public short getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(short cardNumber) {
        this.cardNumber = cardNumber;
    }

    public boolean isCardReverseHolofoil() {
        return cardReverseHolofoil;
    }

    public void setCardReverseHolofoil(boolean cardReverseHolofoil) {
        this.cardReverseHolofoil = cardReverseHolofoil;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PokemonEntityPK that = (PokemonEntityPK) o;
        return cardNumber == that.cardNumber && cardReverseHolofoil == that.cardReverseHolofoil && Objects.equals(cardSet, that.cardSet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardSet, cardNumber, cardReverseHolofoil);
    }
}
