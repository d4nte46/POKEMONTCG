package com.ppptcg.POKEMONTCG.DAO;

import com.ppptcg.POKEMONTCG.model.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserDao extends CrudRepository<UserEntity,String> {
}
