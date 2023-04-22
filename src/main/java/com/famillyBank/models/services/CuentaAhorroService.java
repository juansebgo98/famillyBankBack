package com.famillyBank.models.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.famillyBank.models.entity.CuentaAhorro;

@Service
public interface CuentaAhorroService {

	CuentaAhorro crearActualizarCuentaAhorro(CuentaAhorro cuentaAhorro);

	CuentaAhorro obtenerCuentaAhorroById(Long idCuenta);

	CuentaAhorro obtenerCuentaAhorroByNombre(String nombre);

	List<CuentaAhorro> obtenerCuentaAhorroByCuenta(Long idCuenta);
	
	void borrarCuentaAhorro(Long id);

}
