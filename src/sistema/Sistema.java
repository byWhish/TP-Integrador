package sistema;

import java.util.ArrayList;
import java.util.Collection;

import org.joda.time.DateTime;

import hotel.Habitacion;
import usuario.Usuario;

public class Sistema {
	
	private Collection<Usuario> usuarios;
	private Collection<Reserva> reservas;
	
	
	//constructor de la clase solo seteo las listas
	public Sistema(){
		this.usuarios = new ArrayList<Usuario>();
		this.reservas = new ArrayList<Reserva>();
	}
	
	//agrego un usuario al sistema
	public void agregarUsuario( Usuario usuario ){
		usuarios.add( usuario );
	}
	
	//agrego una reserva al sistema
	public void agregarReserva( Reserva reserva ){
		reservas.add( reserva );
	}
	
	public void cancelarReserva( Reserva reserva ){
		reservas.remove( reserva );
	}
	
	public Usuario logIn( String eMail, String pass ){
		
		Usuario resultUsuario = null;
		
		for( Usuario u : this.usuarios ){
			if ( u.getEmail() == eMail  &&  u.getPass() == pass ){
				resultUsuario = u;
			}
		} 
		return resultUsuario;
	}
	
	
	//para poder concretar la reserva necesito verifcar que no este ya reservada
	public void reservarHabitacion( Habitacion habitacion, Usuario pasajero, Buscador busqueda ){
		
		if ( habitacion.reservableParaLasFechas(busqueda.getFechaIngreso(), busqueda.getFechaSalida() )){
		
		Reserva newReserva = new Reserva( busqueda.getFechaIngreso(), busqueda.getFechaSalida(), busqueda.getCantidadPasajeros(), pasajero, habitacion );
		
		reservas.add( newReserva );
	}

}
	
}
