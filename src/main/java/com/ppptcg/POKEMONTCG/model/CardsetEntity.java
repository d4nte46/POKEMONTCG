package com.ppptcg.POKEMONTCG.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cardset", schema = "card", catalog = "postgres")
public class CardsetEntity {
    @Id
    @Column(name = "id")
    private String id;
    @Basic
    @Column(name = "cardset")
    private String cardset;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCardset() {
        return cardset;
    }

    public void setCardset(String cardset) {
        this.cardset = cardset;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardsetEntity that = (CardsetEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(cardset, that.cardset);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cardset);
    }

}
