package hotel;

import java.util.ArrayList;
import java.util.Collection;

import sistema.Filtrable;

/** La clase Hotel modela hoteles, los cuales se hubican en ciudades. Los hoteles ofrecen sus habitaciones para
 * el alojamiento de pasajeros, los cuales reservan habitaciones a través de un Sistema web. 
 * 
 *  Un Hotel esta ubicado en una determinada ciudad y posee un conjunto de Habitaciones y Servicios.
 *  @author abel*/
public class Hotel implements Filtrable{
	
	private String nombre;
	private String pais;
	private String ciudad;
	private String direccion;
	private String telefono;
	private String email;
	private Integer categoria;
	private Integer checkIn;
	private Integer checkOut;
	private Collection<Habitacion> habitaciones = new ArrayList<Habitacion>();
	private Collection<Servicio> servicios = new ArrayList<Servicio>();
	private Collection<String> formasDePago = new ArrayList<String>();
	
	
	
	public Hotel(){
		super();
	}
	
	/** Constructor de la clase Hotel.
	 * @param nombre String
	 * @param pais String
	 * @param ciudad String
	 * @param direccion String
	 * @param telefono String
	 * @param email String
	 * @param categoria Integer
	 * @param checkin Integer
	 * @param checkout Integer
	 * @author abel*/	
	public Hotel(String nombre, String pais, String ciudad, String direccion, String telefono,
				 String email, Integer categoria, Integer checkin, Integer checkout) {
		this.nombre = nombre;
		this.pais = pais;
		this.ciudad = ciudad;
		this.direccion = direccion;
		this.telefono = telefono;
		this.email = email;
		this.categoria = categoria;
		this.checkIn = checkin;
		this.checkOut = checkout;
	}
	
	
	/** Dado unaHabitacion, se la agrega a la lista de habitaciones del Hotel recibidor.
	 * @author abel*/
	public void agregarHabitacion(Habitacion unaHabitacion) {
		this.habitaciones.add(unaHabitacion);
	}
	
	/** Dado unServicio, se lo agrega a la lista de Servicios ofrecidos por el Hotel recibidor.
	 * @author abel*/
	public void agregarNuevoServicio(Servicio unServicio) {
		this.servicios.add(unServicio);
	}
	
	/** Dada una formaDePago, se la agrega a la lista de formas de pago aceptadas por el Hotel
	 * recibidor.
	 * @author abel*/
	public void agregarUnaNuevaFormaDePagoAceptada(String unaFormaDePago) {
		this.formasDePago.add(unaFormaDePago);
	}
	
//GettersYSetters----------------------------------------------------------------------
	
	/** Se responde con el nombre del hotel.
	 * @author abel*/
	public String getNombre(){
		return this.nombre;
	}
	
	/** Se responde con el país en donde está ubicado el hotel.
	 * @author abel*/
	public String getPais(){
		return this.pais;
	}
		
	/** Se responde con la ciudad en donde está ubicado el hotel.
	 * @author abel*/
	public String getCiudad(){
		return this.ciudad;
	}
	
	/** Se responde con la dirección en donde está ubicado el hotel.
	 * @author abel*/
	public String getDireccion(){
		return this.direccion;
	}
	
	/** Se responde con el teléfono del hotel.
	 * @author abel*/
	public String getTelefono(){
		return this.telefono;
	}
	
	/** Se responde con el correo electrónico del hotel.
	 * @author abel*/
	public String getEmail(){
		return this.email;
	}
	
	/** Se responde con la categoría (cantidad de estrellas) del hotel.
	 * @author abel*/
	public String getCategoria(){
		return this.categoria+" estrellas.";
	}
	
	/** Se responde con el horario de check-in del hotel.
	 * El horario de check-in es el horario en el que la persona que realizó una reserva para
	 * una habitación debe presentarse en el hotel para hacer uso de dicha habitación.
	 * @author abel*/
	public Integer getHorarioDeCheckIn(){
		return this.checkIn;
	}
	
	/** Se responde con el horario de check-out del hotel.
	 * El horario de check-out es el horario en el que la persona que ocupa una reserva de
	 * una habitación en el hotel debe liberar dicha habitación.
	 * @author abel*/
	public Integer getHorarioDeCheckOut(){
		return this.checkOut;
	}
	
	/** Se responde con la lista de habitaciones del hotel.
	 * @author abel*/ 
	public Collection<Habitacion> getHabitaciones(){
		return this.habitaciones;
	}
	
	/** Se responde con la lista de servicios que el Hotel ofrece.
	 * @author abel*/
	public Collection<Servicio> getServicios() {
		return this.servicios;
	}
	
	/** Se responde con la lista de formas de pago aceptadas por el Hotel.
	 * @author abel*/
	public Collection<String> getFormasDePagoAceptadas() {
		return this.formasDePago;
	}

	@Override
	public boolean cumple(String ciudad) {

		return this.getCiudad() == ciudad;
	}

//finGettersYSetters--------------------------------------------------------------------
}
