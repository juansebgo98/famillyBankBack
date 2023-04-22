package com.famillyBank.models.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.famillyBank.models.dao.CuentaAhorroRepository;
import com.famillyBank.models.entity.CuentaAhorro;

@Service
public class CuentaAhorroServiceImpl implements CuentaAhorroService {

	@Autowired
	CuentaAhorroRepository cuentaAhorroRepository;
	
	@Override
	public CuentaAhorro crearActualizarCuentaAhorro(CuentaAhorro cuentaAhorro) {
		return cuentaAhorroRepository.save(cuentaAhorro);
	}

	@Override
	public CuentaAhorro obtenerCuentaAhorroById(Long idCuentaAhorro) {
		return cuentaAhorroRepository.findById(idCuentaAhorro).orElse(null);
	}

	@Override
	public CuentaAhorro obtenerCuentaAhorroByNombre(String nombre) {
		return cuentaAhorroRepository.obtenerCuentaAhorroByName(nombre);
	}

	@Override
	public List<CuentaAhorro> obtenerCuentaAhorroByCuenta(Long idCuenta) {
		return cuentaAhorroRepository.obtenerCuentaAhorroByCuenta(idCuenta);
	}

	@Override
	public void borrarCuentaAhorro(Long id) {
		cuentaAhorroRepository.deleteById(id);		
	}

}
