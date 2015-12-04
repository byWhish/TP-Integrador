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

	//esta es la condicion particular para obtener las reservas de un pasajero
	public boolean reservaDelUsuario( Reserva reserva ){
		
			return ( reserva.getUsuario() == this );
		}
	
	

	//aca recorro las reservas y guardo en un set para que no se repitan
	public Collection<String> ciudadesConReservas( Collection<Reserva> reservas ){
		
		Collection<String> returnCiudades = new HashSet<String>();
		
		for ( Reserva r : reservas ){
				returnCiudades.add( r.ciudadDeReserva() );
			}
		
		return returnCiudades;
	}
	
	//devuelvo una colleccion de reservas en una ciudad dada
	public Collection<Reserva> reservasEnCiudad( Collection<Reserva> reservas, String ciudad ){
		Collection<Reserva> resultReservas = new ArrayList();
		for ( Reserva r : reservas){
			if ( r.getCiudad() == ciudad ){
				resultReservas.add( r );
			}
		}
		return resultReservas;
	}

}
