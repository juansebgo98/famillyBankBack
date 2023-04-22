package com.famillyBank.models.services;

import org.springframework.stereotype.Service;

import com.famillyBank.models.entity.Cuenta;

import java.util.List;

@Service
public interface CuentaService {

	/**
	 * Crea una nueva cuenta
	 * @param cuenta cuenta que queremos crear
	 * @return La cuenta creada
	 */
	Cuenta crearCuenta(Cuenta cuenta);

	/**
	 * Obtener una cuenta según el id
	 * @param idCuenta id de la cuenta
	 * @return Cuenta
	 */
	Cuenta obtenerCuentaById(Long idCuenta);

	
	Cuenta obtenerCuentaByIban(String iban);
	
	/**
	 * Obtener las cuentas del usuario según su id
	 * @param idUsuario id del usuario
	 * @return Lista de cuentas
	 */
	List<Cuenta> obtenerTodasCuentasByUsuarioPrincipalId(Long idUsuario);
	
	List<Cuenta> obtenerCuentasDeUsuario(Long idUsuario);

	Cuenta save(Cuenta cuentaObtenida);
	
	void eliminarCuenta(Long id);
	

}
