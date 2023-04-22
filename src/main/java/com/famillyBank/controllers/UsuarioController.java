package com.famillyBank.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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

import com.famillyBank.models.entity.Usuario;
import com.famillyBank.models.services.UsuarioService;
import com.famillyBank.models.utils.Utils;


@RestController
@RequestMapping("/api/v1")
public class UsuarioController {
    
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/usuarios")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> crearUsuario(@RequestBody Usuario usuario) {
		Map<String, Object> response = new HashMap<>();
		Usuario usuarioCreado;
		try {
			if(usuario.getPassword() == null) {
				usuario.setPassword(Utils.encriptContrasena(usuario.getUsername().substring(0, 2)+usuario.getNombreCorto().substring(0, 2)+"10"));
			}
			usuarioCreado = usuarioService.crearUsuario(usuario);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El usuario ha sido creado con éxito!");
		response.put("usuario", usuarioCreado);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @GetMapping("/usuarios/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> obtenerUsuario(@PathVariable Long id) {
    	Usuario usuario;
    	Map<String, Object> response = new HashMap<>();
    	try {
    		usuario = usuarioService.obtenerUsuarioById(id);
    	}catch (DataAccessException e) {
			response.put("mensaje", "Error al consultar la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(usuario == null){
			response.put("mensaje", "El Usuario ID:".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
    }

    @PutMapping("/usuarios")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> actualizarUsuario( @RequestBody Usuario usuario) {
		Map<String, Object> response = new HashMap<>();
		Usuario usuarioActualizado;
		if(usuario.getId()==null) {
			response.put("mensaje", "Error al actualizar el usuario en  la base de datos(No tiene id)");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		usuarioActualizado = usuarioService.obtenerUsuarioById(usuario.getId());
		if(usuario.getId()==null) {
			response.put("mensaje", "Error al actualizar el usuario en  la base de datos(No se ha encontrado usuario)");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
    	try {
    		if(usuario.getPassword()==null) {
    			usuario.setPassword(usuarioActualizado.getPassword());
    		}
    		usuarioActualizado = usuarioService.saveUpdateUsuario(usuario);
    	}catch (DataAccessException e) {
			response.put("mensaje", "Error al guardar y actualizar el usuario en  la base de datos");
			response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
    	response.put("mensaje", "El Usuario ha sido actualizado con exito");
		response.put("usuario", usuarioActualizado);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
    }

    @DeleteMapping("/usuarios/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> eliminarUsuario(@PathVariable Long id) {
    	Map<String, Object> response = new HashMap<>();

		try {
			usuarioService.eliminarUsuario(id);
		}catch (DataAccessException e) {
			response.put("mensaje", "Error al borrar el usuario en  la base de datos");
			response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "Se ha borrar el usuario en la base de datos");
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);
        
    }

}

