package com.ppptcg.POKEMONTCG.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import com.ppptcg.POKEMONTCG.nonSpringclasses.BCRYPTgenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name="User",schema="UserAccess")
public class UserEntity implements Serializable {
    private String email;

    private String password;

    private boolean verified;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "ID"),
                inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<RolesEntity> roles = new ArrayList<>();
    public String getEmail() {
        return email;
    }

    public String getPassword() {
       return password;
    }

    public boolean isVerified() {
        return verified;
    }

    public int getID() {
        return ID;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        BCRYPTgenerator pp = new BCRYPTgenerator();
        this.password = pp.generatepassword(password);
    }

}
