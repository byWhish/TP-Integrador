package usuario;

import java.util.ArrayList;
import java.util.Collection;

import sistema.Reserva;

public class Hotelero extends Usuario{

	//private Hotel hotel;
	
	public Hotelero(String eMail, String pass) {
		super(eMail, pass);
		
	}

	@Override
	public Collection<Reserva> getReservas(Collection<Reserva> reservas) {
		
		Collection<Reserva> resultReservas = new ArrayList<Reserva>();
		
		for( Reserva r : reservas){
			//esto me dice que reservas tienen habitaciones del hotelero
			//if( this.hotel.habitaciones.contains( r.habitacion ) )
			resultReservas.add( r );
		}
		
		return resultReservas;
	}

}
