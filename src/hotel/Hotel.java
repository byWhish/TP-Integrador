package hotel;

import java.util.ArrayList;
import java.util.Collection;

/** La clase Hotel modela hoteles, los cuales se hubican en ciudades. Los hoteles ofrecen sus habitaciones para
 * el alojamiento de pasajeros, los cuales reservan habitaciones a través de un Sistema web. 
 * 
 *  Un Hotel esta ubicado en una determinada ciudad y posee un conjunto de Habitaciones y Servicios.*/
public class Hotel {
	
	private String nombre;
	private String pais;
	private String ciudad;
	private String direccion;
	private String telefono;
	private String correoElectronico;
	private int categoria;
	private String horaCheckIn;
	private String horaCheckOut;
	private Collection<Habitacion> habitaciones;
	private Collection<Servicio> servicios;
	private Collection<String> formasDePago;
	
	
	
	public Hotel(){
		
	}
	
	/** Constructor de la clase Hotel.
	 * Recibe como parametros unas habitaciones (Collection<Habitacion>), servicios (Collection<Servicio>) ofrecidos y una ciudad (String).*/	
	public Hotel(Collection<Habitacion> habitaciones, Collection<Servicio> servicios, String ciudad) {
		this.habitaciones = habitaciones;
		this.servicios = servicios;
		this.ciudad = ciudad;
	}
	
	
	/** Dado unaHabitacion, se la agrega a la lista de habitaciones del Hotel recibidor.*/
	public void agregarHabitacion(Habitacion unaHabitacion) {
		this.habitaciones.add(unaHabitacion);
	}
	
	/** Dado unServicio, se lo agrega a la lista de Servicios ofrecidos por el Hotel recibidor.*/
	public void agregarNuevoServicio(Servicio unServicio) {
		this.servicios.add(unServicio);
	}
	
	/** Se responde con la lista de habitaciones del hotel.*/ 
	public Collection<Habitacion> getHabitaciones(){
		return this.habitaciones;
	}
	
	/** Se responde con la ciudad en donde está ubicado el hotel.*/
	public String getCiudad(){
		return this.ciudad;
	}
	
	/** Se responde con la lista de servicios que el Hotel ofrece.*/
	public Collection<Servicio> getServicios() {
		return this.servicios;
	}
}
