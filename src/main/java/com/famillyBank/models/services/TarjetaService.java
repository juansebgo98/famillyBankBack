package com.famillyBank.models.services;

import org.springframework.stereotype.Service;

import com.famillyBank.models.entity.Tarjeta;

import java.util.List;

@Service
public interface TarjetaService {
	
	public Tarjeta crearTarjeta(Tarjeta tarjetaNueva);
	
	public List<Tarjeta> obtenerTarjetaByCuenta(Long cuentaId);
	
	public List<Tarjeta> obtenerTarjetas();

	public Tarjeta obtenerTarjetaByNumeroTarjeta(Long numeroTarjeta);
	
	public Tarjeta obtenerTarjetaById(Long idTarjeta);
	
	public Tarjeta actualizarTarjeta(Tarjeta tarjeta);
	
	public void eliminarTarjeta(Long id);
	
}
