package com.famillyBank.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.famillyBank.models.entity.Cuenta;

import java.util.List;

public interface CuentaRepository extends JpaRepository<Cuenta, Long> {

	boolean existsByIban(String iban);

	@Query("SELECT c FROM Cuenta c WHERE c.usuarioPrincipal.id = :idUsuario")
	List<Cuenta> obtenerCuentasByUsuarioPrincipalId(@Param("idUsuario")Long idUsuario);
	

	@Query("select c from Cuenta c join c.usuarios uc where uc.id.usuario.id = :idUsuario")
	List<Cuenta> obtenerCuentasUsuario(@Param("idUsuario") Long idUsuario);

	@Query("SELECT c FROM Cuenta c WHERE c.iban = :iban")
	Cuenta obtenerCuentaByIban(String iban);


}
