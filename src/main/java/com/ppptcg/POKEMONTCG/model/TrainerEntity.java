package com.ppptcg.POKEMONTCG.model;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "trainer", schema = "card", catalog = "postgres")
@IdClass(com.ppptcg.POKEMONTCG.model.TrainerEntityPK.class)
public class TrainerEntity {

    @Id
    @javax.persistence.Column(name = "cardid")
    private int cardid;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @javax.persistence.Column(name = "subtype")
    private String subtype;

    @Basic
    @Column(name = "name")
    private String name;

}
