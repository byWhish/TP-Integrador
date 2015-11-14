package sistema;

import java.util.ArrayList;
import java.util.Collection;

import org.joda.time.DateTime;

import hotel.Habitacion;
import usuario.Usuario;

public class Sistema {
	
	private Collection<Usuario> usuarios;
	private Collection<Reserva> reservas;
	
	public Sistema(){
		this.usuarios = new ArrayList<Usuario>();
		this.reservas = new ArrayList<Reserva>();
	}
	
	public void agregarUsuario( Usuario usuario ){
		usuarios.add( usuario );
	}
	
	public void agregarReserva( Reserva reserva ){
		reservas.add( reserva );
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
	
	public void reservasHabitacion( Habitacion habitacion, Usuario pasajero ){
		
		Reserva newReserva = new Reserva( new DateTime(), new DateTime(), 2, pasajero );
		
		reservas.add( newReserva );
	}

}
