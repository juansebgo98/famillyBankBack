package com.famillyBank.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.famillyBank.models.entity.CuentaAhorro;


public interface CuentaAhorroRepository extends JpaRepository<CuentaAhorro, Long> {

	@Query("SELECT ca FROM CuentaAhorro ca WHERE ca.nombre= :nombre")
	CuentaAhorro obtenerCuentaAhorroByName(@Param("nombre")String nombre);

	@Query("SELECT ca FROM CuentaAhorro ca WHERE ca.cuenta.id= :idCuenta")
	List<CuentaAhorro> obtenerCuentaAhorroByCuenta(@Param("idCuenta")Long idCuenta);

}
