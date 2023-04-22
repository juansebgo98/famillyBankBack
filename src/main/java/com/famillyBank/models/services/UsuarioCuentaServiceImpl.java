package com.famillyBank.models.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.famillyBank.models.dao.UsuarioCuentasRepository;
import com.famillyBank.models.entity.UsuarioCuenta;
import com.famillyBank.models.entity.UsuarioCuentaId;

@Service
public class UsuarioCuentaServiceImpl implements UsuarioCuentaService{
	
	UsuarioCuentasRepository usuarioCuentaRepository;
	
	public UsuarioCuentaServiceImpl(UsuarioCuentasRepository usuarioCuentaRepository) {
		this.usuarioCuentaRepository = usuarioCuentaRepository;
	}

	@Override
	public Optional<UsuarioCuenta> obtenerDatosUsuarioCuenta(UsuarioCuentaId id) {
		return usuarioCuentaRepository.findById(id);
	}

	@Override
	public UsuarioCuenta save(UsuarioCuenta usuarioCuenta) {
		return usuarioCuentaRepository.save(usuarioCuenta);
	}

	@Override
	public boolean exist(UsuarioCuentaId id) {
		return usuarioCuentaRepository.existsById(id);
	}

	@Override
	public List<UsuarioCuenta> obtenerCuentasDeUsuario(Long idUsuario) {
		return usuarioCuentaRepository.obtenerCuentasByUsuario(idUsuario);
	}

	@Override
	public List<UsuarioCuenta> obtenerUsuariosIgualesByCuenta(Long idCuentaActual) {
		return usuarioCuentaRepository.obtenerUsuarioCuentaIgualesByCuenta(idCuentaActual);
	}

	@Override
	public void eliminarUsuarioCuenta(UsuarioCuentaId id) {
		usuarioCuentaRepository.deleteById(id);
		
	}

	@Override
	public List<UsuarioCuenta> obtenerRelacionPorCuenta(Long idCuenta) {
		return usuarioCuentaRepository.obtenerCuentasByCuenta(idCuenta);
	}

}
