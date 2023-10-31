package com.ppptcg.POKEMONTCG.DAO;

import com.ppptcg.POKEMONTCG.model.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserIDDao extends CrudRepository<UserEntity,String> {
}
