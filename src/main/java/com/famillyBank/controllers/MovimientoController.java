package com.famillyBank.controllers;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.famillyBank.models.entity.Movimiento;
import com.famillyBank.models.payload.filter.MovimientoMesFilter;
import com.famillyBank.models.payload.filter.MovimientosFilter;
import com.famillyBank.models.services.MovimientoService;

@RestController
@RequestMapping("/api/v1/movimientos")
public class MovimientoController {

    @Autowired
    private MovimientoService movimientoService;

    @GetMapping("/tarjeta/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> obtenerMovimientosDeTarjeta(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		
        List<Movimiento> movimientos = movimientoService.obtenerMovimientosDeTarjeta(id);
        if(movimientos != null && !movimientos.isEmpty()) {
        	return ResponseEntity.ok(movimientos);
        }else {
        	response.put("mensaje", "No existe movimientos para la tarjeta con id:".concat(id.toString()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/tarjeta/{idTarjeta}/usuario/{idUsuario}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> obtenerMovimientosDeTarjetaByUsuario(@PathVariable Long idTarjeta, @PathVariable Long idUsuario) {
		Map<String, Object> response = new HashMap<>();
        List<Movimiento> movimientos = movimientoService.obtenerMovimientosDeTarjetaByUsuario(idTarjeta, idUsuario);
        
        if(movimientos != null && !movimientos.isEmpty()) {
        	return ResponseEntity.ok(movimientos);
        }else {
        	response.put("mensaje", "No existe movimientos para la tarjeta con id:".concat(idTarjeta.toString()).concat(" y usuario con ID: ").concat(idUsuario.toString()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/cuenta/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> obtenerMovimientosDeCuenta(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();

        List<Movimiento> movimientos = movimientoService.obtenerMovimientosDeCuenta(id);
        if(movimientos != null && !movimientos.isEmpty()) {
        	return ResponseEntity.ok(movimientos);
        }else {
        	response.put("mensaje", "No existe movimientos para la cuenta con id:".concat(id.toString()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/filtrados")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> obtenerMovimientosFiltrados(@RequestBody MovimientosFilter filtro) {
		Map<String, Object> response = new HashMap<>();
		List<Movimiento> movimientos = null;
		try {
			movimientos = movimientoService.obtenerMovimientosFiltrados(filtro);			
		}catch (Exception e) {
			response.put("mensaje", "No existe movimientos para los filtros aplicados");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
        if(movimientos != null && !movimientos.isEmpty()) {
        	return ResponseEntity.ok(movimientos);
        }else {
        	response.put("mensaje", "No existe movimientos para los filtros aplicados");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/cuenta/{idCuenta}/fecha")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> obtenerMovimientoFechaCuenta(@PathVariable Long idCuenta, @RequestParam("fechaInit") LocalDate fechaInit, @RequestParam("fechaFin") LocalDate fechaFin) {
		Map<String, Object> response = new HashMap<>();
        List<Movimiento> movimientos = movimientoService.obtenerMovimientoFechaCuenta(idCuenta, fechaInit, fechaFin);
        if(movimientos != null && !movimientos.isEmpty()) {
        	return ResponseEntity.ok(movimientos);
        }else {
        	response.put("mensaje", "No existe movimientos para la cuenta " + idCuenta + " en las fechas seleccionadas");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/tarjeta/{idTarjeta}/fecha")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> obtenerMovimientoFechaTarjeta(@PathVariable Long idTarjeta, @RequestParam("fechaInit") LocalDate fechaInit, @RequestParam("fechaFin") LocalDate fechaFin) {
		Map<String, Object> response = new HashMap<>();
        List<Movimiento> movimientos = movimientoService.obtenerMovimientoFechaTarjeta(idTarjeta, fechaInit, fechaFin);
        if(movimientos != null && !movimientos.isEmpty()) {
        	return ResponseEntity.ok(movimientos);
        }else {
        	response.put("mensaje", "No existe movimientos para la tarjeta " + idTarjeta + " en las fechas seleccionadas");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/tarjeta/{idTarjeta}/categoria")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> obtenerMovimientosTarjetaByCategoria(@PathVariable Long idTarjeta, @RequestBody MovimientoMesFilter filtroMovimiento) {
		Map<String, Object> response = new HashMap<>();
		List<Movimiento> movimientos = movimientoService.obtenerMovimientosTarjetaByCategoria(idTarjeta, filtroMovimiento);
        if(movimientos != null && !movimientos.isEmpty()) {
        	return ResponseEntity.ok(movimientos);
        }else {
        	response.put("mensaje", "No existe movimientos para la tarjeta " + idTarjeta + " con la categoria y mes proporcionada");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/usuario/{idUsuario}/categoria")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> obtenerMovimientosUsuarioByCategoria(@PathVariable Long idUsuario, @RequestBody MovimientoMesFilter filtroMovimiento) {
		Map<String, Object> response = new HashMap<>();
		List<Movimiento> movimientos = movimientoService.obtenerMovimientosUsuarioByCategoria(idUsuario, filtroMovimiento);
        if(movimientos != null && !movimientos.isEmpty()) {
        	return ResponseEntity.ok(movimientos);
        }else {
        	response.put("mensaje", "No existe movimientos para el usuario " + idUsuario + " con la categoria y mes proporcionada");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> crearMovimiento(@RequestBody Movimiento movimientoNuevo) throws Exception {
    	Movimiento movimientoCreado = null;
		Map<String, Object> response = new HashMap<>();

    	try {
    		movimientoCreado = movimientoService.crearMovimiento(movimientoNuevo);    		
    	}catch (Exception e) {
    		response.put("mensaje", "No Error al intentar crear movimiento con los datos proporcionados");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
        return ResponseEntity.status(HttpStatus.CREATED).body(movimientoCreado);
    }
    
    @GetMapping("/cuenta/{idCuenta}/ordenadosFecha")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<List<Movimiento>> obtenerMovimientosDeCuentaOrdenadosFecha(@PathVariable Long idCuenta) {
		List<Movimiento> movimientos = movimientoService.obtenerMovimientosDeCuentaOrdenadosFecha(idCuenta);
		return ResponseEntity.ok(movimientos);
	}
	
	@GetMapping("/cuenta/{idCuenta}/usuario/{id}/ordenadosFecha")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> obtenerMovimientosDeCuentaByUsuarioOrdenadosFecha(@PathVariable Long idCuenta, @PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		List<Movimiento> movimientos = movimientoService.obtenerMovimientosDeCuentaByUsuarioOrdenadosFecha(idCuenta, id);
		if(movimientos != null && !movimientos.isEmpty()) {
        	return ResponseEntity.ok(movimientos);
        }else {
        	response.put("mensaje", "No existe resultado para busqueda especificada");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
	}
	
	@GetMapping("/cuenta/{idCuenta}/usuario/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> obtenerMovimientoFechaCuentaByUsuario(@PathVariable Long idCuenta, @PathVariable Long id, 
											@RequestParam("fechaInicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio, 
											@RequestParam("fechaFin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin) {
		Map<String, Object> response = new HashMap<>();
		List<Movimiento> movimientos = movimientoService.obtenerMovimientoFechaCuentaByUsuario(idCuenta, id, fechaInicio, fechaFin);
		if(movimientos != null && !movimientos.isEmpty()) {
        	return ResponseEntity.ok(movimientos);
        }else {
        	response.put("mensaje", "No existe resultado para busqueda especificada");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
	}
	
	@GetMapping("/cuentaAhorro/{idCuentaAhorro}")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> obtenerMovimientosDeCuentaByCuentaAhorro(@PathVariable Long idCuentaAhorro, 
											@RequestParam("fechaInicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio, 
											@RequestParam("fechaFin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin) {
		Map<String, Object> response = new HashMap<>();
		List<Movimiento> movimientos = movimientoService.obtenerMovimientosDeCuentaByCuentaAhorro(idCuentaAhorro, fechaInicio, fechaFin);
		if(movimientos != null && !movimientos.isEmpty()) {
        	return ResponseEntity.ok(movimientos);
        }else {
        	response.put("mensaje", "No existe resultado para busqueda especificada");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
	}

	@PutMapping("/actualizar")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> actualizarMovimiento(@RequestBody Movimiento movimientoEditable) {
		Map<String, Object> response = new HashMap<>();
		Movimiento movimientoActualizado = null;
		try {
			movimientoActualizado = movimientoService.actualizarMovimiento(movimientoEditable);			
		}catch (DataAccessException e) {
			response.put("mensaje", "No se ha podido actualizar los datos del movimiento con id: "+movimientoEditable.getId()+" Error: "+e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(movimientoActualizado);
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> obtenerMovimientoPorId(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		Movimiento movimiento = movimientoService.obtenerMovimientoId(id);
		if(movimiento != null) {
        	return ResponseEntity.ok(movimiento);
        }else {
        	response.put("mensaje", "No existe movimiento para el Id: "+id);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> eliminarMovimiento(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		try {
			movimientoService.eliminar(id);
			
		}catch (Exception e) {
			response.put("mensaje", "Error al eliminar movimiento con Id: "+id);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje","La cuenta con id " + id + " ha sido eliminada correctamente.");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

}

