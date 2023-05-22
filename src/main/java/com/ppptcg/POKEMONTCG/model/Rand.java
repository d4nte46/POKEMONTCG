package com.ppptcg.POKEMONTCG.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Rand implements Serializable {
    @Id
    private Integer ID;

    @Id
    private boolean reverseholofoil;

    @Id
    private String packname;


    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public boolean isReverseholofoil() {
        return reverseholofoil;
    }

    public void setReverseholofoil(boolean reverseholofoil) {
        this.reverseholofoil = reverseholofoil;
    }

    public String getPackname() {
        return packname;
    }

    public void setPackname(String packname) {
        this.packname = packname;
    }

    @Override
    public String toString() {
        return "Rand{" +
                "ID=" + ID +
                ", reverseholofoil=" + reverseholofoil +
                ", packname='" + packname + '\'' +
                '}';
    }
}
