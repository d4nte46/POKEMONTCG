package com.ppptcg.POKEMONTCG.model;

import lombok.*;
import net.bytebuddy.build.ToStringPlugin;

import javax.persistence.*;
import java.util.Objects;
@ToString
@Getter
@Setter
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
    @Basic
    @Column(name = "variety")
    private String variety;


    public CardEntity(String setid, short setnumber, String illustrator, String rarity,String variety) {
        this.setid = setid;
        this.setnumber = setnumber;
        this.illustrator = illustrator;
        this.rarity = rarity;
        this.variety = variety;

    }

}
