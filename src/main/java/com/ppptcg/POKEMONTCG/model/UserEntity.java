package com.ppptcg.POKEMONTCG.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity

@Table(name="User",schema="UserAccess")
public class UserEntity implements Serializable {
    private String email;

    private String password;

    private boolean verified;

    @Id
    private boolean ID;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public boolean isVerified() {
        return verified;
    }

    public boolean isID() {
        return ID;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
