package com.ppptcg.POKEMONTCG.DAO;

import com.ppptcg.POKEMONTCG.model.TrainerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainerDAO extends CrudRepository<TrainerEntity,Integer> {

}
