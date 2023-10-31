package com.ppptcg.POKEMONTCG.DAO;

import com.ppptcg.POKEMONTCG.model.EnergyEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnergyDao extends CrudRepository<EnergyEntity,Integer> {

}
