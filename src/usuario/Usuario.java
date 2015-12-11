package usuario;

import java.util.ArrayList;
import java.util.Collection;

import org.joda.time.DateTime;

import sistema.Reserva;

public abstract class Usuario {

	private String eMail;
	private String pass;
	
	/** Constructor de Usuario que recibe como parámetros una dirección de e-mail y una contraseña.*/
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
	public Collection<Reserva> getReservas( Collection<Reserva> reservas ){
		Collection<Reserva> resultReserva = new ArrayList<Reserva>();
		for ( Reserva r : reservas ){
			if ( reservaDelUsuario( r ) ){
				resultReserva.add( r );
			}  
		} 
		return resultReserva;
	}
	
	//reserva del usuario es la condicion para saber si la reserva le pertenece al usuario
	public abstract boolean reservaDelUsuario( Reserva reserva );
	
	//devuelvo una coleccion con las reservas futuras a la fecha actual
	public Collection<Reserva> reservasFuturas( Collection<Reserva> reservas){
			
		DateTime toDay = new DateTime();
			
		return this.reservasAPartirDeFecha( toDay, reservas); 
	}
		
		//devuelvo una coleccion con las reservas a partir de una fecha dada
	public Collection<Reserva> reservasAPartirDeFecha( DateTime fecha, Collection<Reserva> reservas ){
		Collection<Reserva> resultReservas = new ArrayList<Reserva>();
		reservas = getReservas( reservas );
		
		for ( Reserva r : reservas ){
			if ( r.getFechaEntrada().isAfter( fecha ) ){
				resultReservas.add( r );
			}
		}
		return resultReservas;
	}

}
