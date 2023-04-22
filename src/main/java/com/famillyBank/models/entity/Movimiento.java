package com.famillyBank.models.entity;


import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.data.annotation.CreatedDate;

import com.famillyBank.models.enumerado.TipoMovimiento;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Movimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    @Column(nullable = false)
    private Date fecha = new Date();;

    @Column(nullable = false)
    private Double cantidad;

    @Column(nullable = false)
    private TipoMovimiento tipo;

    @Column
    private String concepto;

    @Column(nullable = false)
    private Double saldoActual;

    //relaciones

    @ManyToOne
    @JoinColumn(name= "categoria_id")
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name="cuenta_id", nullable = false)
    private Cuenta cuenta;
    
    @ManyToOne
    @JoinColumn(name="cuentaAhorro_id", nullable = true)
    private CuentaAhorro cuentaAhorro;

    @ManyToOne
    @JoinColumn(name= "tarjeta_id")
    @JsonBackReference(value = "tarjetaMovimiento")
    private Tarjeta tarjeta;
    
    @ManyToOne
    @JoinColumn(name= "usuario_id")
    @JsonBackReference(value = "usuarioMovimiento")
    private Usuario usuario;


    public Movimiento() {
    }

    public Movimiento(Double cantidad, TipoMovimiento tipo, String concepto, Double saldoActual, Categoria categoria, Cuenta cuenta, Tarjeta tarjeta, Usuario usuario) {
        this.cantidad = cantidad;
        this.tipo = tipo;
        this.concepto = concepto;
        this.saldoActual = saldoActual;
        this.categoria = categoria;
        this.cuenta = cuenta;
        this.tarjeta = tarjeta;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    public LocalDate getFechaLocal() {
        return Instant.ofEpochMilli(fecha.getTime())
        	      .atZone(ZoneId.systemDefault())
        	      .toLocalDate();

    }

    public void setFechaLocal(LocalDate fecha) {
        this.fecha = Date.from(fecha.atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant());
;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public TipoMovimiento getTipo() {
        return tipo;
    }

    public void setTipo(TipoMovimiento tipo) {
        this.tipo = tipo;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public Double getSaldoActual() {
        return saldoActual;
    }

    public void setSaldoActual(Double saldoActual) {
        this.saldoActual = saldoActual;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public Tarjeta getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(Tarjeta tarjeta) {
        this.tarjeta = tarjeta;
    }

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public CuentaAhorro getCuentaAhorro() {
		return cuentaAhorro;
	}

	public void setCuentaAhorro(CuentaAhorro cuentaAhorro) {
		this.cuentaAhorro = cuentaAhorro;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
    
}
