package com.ppptcg.POKEMONTCG.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class CardEntityPK implements Serializable {
    @Column(name = "Set")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String set;
    @Column(name = "Reverse_Holofoil?")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private boolean reverseHolofoil;
    @Column(name = "Number")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private short number;

    public String getSet() {
        return set;
    }

    public void setSet(String set) {
        this.set = set;
    }

    public boolean isReverseHolofoil() {
        return reverseHolofoil;
    }

    public void setReverseHolofoil(boolean reverseHolofoil) {
        this.reverseHolofoil = reverseHolofoil;
    }

    public short getNumber() {
        return number;
    }

    public void setNumber(short number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardEntityPK that = (CardEntityPK) o;
        return reverseHolofoil == that.reverseHolofoil && number == that.number && Objects.equals(set, that.set);
    }

    @Override
    public int hashCode() {
        return Objects.hash(set, reverseHolofoil, number);
    }
}
