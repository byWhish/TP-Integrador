package usuario;

import java.util.ArrayList;
import java.util.Collection;

import org.joda.time.DateTime;

import filtro.FiltroComponente;
import filtro.FiltroSimpleHotelero;
import hotel.Hotel;
import sistema.Reserva;

public class Hotelero extends Usuario{

	public Hotel hotel;
	
	/** Constructor de Hotelero que recibe como parámetro un eMail (String) y un pass (String).
	 * @param eMail String
	 * @param pass String
	 * @param hotelAdministrado Hotel
	 * @author abel*/
	public Hotelero(String eMail, String pass, Hotel hotelAdministrado) {
		super(eMail, pass);
		this.hotel = hotelAdministrado;
		
	}
	
	//devuelvo las reservas a partir de N dias
	public Collection<Reserva> reservasAPartirDeNDias( int cantidadDias, Collection<Reserva> reservas ){
		DateTime toDay = new DateTime();
		return reservasAPartirDeFecha( toDay.plusDays( cantidadDias ), reservas );		
	}
	
	
	/** Se responde con las reservas que están siendo actualmente ocupadas en el Hotel
	 * administrado por el Hotelero.*/
	public Collection<Reserva> reservasActuales( Collection<Reserva> reservas ){
		Collection<Reserva> resultReservas = new ArrayList<Reserva>();
		DateTime toDay = new DateTime();
		reservas = this.getReservas( reservas );
			for ( Reserva unaReserva : reservas ){
				
				//MODIFICACION abel - correcion if...
				if ( unaReserva.periodoDeLaReserva().estaIncluidoEnElPeriodoLaFecha(toDay) ){
					resultReservas.add( unaReserva );
				}
			}
		return resultReservas;
	}
	
	

	@Override
	//condicion particular para obtener las reservas de un hotelero
	//como la reserva conoce al hotel lo comparo con el hotel que conoce el hotelero
	public boolean reservaDelUsuario(Reserva reserva) { 
		return reserva.getHotel() == hotel;
	}
	
	public Hotel getHotel(){
		return this.hotel;
	}

	@Override
	public FiltroComponente obtenerFiltroSimpleUsuario() {
		FiltroSimpleHotelero myFiltro = new FiltroSimpleHotelero(this);
		return myFiltro;
	}

}
