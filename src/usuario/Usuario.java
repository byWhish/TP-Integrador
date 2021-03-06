package usuario;



import java.util.ArrayList;
import java.util.Collection;

import org.joda.time.DateTime;

import filtro.FiltroComponente;
import sistema.Reserva;
import sistema.Sistema;

public abstract class Usuario {

	private String eMail;
	private String pass;
	
	/** Constructor de Usuario que recibe como par�metros una direcci�n de e-mail y una contrase�a.*/
	public Usuario( String eMail, String pass){
		this.eMail = eMail;
		this.pass = pass;
	}
	
	/** Se responde con el e-mail del Usuario recibidor.*/
	public String getEmail(){
		return this.eMail;
	}
	
	/** Se responde con el password del Usuario recibidor.*/
	public String getPass(){
		return this.pass;
	}
	
	//getReservas devuelve todas las reservas de un usuario que cumpla la condicion que 
		//puede ser diferente para cada clase de usuario
		public Collection<Reserva> getReservas( Sistema s ){
		 
			return s.getReservasDelUsuario(this);
		}
	
	//reserva del usuario es la condicion para saber si la reserva le pertenece al usuario
	public abstract boolean reservaDelUsuario( Reserva reserva );
	
	//devuelvo una coleccion con las reservas futuras a la fecha actual
	public Collection<Reserva> reservasFuturas( Collection<Reserva> reservas){
			
		DateTime toDay = new DateTime();
			
		return this.reservasAPartirDeFecha( toDay, reservas); 
	}
		
		//devuelvo una coleccion con las reservas a partir de una fecha dada
		// "reservas" ya son las reservas del usuario  
	public Collection<Reserva> reservasAPartirDeFecha( DateTime fecha, Collection<Reserva> reservas ){
		Collection<Reserva> resultReservas = new ArrayList<Reserva>();
		for ( Reserva r : reservas ){
			if ( r.getFechaEntrada().isAfter( fecha ) ){
				resultReservas.add( r );
			}
		}
		return resultReservas;
	}
	
	//con esto obtengo el filtro indicado para la sunclase que corresponde
	public abstract FiltroComponente obtenerFiltroSimpleUsuario();

}
