package com.ppptcg.POKEMONTCG.model;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
@Table(name = "energy", schema = "card", catalog = "postgres")
public class EnergyEntity {
    @Id
    @Column(name = "cardid")
    private int cardid;
    @Column(name = "name")
    private String name;

}
