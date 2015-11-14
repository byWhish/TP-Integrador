package usuario;

import java.util.ArrayList;
import java.util.Collection;

import hotel.Habitacion;
import hotel.Hotel;
import sistema.Reserva;

public class Hotelero extends Usuario{

	//private Hotel hotel;
	
	public Hotel hotel;
	public Hotelero(String eMail, String pass) {
		super(eMail, pass);
		
		
	}

	@Override
	public Collection<Reserva> getReservas(Collection<Reserva> reservas) {
		
		Collection<Reserva> resultReservas = new ArrayList<Reserva>();
		
		return resultReservas;
	}
	
	public Collection<Reserva> reservasFuturas(Collection<Reserva> reservas) {
		
		reservas = getReservas( reservas );
		
		Collection<Reserva> resultReservas = new ArrayList<Reserva>(); 
		
		for( Reserva r : reservas){
			//esto me dice que reservas tienen habitaciones del hotelero
			if( this.hotel.getHabitaciones().contains( r.getHabitacion() ) )
			resultReservas.add( r );
		}
		
		return resultReservas;
	}

}
