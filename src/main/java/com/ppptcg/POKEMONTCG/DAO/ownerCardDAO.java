package com.ppptcg.POKEMONTCG.DAO;

import com.ppptcg.POKEMONTCG.model.ownerCardEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ownerCardDAO extends CrudRepository<ownerCardEntity,Integer> {

}
