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

import com.famillyBank.models.entity.CuentaAhorro;
import com.famillyBank.models.services.CuentaAhorroService;

@RestController
@RequestMapping("/api/v1/cuenta-ahorro")
public class CuentaAhorroController {

	@Autowired
    private CuentaAhorroService cuentaAhorroService;

    @PostMapping("/")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> crearActualizarCuentaAhorro(@RequestBody CuentaAhorro cuentaAhorro) {
        CuentaAhorro cuentaAhorroCreada = cuentaAhorroService.crearActualizarCuentaAhorro(cuentaAhorro);
        return new ResponseEntity<>(cuentaAhorroCreada, HttpStatus.CREATED);
    }

    @GetMapping("/{idCuenta}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> obtenerCuentaAhorroById(@PathVariable Long idCuenta) {
		Map<String, Object> response = new HashMap<>();
		CuentaAhorro cuentaAhorroEncontrada = cuentaAhorroService.obtenerCuentaAhorroById(idCuenta);
        if (cuentaAhorroEncontrada != null) {
        	response.put("mensaje", "La cuenta ahorro ha sido creado con éxito!");
    		response.put("cuentaAhorro", cuentaAhorroEncontrada);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
        	response.put("mensaje", "No se ha encontrado cuenta ahorro con el id "+idCuenta);
            return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/nombre/{nombre}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> obtenerCuentaAhorroByNombre(@PathVariable String nombre) {
		Map<String, Object> response = new HashMap<>();
		CuentaAhorro cuentaAhorroEncontrada = cuentaAhorroService.obtenerCuentaAhorroByNombre(nombre);
        if (cuentaAhorroEncontrada != null) {
            return new ResponseEntity<CuentaAhorro>(cuentaAhorroEncontrada, HttpStatus.OK);
        } else {
        	response.put("mensaje", "No se ha encontrado cuenta ahorro con el nombre "+nombre);
            return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/cuenta/{idCuenta}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> obtenerCuentaAhorroByCuenta(@PathVariable Long idCuenta) {
		Map<String, Object> response = new HashMap<>();
        List<CuentaAhorro> cuentasAhorroEncontradas = cuentaAhorroService.obtenerCuentaAhorroByCuenta(idCuenta);
        if (!cuentasAhorroEncontradas.isEmpty()) {
        	
            return new ResponseEntity<List<CuentaAhorro>>(cuentasAhorroEncontradas, HttpStatus.OK);
        } else {
        	response.put("mensaje", "No se ha encontrado cuenta ahorro con la cuenta id "+idCuenta);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> borrarCuentaAhorro(@PathVariable("id") Long id) {
		Map<String, Object> response = new HashMap<>();
        try {
            cuentaAhorroService.borrarCuentaAhorro(id);
            response.put("mensaje", "La cuenta de ahorro con id " + id + " ha sido eliminada correctamente.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        } catch (Exception e) {
        	response.put("mensaje","Error al eliminar la cuenta de ahorro con id " + id + ": " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}

