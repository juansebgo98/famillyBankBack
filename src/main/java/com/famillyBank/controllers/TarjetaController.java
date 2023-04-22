package com.famillyBank.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RestController;

import com.famillyBank.models.entity.Tarjeta;
import com.famillyBank.models.services.TarjetaService;

@RestController
@RequestMapping("/api/v1/tarjetas")
public class TarjetaController {

    @Autowired
    private TarjetaService tarjetaService;

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> getTarjetaById(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
    	Tarjeta tarjeta = null;
    	try {
    		tarjeta = tarjetaService.obtenerTarjetaById(id);    		
    	}catch (EntityNotFoundException e) {
    		response.put("mensaje", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
        if (tarjeta == null) {
    		response.put("mensaje", "No se ha encontrado tarjeta con el id: "+id);
            return ResponseEntity.notFound().build();
        }

		response.put("tarjeta", tarjeta);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> createTarjeta(@RequestBody Tarjeta tarjeta) {
		Map<String, Object> response = new HashMap<>();
    	Tarjeta nuevaTarjeta = null;
    	try {
    		nuevaTarjeta = tarjetaService.crearTarjeta(tarjeta);    		
    	}catch (Exception e) {
    		response.put("mensaje", "Error al crear nueva Tarjeta");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
    	response.put("mensaje", "Tarjeta creada correctamente");
    	response.put("tarjeta", nuevaTarjeta);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaTarjeta);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> updateTarjeta( @RequestBody Tarjeta tarjeta) {
		Map<String, Object> response = new HashMap<>();
		Tarjeta tarjetaExistente = null;
    	if(tarjeta!=null & tarjeta.getId()!=null) {
    		tarjetaExistente = tarjetaService.obtenerTarjetaById(tarjeta.getId());    		
    	}
        if (tarjetaExistente == null) {
        	response.put("mensaje", "Error al crear nueva Tarjeta");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
        tarjetaExistente.setNumero(tarjeta.getNumero());
        tarjetaExistente.setCuenta(tarjeta.getCuenta());
        if(tarjeta.getMovimientos()!=null & !tarjeta.getMovimientos().isEmpty()) {
        	tarjetaExistente.setMovimientos(tarjeta.getMovimientos());        	
        }
        try {
        	tarjetaService.actualizarTarjeta(tarjetaExistente);        	
        }catch (Exception e) {
        	response.put("mensaje", "Error al actualizar Tarjeta con id: "+ tarjeta.getId());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
        response.put("mensaje", "Error al actualizar Tarjeta con id: "+ tarjeta.getId());
        response.put("tarjeta", tarjetaExistente);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> deleteTarjeta(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		Tarjeta tarjeta = tarjetaService.obtenerTarjetaById(id);
        if (tarjeta == null) {
        	response.put("mensaje", "No existe Tarjeta con id: "+ id);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        tarjetaService.eliminarTarjeta(id);
        
        response.put("mensaje", "Se ha eliminad Tarjeta con id: "+ tarjeta.getNumeroEnmascarado());
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @GetMapping
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<List<Tarjeta>> getAllTarjetas() {
        List<Tarjeta> tarjetas = tarjetaService.obtenerTarjetas();
        return ResponseEntity.ok(tarjetas);
    }
}

