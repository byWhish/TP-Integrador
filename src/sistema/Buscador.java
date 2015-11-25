package sistema;

import java.util.ArrayList;
import java.util.Collection;

import org.joda.time.DateTime;

import hotel.Habitacion;
import hotel.Hotel;

public class Buscador {
	
	private DateTime fechaIngreso;
	private DateTime fechaSalida;
	private Integer cantidadPasajeros;
	private String ciudad;
	
	public Buscador( DateTime checkIn, DateTime checkOut,Integer cantPasajeros,String  city ){
		
		this.fechaIngreso = checkIn;
		this.fechaSalida = checkOut;
		this.cantidadPasajeros = cantPasajeros;
		this.ciudad = city;
	}
	
	public DateTime getFechaIngreso(){
		return this.fechaIngreso;
	}
	
	public DateTime getFechaSalida(){
		return this.fechaSalida;
	}
	
	public Integer getCantidadPasajeros(){
		return this.cantidadPasajeros;
	}
	
	public String getCiudad(){
		return this.ciudad;
	}
	
	//tiene que cumplir disponibilidad y capacidad
	public boolean habitacionCumple( Habitacion habitacion ){
		return true;
	}
	
	//tiene que cumplir disponibilidad, capacidad y ciudad
	public boolean hotelCumple( Hotel hotel){
		return true;
	}
	
	public Collection<Habitacion> buscarHabitaciones( Hotel hotel ){
		Collection<Habitacion> resultHabitaciones = new ArrayList<Habitacion>();
		for ( Habitacion h : hotel.getHabitaciones() ){
			if ( habitacionCumple( h ) ){
				resultHabitaciones.add( h );
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

}
