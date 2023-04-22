package com.famillyBank.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
import com.famillyBank.models.entity.Usuario;
import com.famillyBank.models.entity.UsuarioCuenta;
import com.famillyBank.models.entity.UsuarioCuentaId;
import com.famillyBank.models.services.UsuarioCuentaService;

@RestController
@RequestMapping("/api/v1/usuario-cuenta")
public class UsuarioCuentaController {
    
    @Autowired
    private UsuarioCuentaService usuarioCuentaService;

    @GetMapping("/{usuarioId}/{cuentaId}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> obtenerDatosUsuarioCuenta(@PathVariable Long usuarioId, @PathVariable Long cuentaId) {
		Map<String, Object> response = new HashMap<>();
        UsuarioCuentaId id = new UsuarioCuentaId();
        id.setUsuario(new Usuario(usuarioId));
        id.setCuenta(new Cuenta(cuentaId));
        Optional<UsuarioCuenta> usuarioCuenta = usuarioCuentaService.obtenerDatosUsuarioCuenta(id);
        
        if (usuarioCuenta.isPresent()) {
        	response.put("usuarioCuenta", usuarioCuenta.get());
        	return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
        	response.put("mensaje", "No existe relacion entre cuenta y usuario");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/usuario/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> obtenerRelacionPorUsuario(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		List<UsuarioCuenta> usuarioCuentas = null;
		try {
			usuarioCuentas = usuarioCuentaService.obtenerCuentasDeUsuario(id);			
		}catch (Exception e) {
			response.put("mensaje", "Error al intentar ver la relacion entre cuenta y usuario");
        	return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
		
		if (usuarioCuentas != null && usuarioCuentas.isEmpty()) {
        	response.put("mensaje", "No existe usuarios relacionados con esa cuenta");
        	return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    	return new ResponseEntity<List<UsuarioCuenta>>(usuarioCuentas, HttpStatus.OK);
    }
    
    @GetMapping("/cuenta/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> obtenerRelacionPorCuenta(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		List<UsuarioCuenta> usuarioCuentas = null;
		try {
			usuarioCuentas = usuarioCuentaService.obtenerRelacionPorCuenta(id);			
		}catch (Exception e) {
			response.put("mensaje", "Error al intentar ver la relacion con la cuenta");
        	return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
		
		if (usuarioCuentas != null && usuarioCuentas.isEmpty()) {
        	response.put("mensaje", "No existe usuarios relacionados con esa cuenta");
        	return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    	return new ResponseEntity<List<UsuarioCuenta>>(usuarioCuentas, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> crearUsuarioCuenta(@RequestBody UsuarioCuenta usuarioCuenta) {
		Map<String, Object> response = new HashMap<>();
		if(usuarioCuentaService.exist(usuarioCuenta.getId())) {
    		response.put("mensaje", "Esta relacion ya existe en la base de datos");
        	return new ResponseEntity<>(response, HttpStatus.ALREADY_REPORTED);
    	}
		UsuarioCuenta resultado = null;
		try {
			resultado = usuarioCuentaService.save(usuarioCuenta);			
		}catch (Exception e) {
			response.put("mensaje", "Error al crear relacion revisa que todos los datos sean correctos");
        	return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    	}
        response.put("cuentaUsuario", resultado);
    	return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @DeleteMapping("/{usuarioId}/{cuentaId}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> eliminarUsuarioCuenta(@PathVariable Long usuarioId, @PathVariable Long cuentaId) {
		Map<String, Object> response = new HashMap<>();
        UsuarioCuentaId id = new UsuarioCuentaId();
        id.setUsuario(new Usuario(usuarioId));
        id.setCuenta(new Cuenta(cuentaId));
        if(usuarioCuentaService.exist(id)){
        	response.put("mensaje", "Se ha eliminado la relacion correctamente");
        	return new ResponseEntity<>(response, HttpStatus.OK);
        }else {
        	response.put("mensaje", "No existe la relacion entre el usuario: "+usuarioId+ " y la cuenta: "+cuentaId);
        	return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
}

