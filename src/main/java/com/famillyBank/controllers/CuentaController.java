package com.famillyBank.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.famillyBank.models.entity.Cuenta;
import com.famillyBank.models.services.CuentaService;

@RestController
@RequestMapping("/api/v1/cuentas")
public class CuentaController {

    @Autowired
    private CuentaService cuentaService;

    
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> obtenerCuentaById(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
        Cuenta cuenta = cuentaService.obtenerCuentaById(id);
        if (cuenta != null) {
            return new ResponseEntity<>(cuenta, HttpStatus.OK);
        } else {
        	response.put("mensaje", "La cuenta con ID:".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/usuarioPrincipal/{idUsuario}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> obtenerTodasCuentasByUsuarioPrincipalId(@PathVariable Long idUsuario) {
		Map<String, Object> response = new HashMap<>();
    	List<Cuenta> listaCuentas = cuentaService.obtenerTodasCuentasByUsuarioPrincipalId(idUsuario);
    	
    	if(listaCuentas.isEmpty()) {
    		response.put("mensaje", "La cuenta con usuario ID:".concat(idUsuario.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
    	}
        return new ResponseEntity<>(listaCuentas, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> crearCuenta(@RequestBody Cuenta cuenta) {
		Map<String, Object> response = new HashMap<>();
		Cuenta cuentaCreada = null;
		try {
			cuentaCreada = cuentaService.crearCuenta(cuenta);
		} catch (Exception e) {
			response.put("mensaje", "Error al crear la cuenta");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.IM_USED);
		}
        if (cuentaCreada != null) {
            return ResponseEntity.ok(cuentaCreada);
        } else {
        	response.put("mensaje", "La cuenta con iban: ".concat(cuenta.getIban().concat(" ya existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.IM_USED);
        }
    }
    
    @GetMapping("/usuario/{idUsuario}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> obtenerTodasCuentasByUsuarioId(@PathVariable Long idUsuario) {
		Map<String, Object> response = new HashMap<>();
    	List<Cuenta> listaCuentas = cuentaService.obtenerCuentasDeUsuario(idUsuario);
    	
    	if(listaCuentas.isEmpty()) {
    		response.put("mensaje", "La cuenta con usuario ID:".concat(idUsuario.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
    	}
        return new ResponseEntity<>(listaCuentas, HttpStatus.OK);
    }

    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> borrarCuentaAhorro(@PathVariable("id") Long id) {
		Map<String, Object> response = new HashMap<>();
        try {
        	cuentaService.eliminarCuenta(id);
    		response.put("mensaje","La cuenta con id " + id + " ha sido eliminada correctamente.");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        } catch (Exception e) {
    		response.put("mensaje","Error al eliminar la cuenta de ahorro con id " + id);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}