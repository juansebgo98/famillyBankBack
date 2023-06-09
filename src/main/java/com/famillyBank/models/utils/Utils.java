package com.famillyBank.models.utils;

import java.text.DateFormatSymbols;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.famillyBank.models.entity.Cuenta;
import com.famillyBank.models.entity.Movimiento;
import com.famillyBank.models.entity.Usuario;
import com.famillyBank.models.entity.UsuarioCuenta;
import com.famillyBank.models.entity.UsuarioCuentaId;
import com.famillyBank.models.enumerado.TipoMovimiento;
import com.famillyBank.models.services.UsuarioCuentaService;




public class Utils {
	
//	public static Optional<Usuario> getCurrentUser(UsuarioService usuarioService){
//	    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//	    UserDetails userDetails = ((UserDetails)principal);
//	    Optional<Usuario> usuarioActual = usuarioService.obtenerUsuarioByUsername(userDetails.getUsername());
//	    return usuarioActual;
//	}
	
	public static String getMonthForInt(int num) {
	        String month = "wrong";
	    DateFormatSymbols dfs = new DateFormatSymbols();
	    String[] months = dfs.getMonths();
	    if (num >= 0 && num <= 11 ) {
	        month = months[num];
	    }
	    return month;
	}
  
	public static double obtenerIngresos(List<Movimiento> listaMovimientos) {
		 double ingreso = 0;
		 for (Iterator<Movimiento> iterator = listaMovimientos.iterator(); iterator.hasNext();) {
			 Movimiento movimiento = (Movimiento) iterator.next();
			 if(movimiento.getTipo().equals(TipoMovimiento.INGRESO)) {
				 ingreso += movimiento.getCantidad();
			 }
		 }
		 return ingreso;
	}

	public static String obtenerSaldoDeMovimientosFormateado(List<Movimiento> listaMovimientos){
		double balance = 0;
		for (Iterator<Movimiento> iterator = listaMovimientos.iterator(); iterator.hasNext();) {
			Movimiento movimiento = (Movimiento) iterator.next();
			if(movimiento.getTipo().equals(TipoMovimiento.INGRESO)) {
				balance += movimiento.getCantidad();
			}else {
				balance -= movimiento.getCantidad();
			}
		}
		DecimalFormat df = new DecimalFormat("#.##");
		return df.format(balance);
	}

	public static double obtenerSaldoDeMovimientos(List<Movimiento> listaMovimientos){
		double balance = 0;
		for (Iterator<Movimiento> iterator = listaMovimientos.iterator(); iterator.hasNext();) {
			Movimiento movimiento = (Movimiento) iterator.next();
			if(movimiento.getTipo().equals(TipoMovimiento.INGRESO)) {
				balance += movimiento.getCantidad();
			}else {
				balance -= movimiento.getCantidad();
			}
		}
		return balance;
	}

	public static double obtenerGastos(List<Movimiento> listaMovimientos) {
		double gastos = 0;
		for (Iterator<Movimiento> iterator = listaMovimientos.iterator(); iterator.hasNext();) {
			Movimiento movimiento = (Movimiento) iterator.next();
			if(movimiento.getTipo().equals(TipoMovimiento.GASTO)) {
				gastos += movimiento.getCantidad();
			}
		}
		return gastos;
	}

	public static String enmascararNumeroTarjeta(Long numeroTarjeta) {
		String numeroTarjetaString ;			
		if(numeroTarjeta != null) {
			numeroTarjetaString = String.valueOf(numeroTarjeta);
		}else {
			numeroTarjetaString = "";
		}
		
		String tarjetaMascarade = numeroTarjetaString.replaceAll("\\d(?=\\d{4})", "*");
		
		for (int i = 0; 8 < tarjetaMascarade.length(); i++) {
			tarjetaMascarade = tarjetaMascarade.substring(1);
		}
		
		return tarjetaMascarade;
		
	}
	
	public static Boolean isPrincipal(Usuario usuario) {
		//TODO obtener usuario logueado
		Long idUsuarioPrincipal = null;
        if(usuario.getId().equals(idUsuarioPrincipal)) {
        	return true;
        }else {
        	return false;
        }
	}
	
	public static Double obtenerSaldoEnCuenta(Long idCuenta, Long idUsuario, UsuarioCuentaService usuarioCuentaService) {
		
		Usuario u = new Usuario();
		u.setId(idUsuario);
		
		Cuenta c = new Cuenta();
		c.setId(idCuenta);
		
		UsuarioCuentaId uc = new UsuarioCuentaId();
		uc.setCuenta(c);
		uc.setUsuario(u);
		UsuarioCuenta ucObtenido = null;
		if(usuarioCuentaService.obtenerDatosUsuarioCuenta(uc).isPresent()) {
			ucObtenido = usuarioCuentaService.obtenerDatosUsuarioCuenta(uc).get();			
			return ucObtenido.getSaldoEnCuenta();
		}else {
			return -1d;
		}
		
		
	}
	
	public static String formatearSaldo(Double saldo) {
		DecimalFormat df = new DecimalFormat("#.##");
	    String saldoFormateado = df.format(saldo);
		return saldoFormateado;
	}
	
	public static String encriptContrasena(String contrasena) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(contrasena);
	}
	
	public static boolean validarContrasena(String contrasenaFront, String contrasenaGuardada) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.matches(contrasenaFront, contrasenaGuardada);
		
	}

}
