package com.thommache.sprintboot.app.evaluacion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thommache.sprintboot.app.evaluacion.entity.Producto;

@Repository
public interface ProductRepository extends JpaRepository<Producto, Integer>{

}
