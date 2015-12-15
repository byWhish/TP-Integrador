package sistema;

import java.security.Permissions;
import java.util.ArrayList;
import java.util.Collection;

import org.joda.time.DateTime;

import filtro.FiltroCompuesto;
import filtro.FiltroSimpleCantidadPasajeros;
import filtro.FiltroSimpleCiudad;
import filtro.FiltroSimpleFechas;
import hotel.Habitacion;
import hotel.Hotel;
import hotel.PeriodoDeFechas;


public class Buscador {
	private String ciudad;
	private int nroPasajeros;
	private DateTime fechaEntrada;
	private DateTime fechaSalida;
	private Sistema sistema;
	private FiltroCompuesto myFiltro = new FiltroCompuesto();
		
	/*MODIFICACION - abel
	 * - CheckIn es el horario en el que el pasajero debe presentarse en el hotel para hacer uso de la reserva,
	 * no la fecha de entrada;
	 * - CheckOut, IDEM CheckIn
	 * - Agregue el sistema para solucionar el tema de habitacionCumple(Habitacion). El mayor problema que hay aca es que
	 * 	 se necesita la lista de reservas del sistema para saber si una habitacion está o no disponible.*/
	public Buscador( DateTime fechaEntrada, DateTime fechaSalida, Integer cantPasajeros, String  city, Sistema sistema){		
		this.fechaEntrada = fechaEntrada; 
		this.fechaSalida = fechaSalida;
		this.nroPasajeros = cantPasajeros;
		this.ciudad = city;
		
		myFiltro.componerFiltro(new FiltroSimpleCiudad(this.ciudad));
		myFiltro.componerFiltro(new FiltroSimpleCantidadPasajeros(this.nroPasajeros));
		myFiltro.componerFiltro(new FiltroSimpleFechas( new PeriodoDeFechas(this.fechaEntrada, this.fechaSalida)));
	}
	
		
	public Collection<Hotel> buscarHoteles( Collection<Hotel> hoteles ){
		Collection<Hotel> resultHoteles = new ArrayList<Hotel>();
		for ( Hotel h : hoteles ){
			if ( hotelCumple( h ) ){
				resultHoteles.add( h );
			}
		}
		return resultHoteles;
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
	
		
	//tiene que cumplir disponibilidad, capacidad y ciudad
	public boolean hotelCumple( Hotel hotel){
		
		if(hotel.getCiudad() == ciudad ){
			// pregunta a cada habitacion del hotel si cumple la condicion o no 
			for(Habitacion h : hotel.getHabitaciones()){
				if(myFiltro.cumple(h)){
					return true; 
				}
			}
		}
		return false; 
	}
		
		
	//tiene que cumplir disponibilidad y capacidad
	public boolean habitacionCumple( Habitacion habitacion ){
		return (sistema.habitacionEstaDisponibleParaReserva(habitacion, fechaEntrada, fechaSalida) && 
				habitacion.getCapacidadMaxima() >= nroPasajeros);
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
