package test;

import org.joda.time.DateTime;

import hotel.Habitacion;
import hotel.Hotel;
import junit.framework.TestCase;
import sistema.Buscador;
import sistema.Reserva;
import sistema.Sistema;
import usuario.Pasajero;
import usuario.Usuario;

public class SistemaTest extends TestCase{
	
	private Sistema miSistema = new Sistema();
	
	private Usuario miPasajero = new Pasajero( "tito@gmail.com","1234" );
	//usuarios de control para testeo:
	private Usuario controlPasajero1 = new Pasajero( "tito@gmail.com","4321" );
	private Usuario controlPasajero2 = new Pasajero( "control@gmail.com","1234" );
	
	private Hotel miHotel = new Hotel("Mi Hotel", "Argentina", "CABA", "calle falsa 123", "555-5555", "mihotel@hotel.com", 3/*categoria*/, 3, 3);
	private Habitacion miHabitacion = new Habitacion(10.0, miHotel, 2, "1 cama");
	
	private Reserva miReserva = new Reserva(new DateTime(2015,12,1,0,0), new DateTime(2015,12,10,0,0), 2, miPasajero, miHabitacion);
	
	
	public void setUp(){
		miSistema.registrarUsuario( miPasajero );	
		miSistema.registrarUsuario(controlPasajero1);
		miSistema.registrarUsuario(controlPasajero2);
		
		miSistema.registrarHotel(miHotel);
	}
	
	public void testLogIn(){
		//Aca testeo que me devuelva el usuario que se loguea
		assertEquals( miPasajero, miSistema.logIn( "tito@gmail.com","1234" ));
	}
	
	/*	ESTE TEST ES DE DateTime, NO ES TEST DE SISTEMA 
	public void testFechaIngreso(){
		//con esto me aseguro que la fecha sea futura
		assert( miReserva.getFechaEntrada().isAfterNow());
	}*/
	
	
	/** Dada una habitación que tiene un periodo de fechas no reservable, consulto si esta puede ser reservada durante un
	 * periodo de fechas dado. Como la habitación tiene fechas no reservables para las fechas dadas se responde false.
	 * @author abel*/
	public void testDadaUnaHabitacionConFechasCanceladasConsultoSiEsReservableParaUnPeriodoDeFechasYMeRespondeQueNo() {	
		//miHabitacion tiene el periodo 01/10/2015 al 10/10/2015 no reservable...
		miHabitacion.agregarNuevoPeriodoNoReservable(new DateTime(2015, 10, 01, 0, 0), new DateTime(2015, 10, 10, 0, 0));
		
		DateTime fechaInicio = new DateTime(2015, 10, 01, 0, 0);// 	01/10/2015
		DateTime fechaFin = new DateTime(2015, 10, 10, 0, 0);// 	10/10/2015
		assertFalse(miSistema.habitacionEstaDisponibleParaReserva(miHabitacion, fechaInicio, fechaFin));
		
		fechaInicio = new DateTime(2015, 10, 02, 0, 0);// 	02/10/2015
		fechaFin = new DateTime(2015, 10, 9, 0, 0);// 		09/10/2015
		assertFalse(miSistema.habitacionEstaDisponibleParaReserva(miHabitacion, fechaInicio, fechaFin));
		
		fechaInicio = new DateTime(2015, 9, 30, 0, 0);// 	30/09/2015
		fechaFin = new DateTime(2015, 10, 9, 0, 0);// 		09/10/2015
		assertFalse(miSistema.habitacionEstaDisponibleParaReserva(miHabitacion, fechaInicio, fechaFin));
		
		fechaInicio = new DateTime(2015, 10, 02, 0, 0);// 	02/10/2015
		fechaFin = new DateTime(2015, 10, 11, 0, 0);// 		11/10/2015
		assertFalse(miSistema.habitacionEstaDisponibleParaReserva(miHabitacion, fechaInicio, fechaFin));
	}

	/** Dada una habitación que tiene un periodo de fechas no reservable, consulto si esta puede ser reservada durante un
	 * periodo de fechas dado. Como la habitación tiene no fechas no reservables para las fechas dadas se responde true.
	 * @author abel*/
	public void testDadaUnaHabitacionConFechasCanceladasConsultoSiEsReservableParaUnPeriodoDeFechasYMeRespondeQueSi() {	
		//miHabitacion tiene el periodo 01/10/2015 al 10/10/2015 no reservable...
		miHabitacion.agregarNuevoPeriodoNoReservable(new DateTime(2015, 10, 01, 0, 0), new DateTime(2015, 10, 10, 0, 0));
		
		DateTime fechaInicio = new DateTime(2015, 9, 20, 0, 0);// 	20/09/2015
		DateTime fechaFin = new DateTime(2015, 9, 30, 0, 0);// 	30/09/2015
		assertTrue(miSistema.habitacionEstaDisponibleParaReserva(miHabitacion, fechaInicio, fechaFin));
		
		fechaInicio = new DateTime(2015, 10, 11, 0, 0);// 	11/10/2015
		fechaFin = new DateTime(2015, 10, 20, 0, 0);// 		20/10/2015
		assertTrue(miSistema.habitacionEstaDisponibleParaReserva(miHabitacion, fechaInicio, fechaFin));
	}

	/** Dada una habitación, consulto si esta puede ser reservada durante un periodo de fechas dado.
	 * Como la habitación fue reservada en el sistema para el periodo de fechas dado se responde false.
	 * @author abel*/
	public void testDadaUnaHabitacionConFechasReservadasEnElSistemaConsultoSiEsReservableParaUnPeriodoDeFechasYMeRespondeQueNo() {		
		Buscador unaBusqueda = new Buscador(new DateTime(2015, 10, 01,0, 0), new DateTime(2015, 10, 10,0, 0), 3, "CABA", miSistema);
		
		//Se reserva una habitación para un periodo de fechas que tiene fechas disponible y no fue reservada anteriormente...
		miSistema.reservarHabitacion(miHabitacion, controlPasajero1, unaBusqueda);
		
		DateTime fechaInicio = new DateTime(2015, 10, 01, 0, 0);// 	01/10/2015
		DateTime fechaFin = new DateTime(2015, 10, 10, 0, 0);// 	10/10/2015
		assertFalse(miSistema.habitacionEstaDisponibleParaReserva(miHabitacion, fechaInicio, fechaFin));
		
		fechaInicio = new DateTime(2015, 10, 02, 0, 0);// 	02/10/2015
		fechaFin = new DateTime(2015, 10, 9, 0, 0);// 		09/10/2015
		assertFalse(miSistema.habitacionEstaDisponibleParaReserva(miHabitacion, fechaInicio, fechaFin));
		
		fechaInicio = new DateTime(2015, 9, 30, 0, 0);// 	30/09/2015
		fechaFin = new DateTime(2015, 10, 9, 0, 0);// 		09/10/2015
		assertFalse(miSistema.habitacionEstaDisponibleParaReserva(miHabitacion, fechaInicio, fechaFin));
		
		fechaInicio = new DateTime(2015, 10, 02, 0, 0);// 	02/10/2015
		fechaFin = new DateTime(2015, 10, 11, 0, 0);// 		11/10/2015
		assertFalse(miSistema.habitacionEstaDisponibleParaReserva(miHabitacion, fechaInicio, fechaFin));
	}
}
