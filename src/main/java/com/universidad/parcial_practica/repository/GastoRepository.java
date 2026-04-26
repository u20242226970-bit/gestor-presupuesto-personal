package com.universidad.parcial_practica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.universidad.parcial_practica.model.Gasto;


@Repository

public interface GastoRepository extends JpaRepository<Gasto, Long>{
	

}
