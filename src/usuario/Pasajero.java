package usuario;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import hotel.Hotel;
import sistema.Reserva;

public class Pasajero extends Usuario{

	public Pasajero(String eMail, String pass) {
		super(eMail, pass);
	}

	public boolean reservaDelUsuario( Reserva reserva ){
		
			return ( reserva.getUsuario() == this );
		}
	
	public Collection<Reserva> reservasFuturas( Collection<Reserva> reservas){
		
		Collection<Reserva> resultReserva = new ArrayList<Reserva>();
		
		for( Reserva r : reservas){
			if ( r.getFechaEntrada().isAfterNow() ){
				resultReserva.add(r);
			}
		}
		return resultReserva; 
	}

	//aca recorro las reservas y guardo en un set para que no se repitan
	public Collection<String> ciudadesConReservas( Collection<Reserva> reservas ){
		
		Collection<String> returnCiudades = new HashSet<String>();
		
		for ( Reserva r : reservas ){
				returnCiudades.add( r.ciudadDeReserva() );
			}
		
		return returnCiudades;
	}
	
	public String ciudadDeReserva( Reserva reserva ){
		return reserva.ciudadDeReserva();
	}

}
