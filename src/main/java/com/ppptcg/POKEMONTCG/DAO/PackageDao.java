package com.ppptcg.POKEMONTCG.DAO;

import com.ppptcg.POKEMONTCG.model.CardsetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PackageDao extends JpaRepository<CardsetEntity,Integer> {
    List<CardsetEntity> findAll();
}
