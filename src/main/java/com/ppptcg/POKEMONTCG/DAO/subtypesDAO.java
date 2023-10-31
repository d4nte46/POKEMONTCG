package com.ppptcg.POKEMONTCG.DAO;

import com.ppptcg.POKEMONTCG.model.SubtypesEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.sql.rowset.CachedRowSet;
@Repository
public interface subtypesDAO extends CrudRepository<SubtypesEntity,Integer> {
}
