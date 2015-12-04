package usuario;

import java.util.ArrayList;
import java.util.Collection;

import org.joda.time.DateTime;

import hotel.Habitacion;
import hotel.Hotel;
import sistema.Reserva;

public class Hotelero extends Usuario{

	//private Hotel hotel;
	
	public Hotel hotel;
	public Hotelero(String eMail, String pass) {
		super(eMail, pass);
		
		
	}
	
	//devuelvo las reservas a partir de N dias
	public Collection<Reserva> reservasAPartirDeNDias( int cantidadDias, Collection<Reserva> reservas ){
		
		DateTime toDay = new DateTime();
		
		return reservasAPartirDeFecha( toDay.plusDays( cantidadDias ), reservas );
		
	}
	
	
	//devuelvo las reservas actuales aquellas donde los pasajeros estan en el hotel actualmente
	public Collection<Reserva> reservasActuales( Collection<Reserva> reservas ){
		
		Collection<Reserva> resultReservas = new ArrayList<Reserva>();
		
		reservas = this.getReservas( reservas );
		
		for ( Reserva r : reservas ){
			if ( r.getFechaEntrada().isBeforeNow()){
				resultReservas.add( r );
			}
		}
		
		return resultReservas;
				
	}
	
	

	@Override
	//condicion particular para obtener las reservas de un hotelero
	//como la reserva conoce al hotel lo comparo con el hotel que conoce el hotelero
	public boolean reservaDelUsuario(Reserva reserva) { 
		return reserva.getHotel() == this.hotel;
	}

}
