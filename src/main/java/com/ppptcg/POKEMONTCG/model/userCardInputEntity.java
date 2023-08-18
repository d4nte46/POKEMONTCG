package com.ppptcg.POKEMONTCG.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Entity
public class userCardInputEntity implements Serializable {
    @Id
    private Short id;

    @Id
    private String setId;

    @Id
    private String varietyName;

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getSetId() {
        return setId;
    }

    public void setSetId(String setName) {
        this.setId = setName;
    }

    public String getVarietyName() {
        return varietyName;
    }

    public void setVarietyName(String varietyName) {
        this.varietyName = varietyName;
    }
}
