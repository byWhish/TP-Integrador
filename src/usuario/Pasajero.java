package usuario;

import java.util.ArrayList;
import java.util.Collection;

import sistema.Reserva;

public class Pasajero extends Usuario{

	public Pasajero(String eMail, String pass) {
		super(eMail, pass);
	}

	public Collection<Reserva> getReservas( Collection<Reserva> reservas ){
		
		Collection<Reserva> resultReserva = new ArrayList<Reserva>();
		
		for( Reserva r : reservas){
			if ( r.getFechaEntrada().isAfterNow() ){
				resultReserva.add(r);
			}
		}
		return resultReserva; 
	}

	public Collection<String> ciudadesConReservas( Collection<Reserva> reservas ){
		
		Collection<String> returnCiudades = new ArrayList<String>();
		
		for ( Reserva r : reservas){
			
		}
		
		return returnCiudades;
	}
}
