package com.famillyBank.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.famillyBank.models.dao.CuentaRepository;
import com.famillyBank.models.entity.Cuenta;

import java.util.List;

@Service
public class CuentaServiceImpl implements CuentaService {

	@Autowired
	CuentaRepository cuentaRepository;
	
	
	@Override
	public Cuenta crearCuenta(Cuenta cuenta) {
		 if(cuentaRepository.existsByIban(cuenta.getIban())){
			 return null;
		 }
		return cuentaRepository.save(cuenta);
	}

	@Override
	public Cuenta obtenerCuentaById(Long idCuenta) {
		return cuentaRepository.findById(idCuenta).orElse(null);
	}

	@Override
	public List<Cuenta> obtenerTodasCuentasByUsuarioPrincipalId(Long idUsuario) {
		return cuentaRepository.obtenerCuentasByUsuarioPrincipalId(idUsuario);
	}

	@Override
	public Cuenta save(Cuenta cuentaObtenida) {
		return cuentaRepository.save(cuentaObtenida);
	}

	@Override
	public void eliminarCuenta(Long id) {
		cuentaRepository.deleteById(id);
	}

	@Override
	public List<Cuenta> obtenerCuentasDeUsuario(Long idUsuario) {
		return cuentaRepository.obtenerCuentasUsuario(idUsuario);
	}

	@Override
	public Cuenta obtenerCuentaByIban(String iban) {
		return cuentaRepository.obtenerCuentaByIban(iban);
	}

}
