package com.ppptcg.POKEMONTCG.model;

import com.ppptcg.POKEMONTCG.nonSpringclasses.RandomString;
import lombok.Data;

import com.ppptcg.POKEMONTCG.nonSpringclasses.BCRYPTgenerator;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name="consumer",schema = "user_access")
public class UserEntity implements Serializable {

    private String email;

    private String password;

    private boolean verified;

    @Id
    @Column(name = "id")
    private String ID;

    private String verification_code;
    public String getEmail() {
        return email;
    }

    public String getPassword() {
       return password;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setID(){
        RandomString randomString = new RandomString(13);
        this.ID = randomString.generate();
    }
    public String getID() {
        return ID;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        BCRYPTgenerator pp = new BCRYPTgenerator();
        this.password = pp.generatepassword(password);
    }

    public void setvericode(){
        RandomString randomString = new RandomString(13);
        this.verification_code = randomString.generate();
    }
}
