package com.ppptcg.POKEMONTCG.model;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
@Table(name = "pokemon", schema = "card", catalog = "postgres")
@javax.persistence.IdClass(com.ppptcg.POKEMONTCG.model.pokemonEntityPK.class)
public class PokemonEntity {

    @Id
    @Column(name = "cardid")
    private int cardid;

    @Id
    @Column(name = "nationaldexid")
    private short nationaldexid;

}
