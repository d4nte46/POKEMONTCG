package com.ppptcg.POKEMONTCG.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Package", schema = "pokemon_tcg_demo_db")
public class PackageEntity {
    @Basic
    @Column(name = "Online_Code")
    private String onlineCode;
    @Basic
    @Column(name = "Card_Set")
    private String cardSet;
    @Basic
    @Column(name = "Card_Reverse_Holofoil?")
    private boolean cardReverseHolofoil;
    @Basic
    @Column(name = "Card_Number")
    private short cardNumber;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private int id;

    public String getOnlineCode() {
        return onlineCode;
    }

    public void setOnlineCode(String onlineCode) {
        this.onlineCode = onlineCode;
    }

    public String getCardSet() {
        return cardSet;
    }

    public void setCardSet(String cardSet) {
        this.cardSet = cardSet;
    }

    public boolean isCardReverseHolofoil() {
        return cardReverseHolofoil;
    }

    public void setCardReverseHolofoil(boolean cardReverseHolofoil) {
        this.cardReverseHolofoil = cardReverseHolofoil;
    }

    public short getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(short cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PackageEntity that = (PackageEntity) o;
        return cardReverseHolofoil == that.cardReverseHolofoil && cardNumber == that.cardNumber && id == that.id && Objects.equals(onlineCode, that.onlineCode) && Objects.equals(cardSet, that.cardSet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(onlineCode, cardSet, cardReverseHolofoil, cardNumber, id);
    }
}
