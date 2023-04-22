package com.famillyBank.models.services;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.famillyBank.models.dao.TarjetaRepository;
import com.famillyBank.models.entity.Tarjeta;

@Service
public class TarjetaServiceImpl implements TarjetaService {
	
	@Autowired
	TarjetaRepository tarjetaRepository;

	@Transactional
    @Override
	public Tarjeta crearTarjeta(Tarjeta tarjetaNueva) {
		return tarjetaRepository.save(tarjetaNueva);
	}

	@Transactional
	@Override
	public List<Tarjeta> obtenerTarjetaByCuenta(Long cuentaId) {
		
		return tarjetaRepository.findByCuentaId(cuentaId);
	}

	@Transactional
	@Override
	public Tarjeta obtenerTarjetaByNumeroTarjeta(Long numeroTarjeta) {
		return tarjetaRepository.obtenerTarjetaByNumeroTarjeta(numeroTarjeta);
	}

	@Transactional
	@Override
	public Tarjeta obtenerTarjetaById(Long idTarjeta) throws EntityNotFoundException{
		return tarjetaRepository.findById(idTarjeta).orElseThrow(()
                -> new EntityNotFoundException("No se ha encontrado tarjeta con id: "+idTarjeta));
	}

	@Override
	public Tarjeta actualizarTarjeta(Tarjeta tarjeta) {
		return tarjetaRepository.save(tarjeta);
	}

	@Override
	public void eliminarTarjeta(Long id) {
		tarjetaRepository.deleteById(id);
	}

	@Override
	public List<Tarjeta> obtenerTarjetas() {
		return tarjetaRepository.findAll();
	}

	
}
