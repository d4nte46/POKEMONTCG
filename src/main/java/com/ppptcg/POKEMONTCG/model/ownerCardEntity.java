package com.ppptcg.POKEMONTCG.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@ToString
@javax.persistence.Table(name = "ownercard", schema = "owner", catalog = "postgres")
@javax.persistence.IdClass(com.ppptcg.POKEMONTCG.model.OwnercardEntityPK.class)
public class ownerCardEntity implements Serializable {

    @Id
    @javax.persistence.Column(name = "userid")
    private String userid;

    @Id
    @javax.persistence.Column(name = "cardid")
    private int cardid;

}
