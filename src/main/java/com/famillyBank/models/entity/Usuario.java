package com.famillyBank.models.entity;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "usuario", uniqueConstraints = {
        @UniqueConstraint(columnNames = "username") })
public class Usuario {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;   // para el login

    private String nombreCompleto;
    
    private String nombreCorto;
    
    private String image;
    
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;
    
    private boolean resetearPassword;

    @JsonIgnore
    private String password;

    // relaciones
    
    @OneToMany(mappedBy = "usuarioPrincipal")
    @JsonIgnore
    List<Cuenta> cuentasPrincipales = new ArrayList<>();

//    CREATE TABLE `usuario_cuenta` (
//    		  `usuario_id` bigint NOT NULL,
//    		  `cuenta_id` bigint NOT NULL,
//    		  FOREIGN KEY (`cuenta_id`) REFERENCES `cuenta` (`id`),
//    		  FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`)
//    		);
    
//    @ManyToMany
//    @JoinTable(
//            name = "usuario_cuenta",
//            joinColumns = {@JoinColumn(name="usuario_id", referencedColumnName = "id")},
//            inverseJoinColumns = {@JoinColumn(name="cuenta_id", referencedColumnName = "id")}
//    )
//    List<Cuenta> cuentas = new ArrayList<>();
    @OneToMany(mappedBy = "id.usuario")
    @JsonIgnore
    private Set<UsuarioCuenta> cuentas = new HashSet<UsuarioCuenta>(0);

    
    
    @OneToMany(mappedBy = "usuario")
    @JsonIgnore
    List<Movimiento> movimientos = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles", 
               joinColumns = @JoinColumn(name = "user_id"),
               inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public Usuario() {
    }

    public Usuario(String username, String email, String password) {
    	this.username = username;
    	this.email = email;
    	this.password = password;
    }
    
    public Usuario(String username,String image, String nombreCompleto, String nombreCorto, String password) {
        this.username = username;
        this.image = image;
        this.nombreCompleto = nombreCompleto;
        this.nombreCorto = nombreCorto;
        this.password = password;
    }

    public Usuario(Long usuarioId) {
		super();
		this.id = usuarioId;
	}

	public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getNombreCorto() {
		return nombreCorto;
	}

	public void setNombreCorto(String nombreCorto) {
		this.nombreCorto = nombreCorto;
	}

	public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}


	public List<Cuenta> getCuentasPrincipales() {
		return cuentasPrincipales;
	}

	public void setCuentasPrincipales(List<Cuenta> cuentasPrincipales) {
		this.cuentasPrincipales = cuentasPrincipales;
	}

	public Set<UsuarioCuenta> getCuentas() {
		return cuentas;
	}

	public void setCuentas(Set<UsuarioCuenta> cuentas) {
		this.cuentas = cuentas;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Movimiento> getMovimientos() {
		return movimientos;
	}

	public void setMovimientos(List<Movimiento> movimientos) {
		this.movimientos = movimientos;
	}

	public boolean isResetearPassword() {
		return resetearPassword;
	}

	public void setResetearPassword(boolean resetearPassword) {
		this.resetearPassword = resetearPassword;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
    
	
    
}
