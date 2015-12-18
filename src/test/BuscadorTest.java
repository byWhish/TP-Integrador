package test;

import java.util.ArrayList;
import java.util.Collection;

import org.joda.time.DateTime;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import hotel.Habitacion;
import hotel.Hotel;
import junit.framework.TestCase;
import sistema.Buscador;
import sistema.Reserva;
import usuario.Usuario;

public class BuscadorTest extends TestCase{
	
	@Mock Usuario miUsuario;
	@Mock Habitacion miHabitacionReservada;
	@Mock Hotel miHotel;
	@Mock Hotel tuHotel;
	@Mock Hotel otroHotel;
	
	private Habitacion miHabitacion;
	private Habitacion otraHabitacion;
	private Buscador miBuscador;
	private Collection<Hotel> hoteles;
	private Collection<Reserva> reservas;
	private DateTime fechaEntrada;
	private DateTime fechaSalida;
	private Reserva miReserva;
	private Collection<Habitacion> habitaciones;
	private Collection<Habitacion> otrasHabitaciones;
	
	
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		
		fechaEntrada = new DateTime(2015,12,1,0,0);
		fechaSalida = new DateTime(2015,12,31,0,0);
		miReserva = new Reserva(new DateTime( 2016,01,01,0,0 ), new DateTime( 2016,01,02,0,0 ),2, miUsuario, miHabitacionReservada);
		reservas = new ArrayList<Reserva>();
		reservas.add(miReserva);
		hoteles = new ArrayList<Hotel>();
		miBuscador = new Buscador(fechaEntrada, fechaSalida, 2, "Quilmes", hoteles, reservas);
		miHabitacion = new Habitacion(/*$*/50.0, miHotel, 2/*personas*/, "2 camas de 1 plaza");
		habitaciones = new ArrayList<Habitacion>();
		habitaciones.add(miHabitacion);
		
		otraHabitacion = new Habitacion(/*$*/50.0, miHotel, 1/*personas*/, "2 camas de 1 plaza");
		otrasHabitaciones = new ArrayList<Habitacion>();
		otrasHabitaciones.add(otraHabitacion);
	}
	
	
	public void testConstructor(){
		assertEquals(2, miBuscador.getCantidadPasajeros());
		assertEquals(fechaEntrada, miBuscador.getFechaIngreso());
	}
	
	/** Dada una habitación que tiene un periodo de fechas no reservable, consulto si esta puede ser reservada durante un
	 * periodo de fechas dado. Como la habitación tiene fechas no reservables para las fechas dadas se responde false.
	 * @author abel*/
	public void testDadaUnaHabitacionConFechasCanceladasConsultoSiEsReservableParaUnPeriodoDeFechasYMeRespondeQueNo() {	
		//miHabitacion tiene el periodo 01/10/2015 al 10/10/2015 no reservable...
		miHabitacion.agregarNuevoPeriodoNoReservable(new DateTime(2015, 10, 01, 0, 0), new DateTime(2015, 10, 10, 0, 0));
		
		DateTime fechaInicio = new DateTime(2015, 10, 01, 0, 0);// 	01/10/2015
		DateTime fechaFin = new DateTime(2015, 10, 10, 0, 0);// 	10/10/2015
		assertFalse(miBuscador.habitacionEstaDisponibleParaReserva(miHabitacion, fechaInicio, fechaFin));
		
		fechaInicio = new DateTime(2015, 10, 02, 0, 0);// 	02/10/2015
		fechaFin = new DateTime(2015, 10, 9, 0, 0);// 		09/10/2015
		assertFalse(miBuscador.habitacionEstaDisponibleParaReserva(miHabitacion, fechaInicio, fechaFin));
		
		fechaInicio = new DateTime(2015, 9, 30, 0, 0);// 	30/09/2015
		fechaFin = new DateTime(2015, 10, 9, 0, 0);// 		09/10/2015
		assertFalse(miBuscador.habitacionEstaDisponibleParaReserva(miHabitacion, fechaInicio, fechaFin));
		
		fechaInicio = new DateTime(2015, 10, 02, 0, 0);// 	02/10/2015
		fechaFin = new DateTime(2015, 10, 11, 0, 0);// 		11/10/2015
		assertFalse(miBuscador.habitacionEstaDisponibleParaReserva(miHabitacion, fechaInicio, fechaFin));
	}
	
	/** Dada una habitación que tiene un periodo de fechas no reservable, consulto si esta puede ser reservada durante un
	 * periodo de fechas dado. Como la habitación tiene no fechas no reservables para las fechas dadas se responde true.
	 * @author abel*/
	public void testDadaUnaHabitacionConFechasCanceladasConsultoSiEsReservableParaUnPeriodoDeFechasYMeRespondeQueSi() {	
		//miHabitacion tiene el periodo 01/10/2015 al 10/10/2015 no reservable...
		miHabitacion.agregarNuevoPeriodoNoReservable(new DateTime(2015, 10, 01, 0, 0), new DateTime(2015, 10, 10, 0, 0));
		
		DateTime fechaInicio = new DateTime(2015, 9, 20, 0, 0);// 	20/09/2015
		DateTime fechaFin = new DateTime(2015, 9, 30, 0, 0);// 	30/09/2015
		assertTrue(miBuscador.habitacionEstaDisponibleParaReserva(miHabitacion, fechaInicio, fechaFin));
		
		fechaInicio = new DateTime(2015, 10, 11, 0, 0);// 	11/10/2015
		fechaFin = new DateTime(2015, 10, 20, 0, 0);// 		20/10/2015
		assertTrue(miBuscador.habitacionEstaDisponibleParaReserva(miHabitacion, fechaInicio, fechaFin));
	}

	public void testBusquedas(){
		Mockito.when(miHotel.getCiudad()).thenReturn("Quilmes");
		Mockito.when(miHotel.getHabitaciones()).thenReturn(habitaciones);
		
		assertTrue(miBuscador.hotelCumple(miHotel));
		
		Mockito.when(otroHotel.getCiudad()).thenReturn("Quilmes");
		Mockito.when(otroHotel.getHabitaciones()).thenReturn(otrasHabitaciones);
		
		assertFalse(miBuscador.hotelCumple(otroHotel));
		
		Mockito.when(tuHotel.getCiudad()).thenReturn("Quilmes");
		Mockito.when(tuHotel.getHabitaciones()).thenReturn(habitaciones);
		
		assertEquals( hoteles, miBuscador.buscarHoteles() );
		
		assertEquals(habitaciones, miBuscador.buscarHabitaciones(miHotel));
		
	}
	
}
