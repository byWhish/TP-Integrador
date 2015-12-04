package usuario;

import java.util.ArrayList;
import java.util.Collection;

import sistema.Reserva;

public abstract class Usuario {

	private String eMail;
	private String pass;
	
	public Usuario( String eMail, String pass){
		this.eMail = eMail;
		this.pass = pass;
	}
	
	public String getEmail(){
		return this.eMail;
	}
	
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

}
