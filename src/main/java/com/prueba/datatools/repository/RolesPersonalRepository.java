package com.prueba.datatools.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.prueba.datatools.entities.RolesPersonal;

@Repository
public interface RolesPersonalRepository extends JpaRepository<RolesPersonal,Long> {

}
