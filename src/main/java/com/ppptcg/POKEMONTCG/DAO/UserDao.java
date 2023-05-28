package com.ppptcg.POKEMONTCG.DAO;

import com.ppptcg.POKEMONTCG.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDao extends JpaRepository<UserEntity,Integer> {
        List<UserEntity> findAll();
}
