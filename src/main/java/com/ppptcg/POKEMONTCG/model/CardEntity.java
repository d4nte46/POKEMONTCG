package com.ppptcg.POKEMONTCG.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Card", schema = "pokemon_tcg_demo_db")
@IdClass(CardEntityPK.class)
public class CardEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Set")
    private String set;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Reverse_Holofoil?")
    private boolean reverseHolofoil;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Number")
    private short number;
    @Basic
    @Column(name = "Extra")
    private short extra;

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

    public short getExtra() {
        return extra;
    }

    public void setExtra(short extra) {
        this.extra = extra;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardEntity that = (CardEntity) o;
        return reverseHolofoil == that.reverseHolofoil && number == that.number && extra == that.extra && Objects.equals(set, that.set);
    }

    @Override
    public int hashCode() {
        return Objects.hash(set, reverseHolofoil, number, extra);
    }
}
