import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;

import hotel.Habitacion;
import hotel.Hotel;


public class Buscador {
	String ciudad;
	int nroPasajeros;
	DateTime fechaEntrada;
	DateTime fechaSalida;
	
public Buscador( DateTime checkIn, DateTime checkOut,Integer cantPasajeros,String  city ){
		
		this.fechaEntrada = checkIn;
		this.fechaSalida = checkOut;
		this.nroPasajeros = cantPasajeros;
		this.ciudad = city;
	}
		
	public Collection<Habitacion> buscarHabitaciones( Hotel hotel ){
		Collection<Habitacion> resultHabitaciones = new ArrayList<Habitacion>();
		//for ( Habitacion h : hotel.getHabitaciones() ){
		for(int i=0; i< hotel.getHabitaciones().size(); i++){
			
			if ( habitacionCumple( hotel.getHabitaciones().get(i))){
				resultHabitaciones.add(  hotel.getHabitaciones().get(i) );
			}
		}
			
		return resultHabitaciones;
		
	}
	
	public Collection<Hotel> buscarHoteles( Collection<Hotel>hoteles ){
		Collection<Hotel> resultHoteles = new ArrayList<Hotel>();
		for ( Hotel h : hoteles ){
			if ( hotelCumple( h ) ){
				resultHoteles.add( h );
			}
		}
		return resultHoteles;
	}
	
	//tiene que cumplir disponibilidad y capacidad
		public boolean habitacionCumple( Habitacion habitacion ){
			return (habitacion.disponibilidad(fechaEntrada,fechaSalida)&& habitacion.getCapMaxima()>= nroPasajeros);
		}
		
		//tiene que cumplir disponibilidad, capacidad y ciudad
		public boolean hotelCumple( Hotel hotel){
			if(hotel.getCiudad() == ciudad ){
				// pregunta a cada habitacion del hotel si cumple la condicion o no 
				for(Habitacion h : hotel.getHabitaciones()){
					if(habitacionCumple(h)){
						return true; 
					}
			}
		}
			return false; 
	}

		public DateTime getFechaIngreso() {
			return this.fechaEntrada;
		}

		public DateTime getFechaSalida() {
			return this.fechaSalida;
		}

		public Integer getCantidadPasajeros() {
			return this.nroPasajeros;
		}
		
}
