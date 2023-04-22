package com.famillyBank.models.services;

import java.util.List;
import java.util.Optional;

import com.famillyBank.models.entity.UsuarioCuenta;
import com.famillyBank.models.entity.UsuarioCuentaId;



public interface UsuarioCuentaService {
	
	public Optional<UsuarioCuenta> obtenerDatosUsuarioCuenta(UsuarioCuentaId id);
	
	public List<UsuarioCuenta> obtenerCuentasDeUsuario(Long idUsuario);
	
	public List<UsuarioCuenta> obtenerRelacionPorCuenta(Long idCuenta);

	public UsuarioCuenta save(UsuarioCuenta usuarioCuenta);
	
	public boolean exist(UsuarioCuentaId id);

	public List<UsuarioCuenta> obtenerUsuariosIgualesByCuenta(Long idCuentaActual);
	
	public void eliminarUsuarioCuenta(UsuarioCuentaId id);

}
