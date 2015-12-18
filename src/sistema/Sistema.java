package sistema;

import java.util.ArrayList;
import java.util.Collection;

import org.joda.time.DateTime;

import filtro.FiltroComponente;
import filtro.FiltroSimplePasajero;
import hotel.Habitacion;
import hotel.Hotel;
import usuario.Pasajero;
import usuario.Usuario;

public class Sistema {
	
	private Collection<Usuario> usuarios = new ArrayList<Usuario>();
	private Collection<Reserva> reservas = new ArrayList<Reserva>();
	private Collection<Hotel> hoteles = new ArrayList<Hotel>();
	
		
	public Sistema(){
	
	}
	
	public void registrarReserva(Reserva r){
		reservas.add(r);
	}
	
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
		return new Buscador(fechaEntrada, fechaSalida, cantidadPasajeros, ciudad, hoteles, reservas);
	}
	
	
	/** Dados una habitaci�n, un pasajero y una busqueda, que contiene los par�metros de b�squeda del pasajero, se intenta
	 * realizar una nueva reserva para la habitaci�n dada. Si la habitaci�n est� disponible para ser reservada, se realiza
	 * la reserva y se la guarda en la lista de reservas del sistema.
	 * 
	 * 	Una habitaci�n est� disponible para ser reservada si y solo si se cumplen las siguientes condiciones:
	 * 	- La habitaci�n no ha sido reservada por otro Usuario para ninguna de las fechas almacenadas en los par�metros
	 * 	de b�squeda;
	 * 	- La habitaci�n puede ser reservable para las fechas dadas, es decir, el hotelero que administra la habitaci�n
	 * 	permite que la habitaci�n pueda ser reservada para esas fechas.
	 * @author �?
	 * */ 
	public void reservarHabitacion( Habitacion unaHabitacion, Usuario unPasajero, Buscador unaBusqueda ){
		//MODIFICACION abel - correccion if
		if ( unaBusqueda.habitacionEstaDisponibleParaReserva(unaHabitacion, unaBusqueda.getFechaIngreso(), unaBusqueda.getFechaSalida()) ){
			Reserva nuevaReserva = new Reserva( unaBusqueda.getFechaIngreso(), unaBusqueda.getFechaSalida(), unaBusqueda.getCantidadPasajeros(), unPasajero, unaHabitacion );
			registrarReserva( nuevaReserva );
		}
	}
	
	/** Se responde con la colecci�n de todas las reservas realizadas en el Sistema.
	 * @author abel*/
	public Collection<Reserva> getReservas() {
		return this.reservas;
	}
	
	public Collection<Hotel> getHoteles(){
		return this.hoteles;
	}
	
	//con esto obtengo todas las reservas de un usuario 
	public Collection<Reserva> getReservasDelUsuario( Usuario usuario ){
		Collection<Reserva> resultReservas = new ArrayList<Reserva>();
		
		FiltroComponente myFiltro = usuario.obtenerFiltroSimpleUsuario();
		
		for ( Reserva r : this.getReservas()){
			if ( myFiltro.cumple(r)){
				resultReservas.add(r);
			}
		}
		return resultReservas;
	}
	
	
}
