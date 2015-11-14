package usuario;

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
	
	public abstract Collection<Reserva> getReservas( Collection<Reserva> reservas );

}
