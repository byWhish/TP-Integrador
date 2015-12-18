package sistema;

import java.security.Permissions;
import java.util.ArrayList;
import java.util.Collection;

import org.joda.time.DateTime;

import filtro.FiltroComponente;
import filtro.FiltroCompuesto;
import filtro.FiltroSimpleCantidadPasajeros;
import filtro.FiltroSimpleCiudad;
import filtro.FiltroSimpleFechasFuturas;
import hotel.Habitacion;
import hotel.Hotel;
import hotel.PeriodoDeFechas;


public class Buscador {
	private String ciudad;
	private int nroPasajeros;
	private DateTime fechaEntrada;
	private DateTime fechaSalida;
	private FiltroCompuesto myFiltro;
	private Collection<Hotel> hoteles;
	private Collection<Reserva> reservas;
		
	/*MODIFICACION - abel
	 * - CheckIn es el horario en el que el pasajero debe presentarse en el hotel para hacer uso de la reserva,
	 * no la fecha de entrada;
	 * - CheckOut, IDEM CheckIn
	 * - Agregue el sistema para solucionar el tema de habitacionCumple(Habitacion). El mayor problema que hay aca es que
	 * 	 se necesita la lista de reservas del sistema para saber si una habitacion está o no disponible.*/
	public Buscador( DateTime fechaEntrada, DateTime fechaSalida, Integer cantPasajeros, String  city,
			Collection<Hotel> hoteles, Collection<Reserva> reservas){		
		this.fechaEntrada = fechaEntrada; 
		this.fechaSalida = fechaSalida;
		this.nroPasajeros = cantPasajeros;
		this.ciudad = city;
		this.hoteles = hoteles;
		this.reservas = reservas;
		
		myFiltro = new FiltroCompuesto();
		myFiltro.componerFiltro(new FiltroSimpleCiudad(this.ciudad));
		myFiltro.componerFiltro(new FiltroSimpleCantidadPasajeros(this.nroPasajeros));
	}
	
		
	public Collection<Hotel> buscarHoteles(){
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
			if ( myFiltro.cumple( h ) && habitacionEstaDisponibleParaReserva(h, fechaEntrada, fechaSalida )){
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
				if(myFiltro.cumple(h) && habitacionEstaDisponibleParaReserva(h, fechaEntrada, fechaSalida )){
					return true; 
				}
			}
		}
		return false; 
	}
	
	/** Se responde si unaHabitacion está disponible para ser reservada a partir desde una fechaInicio hasta una fechaFin.
	 * @author abel*/
	public boolean habitacionEstaDisponibleParaReserva(Habitacion unaHabitacion, DateTime fechaInicio, DateTime fechaFin) {
		return 	unaHabitacion.noEstaCanceladaParaLasFechas(fechaInicio,fechaFin) &&
				! this.habitacionHaSidoReservada(unaHabitacion, fechaInicio, fechaFin);
	}

	
	/** Se responde si unaHabitacion tiene alguna reserva realizada en el sistema para alguna de las fechas del periodo
	 * que va desde la fechaInicio hasta la fechaFin.
	 * @author abel*/
	private boolean habitacionHaSidoReservada(Habitacion unaHabitacion, DateTime fechaInicio, DateTime fechaFin) {
		for(Reserva unaReserva: reservas ) {
			if(	unaReserva.getHabitacion() == unaHabitacion &&
				unaReserva.periodoDeLaReserva().seIntersectaConElPeriodo(fechaInicio, fechaFin)) {
				return true;
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

	public int getCantidadPasajeros() {
		return this.nroPasajeros;
	}
	
	public Collection<Reserva> getReservas(){
		return this.reservas;
	}
		
}
