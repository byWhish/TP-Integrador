package sistema;

import java.util.ArrayList;
import java.util.Collection;

import org.joda.time.DateTime;

import hotel.Habitacion;
import hotel.Hotel;
import usuario.Usuario;

public class Sistema {
	
	private Collection<Usuario> usuarios;
	private Collection<Reserva> reservas;
	private Collection<Hotel> hotel;
	
	
	//constructor de la clase solo seteo las listas
	public Sistema(){
		this.usuarios = new ArrayList<Usuario>();
		this.hotel = new ArrayList<Hotel>();
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
	
	//cancelo una reserva del sistema
	public void cancelarReserva( Reserva reserva ){
		reservas.remove( reserva );
	}
	
	//devuelvo una reserva con la habitacion modificada
	public Reserva modificarReserva ( Reserva reserva, Habitacion habitacion ){
		reserva.setHabitacion( habitacion );
		return reserva;
	}
	
	//devuelvo un usuario que responda a un correo y un pass
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
	//devuelvo todas las reservas del sistema
	public Collection<Reserva> getReservas(){
		return this.reservas;
	}
	
}
