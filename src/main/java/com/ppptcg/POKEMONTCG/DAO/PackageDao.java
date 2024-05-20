package com.ppptcg.POKEMONTCG.DAO;

import com.ppptcg.POKEMONTCG.model.CardsetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PackageDao extends JpaRepository<CardsetEntity,Integer> {
    List<CardsetEntity> findAll();
    @Query("SELECT cd FROM CardsetEntity cd WHERE cd.cardset = :name")
    CardsetEntity findByName(String name);

    @Query("SELECT cd FROM CardsetEntity cd WHERE cd.id = :id")
    CardsetEntity findById(String id);
}
