package com.ppptcg.POKEMONTCG.DAO;

import com.ppptcg.POKEMONTCG.model.TypesCounterEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface typesDAO extends CrudRepository<TypesCounterEntity,Integer> {

}
