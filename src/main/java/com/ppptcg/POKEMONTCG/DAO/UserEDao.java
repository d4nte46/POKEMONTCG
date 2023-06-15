package com.ppptcg.POKEMONTCG.DAO;

import com.ppptcg.POKEMONTCG.model.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserEDao extends CrudRepository<UserEntity,String> {
    @Query("SELECT ue FROM UserEntity ue WHERE ue.email = :email")
    Optional<UserEntity> findByEmail(String email);
}
