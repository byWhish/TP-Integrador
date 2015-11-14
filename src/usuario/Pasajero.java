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

	public Collection<Reserva> getReservas( Collection<Reserva> reservas ){
		
		Collection<Reserva> resultReserva = new ArrayList<Reserva>();
		
		for( Reserva r : reservas){
			if ( r.getFechaEntrada().isAfterNow() ){
				resultReserva.add(r);
			}
		}
		return resultReserva; 
	}

	//como las reservas solo tienen la habitacion y la habitacion no conoce su hotel tengo 
	//que pedir los hoteles para linkear con las habitaciones y asi poder obtener las ciudades
	public Collection<String> ciudadesConReservas( Collection<Reserva> reservas, Collection<Hotel> hoteles ){
		
		Collection<String> returnCiudades = new HashSet<String>();
		
		for ( Reserva r : reservas ){
			for ( Hotel h : hoteles ){
				if ( h.getHabitaciones().contains( r )  ){
					returnCiudades.add( h.getCiudad() );
				};
			}
		}
		
		return returnCiudades;
	}
}
