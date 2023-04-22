package com.famillyBank.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.famillyBank.models.entity.UsuarioCuenta;
import com.famillyBank.models.entity.UsuarioCuentaId;


public interface UsuarioCuentasRepository extends JpaRepository<UsuarioCuenta, UsuarioCuentaId> {

	@Query("SELECT uc FROM UsuarioCuenta uc WHERE uc.id.usuario.id =:idUsuario")
	List<UsuarioCuenta> obtenerCuentasByUsuario(@Param("idUsuario")Long idUsuario);

	@Query("SELECT uc FROM UsuarioCuenta uc WHERE uc.id.cuenta.id =:idCuenta AND uc.tipoUsuarioCuenta = 1")
	List<UsuarioCuenta> obtenerUsuarioCuentaIgualesByCuenta(@Param("idCuenta")Long idCuentaActual);

	@Query("SELECT uc FROM UsuarioCuenta uc WHERE uc.id.cuenta.id =:idCuenta")
	List<UsuarioCuenta> obtenerCuentasByCuenta(Long idCuenta);
	
	

}
