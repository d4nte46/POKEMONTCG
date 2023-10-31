package com.ppptcg.POKEMONTCG.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@ToString
@Table(name = "ownercard", schema = "owner", catalog = "postgres")
@IdClass(com.ppptcg.POKEMONTCG.model.OwnercardEntityPK.class)
public class ownerCardEntity implements Serializable {

    @Id
    @Column(name = "userid")
    private String userid;

    @Id
    @Column(name = "cardid")
    private int cardid;

}
