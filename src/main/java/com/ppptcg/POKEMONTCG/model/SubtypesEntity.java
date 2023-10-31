package com.ppptcg.POKEMONTCG.model;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Entity
@Table(name = "subtypes", schema = "card", catalog = "postgres")
@IdClass(com.ppptcg.POKEMONTCG.model.SubtypesEntityPK.class)
public class SubtypesEntity {

    @Id
    @Column(name = "cardid")
    private int cardid;

    @Id
    @Column(name = "subtype")
    private String subtype;
}

