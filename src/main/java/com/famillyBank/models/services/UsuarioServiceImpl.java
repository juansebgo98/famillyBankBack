package com.famillyBank.models.services;

import org.springframework.stereotype.Service;

import com.famillyBank.models.dao.UsuarioRepository;
import com.famillyBank.models.entity.Usuario;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    UsuarioRepository usuarioRepositorio;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
    }

    @Override
    public Usuario obtenerUsuarioById(Long id) {
        return usuarioRepositorio.findById(id).orElse(null);
    }

    @Override
    public Usuario obtenerUsuarioByUsername(String username) {
        return usuarioRepositorio.findByUsername(username).orElse(null);
    }

    @Override
    public List<Usuario> obtenerTodosUsuarios() {
        return usuarioRepositorio.findAll();
    }

    @Override
    public Usuario crearUsuario(Usuario usuario) {
    	Usuario usuarioCreado = null;
        // miramos si ya existe un usuario con el 'username' indicado
        if(!usuarioRepositorio.existsByUsername(usuario.getUsername())){
            usuarioCreado = usuarioRepositorio.save(usuario);
        }
        return usuarioCreado;

    }
    
    @Override
	public Usuario obtenerUsuarioActualConectado() {
		// TODO ver usuario conectado
		return null;
	}
/*
    @Override
    public Usuario obtenerUsuarioActualConectado() {
    	//TODO ver usuario conectado
        UserDetails userDetails = ((UserDetails)principal);
        Optional<Usuario> usuarioActual = obtenerUsuarioByUsername(userDetails.getUsername());
        return usuarioActual;
    }
*/
	@Override
	public Usuario saveUpdateUsuario(Usuario usuario) {
		return usuarioRepositorio.save(usuario);
	}

	@Override
	public List<Usuario> obtenerUsuarioByCuenta(Long idCuenta) {
		return usuarioRepositorio.obtenerUsuariosByCuenta(idCuenta);
	}

	@Override
	public List<Usuario> obtenerUsuarioFilterUsername(String userName) {
		userName = "%"+userName+"%";
		return usuarioRepositorio.obtenerUsuariosFiltradoUserName(userName);
	}

	@Override
	public List<Usuario> obtenerTodosUsuariosEnCuenta(Long idCuenta) {
		
		return usuarioRepositorio.obtenerUsuariosByCuenta(idCuenta);
	}

	@Override
	public void eliminarUsuario(Long id) {
		usuarioRepositorio.deleteById(id);
		
	}

}
