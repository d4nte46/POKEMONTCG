package com.ppptcg.POKEMONTCG.DAO;

import com.ppptcg.POKEMONTCG.model.Package;
import com.ppptcg.POKEMONTCG.model.TableNameIdEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

public interface PackageDao extends JpaRepository<TableNameIdEntity,Integer> {
    List<TableNameIdEntity> findAll();
}
