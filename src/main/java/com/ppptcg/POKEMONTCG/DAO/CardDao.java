package com.ppptcg.POKEMONTCG.DAO;

import com.ppptcg.POKEMONTCG.model.CardEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardDao extends CrudRepository<CardEntity,Integer> {

}
