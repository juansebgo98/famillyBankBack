package com.famillyBank.models.services;

import java.util.List;

import com.famillyBank.models.entity.Usuario;



public interface UsuarioService {


    /**
     * Obtener usuario de la BD según su id
     * @param id
     * @return usuario
     */
    public Usuario obtenerUsuarioById(Long id);


    /**
     * Obtener usuario de la BD según su nombre de usuario (username)
     * @param username
     * @return
     */
    public Usuario obtenerUsuarioByUsername(String username);

    /**
     * Obtener usuario de la BD según su cuenta
     * @param id cuenta
     * @return
     */
    public List<Usuario> obtenerUsuarioByCuenta(Long idCuenta);


    /**
     * Obtener todos los usuarios de la BD
     * @return lista de usuarios
     */
    public List<Usuario> obtenerTodosUsuarios();


    /**
     * Crear un nuevo usuario. Si ya existe un usuario con el mismo 'username' no se crea.
     * @param usuario
     * @return usuario creado
     */
    public Usuario crearUsuario(Usuario usuario);


    /**
     * Devuelve el usuario actual que está conectado (autenticado).
     * @return
     */
    public Usuario obtenerUsuarioActualConectado();

    public Usuario saveUpdateUsuario(Usuario usuario);


	public List<Usuario> obtenerUsuarioFilterUsername(String value);


	public List<Usuario> obtenerTodosUsuariosEnCuenta(Long idCuenta);


	public void eliminarUsuario(Long id);

}
