package test;

import java.util.ArrayList;
import java.util.Collection;

import org.joda.time.DateTime;

import hotel.Habitacion;
import hotel.Hotel;
import junit.framework.TestCase;
import sistema.Reserva;
import sistema.Sistema;
import usuario.Hotelero;
import usuario.Pasajero;

/** Testeo de la clase Hotelero
 * @author abel*/
public class HoteleroTest extends TestCase {
	private Sistema sistema = new Sistema();
	
	private Hotel hotelAdministrado = new Hotel(); //Hotel administrado por unHotelero
	private Hotel hotel2 = new Hotel();
	
	private Hotelero unHotelero = new Hotelero("1234", "hotelero@hotel.com", hotelAdministrado);
	private Pasajero unPasajero = new Pasajero("1234", "pasajero@hotel.com");
	
	private ArrayList<Reserva> listaDeReservas = new ArrayList<Reserva>();//Todas las reservas del sistema
	private ArrayList<Reserva> listaDeReservasDeUnHotelero = new ArrayList<Reserva>(); //Solo las reservas de unHotelero
	
	private Habitacion unaHabitacion = new Habitacion(50.0, hotelAdministrado, 4, "Dos camas"); //Habitacion del Hotel administrado por unHotelero
	private Habitacion habitacion2 = new Habitacion(50.0, hotel2, 4, "Dos camas");
	
	//Reservas de a unHotelero:
	private Reserva reservaAntigua = new Reserva(new DateTime(2014, 01, 01, 0, 0), new DateTime(2014, 01, 15, 0, 0), 5, unPasajero, unaHabitacion);
	private Reserva reservaActual = new Reserva(new DateTime(), (new DateTime()).plusDays(10), 5, unPasajero, unaHabitacion);
	private Reserva reservaFutura = new Reserva((new DateTime()).plusDays(5), (new DateTime()).plusDays(10), 5, unPasajero, unaHabitacion); 
	private Reserva reservaFuturaEnNDias = new Reserva((new DateTime()).plusDays(20), (new DateTime()).plusDays(25), 5, unPasajero, unaHabitacion); 
	/*N dias = 20:*/
	
	//Reservas que no son de unHotelero. Son reservas que sirven para control en los tests:
	private Reserva controlReservaAntigua = new Reserva(new DateTime(2014, 01, 01, 0, 0), new DateTime(2014, 01, 15, 0, 0), 5, unPasajero, habitacion2);
	private Reserva controlReservaActual = new Reserva(new DateTime(), (new DateTime()).plusDays(10), 5, unPasajero, habitacion2);
	private Reserva controlReservaFutura = new Reserva((new DateTime()).plusDays(5), (new DateTime()).plusDays(10), 5, unPasajero, habitacion2); 
	private Reserva controlReservaFuturaEnNDias = new Reserva((new DateTime()).plusDays(20), (new DateTime()).plusDays(25), 5, unPasajero, habitacion2);
	/*N dias = 20:*/
	

	
	/**@author abel*/
	protected void setUp() throws Exception {
		super.setUp();
		
		
		sistema.registrarReserva(reservaAntigua);
		sistema.registrarReserva(reservaActual);
		sistema.registrarReserva(reservaFutura);
		sistema.registrarReserva(reservaFuturaEnNDias);
		
		listaDeReservasDeUnHotelero.addAll(sistema.getReservas());//solamente reservas de unHotelero
		
		sistema.registrarReserva(controlReservaAntigua);
		sistema.registrarReserva(controlReservaActual);
		sistema.registrarReserva(controlReservaFutura);
		sistema.registrarReserva(controlReservaFuturaEnNDias);
	}

	
	/** Dado unHotelero y una listaDeReservas, que representa la lista de reservas del sistema, filtro las listaDeReservas 
	 * para obtener unicamente las reservas que le corresponden a unHotelero.
	 * @author abel*/
	public void test01_DadoUnHoteleroYUnaListaDeReservasObtengoTodasLasReservasDelHoteleroUnicamente() {
		Collection<Reserva> listaObtenida = unHotelero.getReservas(sistema);
		
		assertEquals(listaObtenida, listaDeReservasDeUnHotelero);
		assertEquals(listaObtenida.size(), 4);
	}
	
	
	/** Dado unHotelero y una listaDeReservas, que representa la lista de reservas del sistema, filtro la lista de reservas
	 * para obtener unicamente las reservas que le pertenecen al hotelero y cuyo periodo de valides se encuentra dentro de 
	 * la fecha actual.
	 * @author abel*/
	public void test02_DadoUnHoteleroYUnaListaDeReservasObtengoLasReservasActualesYQueSonDelHoteleroUnicamente() {
		Collection<Reserva> listaObtenida = unHotelero.reservasActuales(sistema.getReservasDelUsuario(unHotelero));
		// el metodo reservasActuales resiva ya las reservas del usuario 
		assertEquals(listaObtenida.size(), 1);
		assertTrue(listaObtenida.contains(reservaActual));
	}
	
	
	/** Dado unHotelero y una listaDeReservas, que representa la lista de reservas del sistema, filtro la lista de reservas
	 * para obtener unicamente las reservas que le pertenecen al hotelero y cuya fecha de ingreso es posterior a la fecha
	 * actual.
	 * @author abel*/
	public void test03_DadoUnHoteleroYUnaListaDeReservasObtengoTodasLasReservasFuturas() {
		Collection<Reserva> listaObtenida = unHotelero.reservasFuturas(sistema.getReservasDelUsuario(unHotelero));
		
		assertEquals(listaObtenida.size(), 2);
		assertTrue(listaObtenida.contains(reservaFutura));
		assertTrue(listaObtenida.contains(reservaFuturaEnNDias));
	}
	
	
	/** Dado unHotelero y una listaDeReservas, que representa la lista de reservas del sistema, filtro la lista de reservas
	 * para obtener unicamente las reservas que le pertenecen al hotelero y cuya fecha de ingreso es posterior a N dias.
	 * @author abel*/
	public void test04_DadoUnHoteleroYUnaListaDeReservasObtengoTodasLasReservasFuturasCuyaFechaDeIngresoEsEnNDias() {
		Integer n = 19/*dias*/;
		Collection<Reserva> listaObtenida = unHotelero.reservasAPartirDeNDias(n, sistema.getReservasDelUsuario(unHotelero));
		
		assertEquals(listaObtenida.size(), 1);
		assertTrue(listaObtenida.contains(reservaFuturaEnNDias));
	}
	
}
