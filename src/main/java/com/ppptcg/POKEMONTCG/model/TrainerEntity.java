package com.ppptcg.POKEMONTCG.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Trainer", schema = "pokemon_tcg_demo_db")
@IdClass(TrainerEntityPK.class)
public class TrainerEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Card_Set")
    private String cardSet;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Card_Number")
    private short cardNumber;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Card_Reverse_Holofoil?")
    private boolean cardReverseHolofoil;
    @Basic
    @Column(name = "Name")
    private String name;
    @Basic
    @Column(name = "Type")
    private String type;
    @Basic
    @Column(name = "Illustator")
    private String illustator;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIllustator() {
        return illustator;
    }

    public void setIllustator(String illustator) {
        this.illustator = illustator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrainerEntity that = (TrainerEntity) o;
        return cardNumber == that.cardNumber && cardReverseHolofoil == that.cardReverseHolofoil && Objects.equals(cardSet, that.cardSet) && Objects.equals(name, that.name) && Objects.equals(type, that.type) && Objects.equals(illustator, that.illustator);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardSet, cardNumber, cardReverseHolofoil, name, type, illustator);
    }
}
