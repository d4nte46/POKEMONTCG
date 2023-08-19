package com.ppptcg.POKEMONTCG.DAO;

import com.ppptcg.POKEMONTCG.model.PokemonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface pokemonDAO extends CrudRepository<PokemonEntity, Integer> {

}
