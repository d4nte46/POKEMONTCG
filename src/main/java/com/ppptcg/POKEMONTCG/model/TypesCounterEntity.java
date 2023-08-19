package com.ppptcg.POKEMONTCG.model;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@ToString
@Table(name = "types-counter", schema = "card", catalog = "postgres")
@IdClass(com.ppptcg.POKEMONTCG.model.TypesCounterEntityPK.class)
public class TypesCounterEntity {
    @Id
    @Column(name = "cardid")
    private int cardid;

    @Id
    @Column(name = "type")
    private String type;

}
