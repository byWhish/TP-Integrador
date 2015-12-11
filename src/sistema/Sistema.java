package sistema;

import java.util.ArrayList;
import java.util.Collection;

import org.joda.time.DateTime;

import hotel.Habitacion;
import hotel.Hotel;
import usuario.Usuario;

public class Sistema {
	
	private Collection<Usuario> usuarios = new ArrayList<Usuario>();
	private Collection<Reserva> reservas = new ArrayList<Reserva>();
	private Collection<Hotel> hoteles = new ArrayList<Hotel>();
	
		
	//agrego un usuario al sistema
	public void registrarUsuario( Usuario usuario ){
		usuarios.add( usuario );
	}
	
	
	/** Dado unHotel, se lo registra agregandoselo a la lista de hoteles del sistema.
	 * @param unHotel Hotel
	 * @author abel*/
	public void registrarHotel(Hotel unHotel) {
		this.hoteles.add(unHotel);
	}
	
	public void cancelarReserva( Reserva reserva ){
		reservas.remove( reserva );
	}
	
	
	//public Reserva modificarReserva ( reserva Reserva, Hotel hotel habitacion )
	public Usuario logIn( String eMail, String pass ){	
		Usuario resultUsuario = null;
			for( Usuario u : this.usuarios ){
				if ( u.getEmail() == eMail  &&  u.getPass() == pass ){
					resultUsuario = u;
				}
			}
		return resultUsuario;
	}
	
	
	/** Dados una fechaEntrada, una fechaSalida, una cantidadPasajeros y una ciudad, se instancia y reponde con una 
	 * nueva busqueda para buscar habitaciones en hoteles.
	 * @author abel*/
	public Buscador nuevaBusqueda(DateTime fechaEntrada, DateTime fechaSalida, Integer cantidadPasajeros, String ciudad) {
		return new Buscador(fechaEntrada, fechaSalida, cantidadPasajeros, ciudad, this);
	}
	
	
	/** Dados una habitación, un pasajero y una busqueda, que contiene los parámetros de búsqueda del pasajero, se intenta
	 * realizar una nueva reserva para la habitación dada. Si la habitación está disponible para ser reservada, se realiza
	 * la reserva y se la guarda en la lista de reservas del sistema.
	 * 
	 * 	Una habitación está disponible para ser reservada si y solo si se cumplen las siguientes condiciones:
	 * 	- La habitación no ha sido reservada por otro Usuario para ninguna de las fechas almacenadas en los parámetros
	 * 	de búsqueda;
	 * 	- La habitación puede ser reservable para las fechas dadas, es decir, el hotelero que administra la habitación
	 * 	permite que la habitación pueda ser reservada para esas fechas.
	 * @author ¿?
	 * */ 
	public void reservarHabitacion( Habitacion unaHabitacion, Usuario unPasajero, Buscador unaBusqueda ){
		//MODIFICACION abel - correccion if
		if ( this.habitacionEstaDisponibleParaReserva(unaHabitacion, unaBusqueda.getFechaIngreso(), unaBusqueda.getFechaSalida()) ){
			Reserva nuevaReserva = new Reserva( unaBusqueda.getFechaIngreso(), unaBusqueda.getFechaSalida(), unaBusqueda.getCantidadPasajeros(), unPasajero, unaHabitacion );
			getReservas().add( nuevaReserva );
		}
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
		for(Reserva unaReserva: this.getReservas() ) {
			if(	unaReserva.getHabitacion() == unaHabitacion &&
				unaReserva.periodoDeLaReserva().seIntersectaConElPeriodo(fechaInicio, fechaFin)) {
				return true;
			}
		}
		return false;
	}

	
	/** Se responde con la colección de todas las reservas realizadas en el Sistema.
	 * @author abel*/
	private Collection<Reserva> getReservas() {
		return this.reservas;
	}
}
