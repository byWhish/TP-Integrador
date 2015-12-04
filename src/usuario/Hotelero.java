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
	
	public Collection<Reserva> reservasAPartirDeNDias( int cantidadDias, Collection<Reserva> reservas ){
		
		DateTime toDay = new DateTime();
		
		return reservasAPartirDeFecha( toDay.plusDays( cantidadDias ), reservas );
		
	}
	
	

	@Override
	//condicion particular para obtener las reservas de un hotelero
	//como la reserva conoce al hotel lo comparo con el hotel que conoce el hotelero
	public boolean reservaDelUsuario(Reserva reserva) { 
		return reserva.getHotel() == this.hotel;
	}

}
