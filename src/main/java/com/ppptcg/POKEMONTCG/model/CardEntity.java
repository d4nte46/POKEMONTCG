package com.ppptcg.POKEMONTCG.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import net.bytebuddy.build.ToStringPlugin;

import javax.persistence.*;
import java.util.Objects;
@ToString
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "card", schema = "card", catalog = "postgres")
public class CardEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "setid")
    private String setid;
    @Basic
    @Column(name = "setnumber")
    private short setnumber;
    @Basic
    @Column(name = "illustrator")
    private String illustrator;
    @Basic
    @Column(name = "rarity")
    private String rarity;

    public void setId(int id) {
        this.id = id;
    }

    public void setSetid(String setid) {
        this.setid = setid;
    }

    public void setSetnumber(short setnumber) {
        this.setnumber = setnumber;
    }

    public void setIllustrator(String illustrator) {
        this.illustrator = illustrator;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }


    public CardEntity(String setid, short setnumber, String illustrator, String rarity) {
        this.setid = setid;
        this.setnumber = setnumber;
        this.illustrator = illustrator;
        this.rarity = rarity;
    }

}
