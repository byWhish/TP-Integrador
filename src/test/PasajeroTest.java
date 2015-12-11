package test;

import java.util.ArrayList;
import java.util.Collection;

import org.joda.time.DateTime;

import hotel.Habitacion;
import hotel.Hotel;
import junit.framework.TestCase;
import sistema.Reserva;
import usuario.Pasajero;

/** Testeo de la clase Pasajero
 * @author abel*/
public class PasajeroTest extends TestCase {

	private Hotel hotel1 = new Hotel("hotel1", "Argentina", "CABA", "calleFalsa123", "555-5555", "hotel1@hotel.com", 3, null, null);
	private Hotel hotel2 = new Hotel("hotel2", "Perú", "Quito", "calleFalsa123", "123-456", "hotel2@hotel.com", 2, null, null);
	private Hotel hotel3 = new Hotel("hotel3", "Brasil", "Rio", "calleFalsa123", "678-456", "hotel3@hotel.com", 2, null, null);
	private Hotel controlHotel = new Hotel("hotel4", "Paraguay", "Asunción", "calleFalsa123", "666-6666", "hotel4@hotel.com", 5, null, null);

	
	private Habitacion habitacion1 = new Habitacion(50.0, hotel1, 4, "");
	private Habitacion habitacion2 = new Habitacion(60.0, hotel2, 5, "");
	private Habitacion habitacion3 = new Habitacion(70.0, hotel3, 6, "");
	private Habitacion controlHabitacion = new Habitacion(70.0, controlHotel, 6, "");
	
	private Pasajero unPasajero = new Pasajero("1234", "pasajero@hotel.com");
	private Pasajero controlPasajero = new Pasajero("4567", "pasajero@hotel.com");//pasajero de control creado para testeo
	
	private ArrayList<Reserva> listaDeReservas = new ArrayList<Reserva>();//Todas las reservas del sistema
	private ArrayList<Reserva> listaDeReservasDeUnPasajero = new ArrayList<Reserva>(); //Solo las reservas de unPasajero
	private ArrayList<String> listaDeCiudadesDondeHayReservas = new ArrayList<String>();
	
	//Reservas de a unPasajero:
	private Reserva reservaAntigua = new Reserva(new DateTime(2014, 01, 01, 0, 0), new DateTime(2014, 01, 15, 0, 0), 5, unPasajero, habitacion1);
	private Reserva reservaActual = new Reserva(new DateTime(), (new DateTime()).plusDays(10), 5, unPasajero, habitacion2);
	private Reserva reservaFutura = new Reserva((new DateTime()).plusDays(5), (new DateTime()).plusDays(10), 5, unPasajero, habitacion3); 
	private Reserva reservaFuturaEnNDias = new Reserva((new DateTime()).plusDays(20), (new DateTime()).plusDays(25), 5, unPasajero, habitacion1); 
	/*N dias = 20:*/
	
	//Reservas que no son de unPasajero. Son reservas que sirven para control en los tests:
	private Reserva controlReservaAntigua = new Reserva(new DateTime(2014, 01, 01, 0, 0), new DateTime(2014, 01, 15, 0, 0), 5, controlPasajero, habitacion1);
	private Reserva controlReservaActual = new Reserva(new DateTime(), (new DateTime()).plusDays(10), 5, controlPasajero, habitacion2);
	private Reserva controlReservaFutura = new Reserva((new DateTime()).plusDays(5), (new DateTime()).plusDays(10), 5, controlPasajero, controlHabitacion); 
	private Reserva controlReservaFuturaEnNDias = new Reserva((new DateTime()).plusDays(20), (new DateTime()).plusDays(25), 5, controlPasajero, habitacion3);
	/*N dias = 20:*/
	

	
	/**@author abel*/
	protected void setUp() throws Exception {
		super.setUp();
		
		listaDeCiudadesDondeHayReservas.add("CABA");
		listaDeCiudadesDondeHayReservas.add("Rio");
		listaDeCiudadesDondeHayReservas.add("Quito");
		
		hotel1.agregarHabitacion(habitacion1);
		hotel2.agregarHabitacion(habitacion2);
		hotel3.agregarHabitacion(habitacion3);
		
		listaDeReservas.add(reservaAntigua);
		listaDeReservas.add(reservaActual);
		listaDeReservas.add(reservaFutura);
		listaDeReservas.add(reservaFuturaEnNDias);
		
		listaDeReservasDeUnPasajero.addAll(listaDeReservas);//solamente reservas de unPasajero
		
		listaDeReservas.add(controlReservaAntigua);
		listaDeReservas.add(controlReservaActual);
		listaDeReservas.add(controlReservaFutura);
		listaDeReservas.add(controlReservaFuturaEnNDias);
	}

	
	/** Dado unPasajero y una listaDeReservas, que representa la lista de reservas del sistema, filtro las listaDeReservas 
	 * para obtener unicamente las reservas que le corresponden a unPasajero.
	 * @author abel*/
	public void test01_DadoUnPasajeroYUnaListaDeReservasObtengoTodasLasReservasUnicamenteDelPasajero() {
		Collection<Reserva> listaObtenida = unPasajero.todasLasReservas(listaDeReservas);
		
		assertTrue(listaObtenida.containsAll(listaDeReservasDeUnPasajero));
		assertEquals(listaObtenida.size(), 4);
	}
	
	
	/** Dado unPasajero y una listaDeReservas, que representa la lista de reservas del sistema, filtro la lista de reservas
	 * para obtener unicamente las reservas que le pertenecen al pasajero y cuya fecha de ingreso es posterior a la fecha
	 * actual.
	 * @author abel*/
	public void test02_DadoUnPasajeroYUnaListaDeReservasObtengoTodasLasReservasFuturas() {
		Collection<Reserva> listaObtenida = unPasajero.reservasFuturas(listaDeReservas);
		
		assertEquals(listaObtenida.size(), 2);
		assertTrue(listaObtenida.contains(reservaFutura));
		assertTrue(listaObtenida.contains(reservaFuturaEnNDias));
	}
	
	
	/** Dado unPasajero y una listaDeReservas, que representa la lista de reservas del sistema, filtro la lista de reservas
	 * para obtener unicamente las reservas que le pertenecen al pasajero y cuya ciudad donde se encuentra el hotel sea 
	 * CABA.
	 * @author abel*/
	public void test03_DadoUnPasajeroYUnaListaDeReservasObtengoTodasLasReservasQueFueronRealizadasParaUnaCiudadParticular() {
		Collection<Reserva> listaObtenida = unPasajero.reservasEnUnaCiudadParticular(listaDeReservas, "CABA");
		
		/*Solo las reservas reservaAntigua y reservaFuturaEnNDias son reservas realizadas en la ciudad de CABA.*/
		
		assertEquals(listaObtenida.size(), 2);
		assertTrue(listaObtenida.contains(reservaAntigua));
		assertTrue(listaObtenida.contains(reservaFuturaEnNDias));
	}
	
	
	/** Dado unPasajero y una listaDeReservas, que representa la lista de reservas del sistema, consulto cuáles son las 
	 * ciudades en donde unPasajero realizó reservas.
	 * Las ciudades donde unPasajero realizó reservas son los mismas de los hoteles que poseen habitaciones donde se realizaron
	 * reservas.
	 * @author abel*/
	public void test04_DadoUnPasajeroYUnaListaDeReservasObtengoTodasCiudadesDondeSeRealizarionReservas() {
		Collection<String> listaObtenida = unPasajero.ciudadesDondeSeRealizaronReservas(listaDeReservas);
		
		/*Las ciudades donde se realizaron reservas son: CABA, Quito y Rio.*/
		assertEquals(listaObtenida.size(), 3);
		assertTrue(listaObtenida.containsAll(listaDeCiudadesDondeHayReservas));
	}
}
