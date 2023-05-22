package com.ppptcg.POKEMONTCG.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Pokemon", schema = "pokemon_tcg_demo_db")
@IdClass(PokemonEntityPK.class)
public class PokemonEntity {
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
    @Column(name = "ID")
    private short id;
    @Basic
    @Column(name = "Type")
    private String type;
    @Basic
    @Column(name = "Special Modifier")
    private String specialModifier;
    @Basic
    @Column(name = "Illustrator")
    private String illustrator;

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

    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSpecialModifier() {
        return specialModifier;
    }

    public void setSpecialModifier(String specialModifier) {
        this.specialModifier = specialModifier;
    }

    public String getIllustrator() {
        return illustrator;
    }

    public void setIllustrator(String illustrator) {
        this.illustrator = illustrator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PokemonEntity that = (PokemonEntity) o;
        return cardNumber == that.cardNumber && cardReverseHolofoil == that.cardReverseHolofoil && id == that.id && Objects.equals(cardSet, that.cardSet) && Objects.equals(type, that.type) && Objects.equals(specialModifier, that.specialModifier) && Objects.equals(illustrator, that.illustrator);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardSet, cardNumber, cardReverseHolofoil, id, type, specialModifier, illustrator);
    }
}
