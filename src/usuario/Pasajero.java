package usuario;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import filtro.FiltroComponente;
import filtro.FiltroSimplePasajero;
import sistema.Reserva;

public class Pasajero extends Usuario{

	/** Constructor de la clase Pasajero.
	 * @param eMail String
	 * @param pass String*/
	public Pasajero(String eMail, String pass) {
		super(eMail, pass);
	}

	//esta es la condicion particular para obtener las reservas de un pasajero
	public boolean reservaDelUsuario( Reserva reserva ){
		return ( reserva.getUsuario() == this );
	}
	

	//aca recorro las reservas y guardo en un set para que no se repitan
	public Collection<String> ciudadesDondeSeRealizaronReservas( Collection<Reserva> reservasDelUsuario ){

		Collection<String> returnCiudades = new HashSet<String>();
			for ( Reserva r : reservasDelUsuario ){
				returnCiudades.add( r.getCiudad() );
			}
		
		return returnCiudades;
	}
	
	//devuelvo una colleccion de reservas en una ciudad dada
	public Collection<Reserva> reservasEnUnaCiudadParticular( Collection<Reserva> reservasDelUsuario, String ciudad ){
		
		//MODIFICACION abel - obtener las reservas unicamente del usuario:
		Collection<Reserva> resultReservas = new ArrayList<Reserva>();
		for ( Reserva r : reservasDelUsuario){
			if ( r.getCiudad() == ciudad ){
				resultReservas.add( r );
			}
		}
		return resultReservas;
	}

	@Override
	public FiltroComponente obtenerFiltroSimpleUsuario() {
		FiltroSimplePasajero myFiltro = new FiltroSimplePasajero(this);
		return myFiltro;
	}

}
