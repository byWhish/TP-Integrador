package test;

import org.joda.time.DateTime;

import hotel.Habitacion;
import hotel.Hotel;
import hotel.PeriodoDeFechas;
import junit.framework.TestCase;
import sistema.Reserva;
import usuario.Pasajero;

public class ReservaTest extends TestCase {

	private Pasajero unPasajero = new Pasajero("pasajero@gmail.com", "4321");
	private Hotel unHotel = new Hotel("", "", "Quilmes", "", "", "", null, null, null);
	private Habitacion unaHabitacion = new Habitacion(50.0, unHotel, 5, "");
	
	private DateTime fechaInicio = new DateTime();
	private DateTime fechaFin = (new DateTime()).plusDays(5);
	
	private Reserva unaReserva = new Reserva(fechaInicio, fechaFin, 5, unPasajero, unaHabitacion);
	
	public void setUp() {
	}

//Getters&Setters---------------------------------------------------------------------------
	public void testDadoUnaReservaConsultoLaHabitacionReservadaYMeLaResponde() {
		assertEquals(unaReserva.getHabitacion(), unaHabitacion);
	}
	
	public void testDadoUnaReservaConsultoElPeriodoDeLaReservaYMeLoResponde() {
		PeriodoDeFechas periodoAObtener = new PeriodoDeFechas(fechaInicio, fechaFin);
		
		assertEquals(unaReserva.periodoDeLaReserva(), periodoAObtener);
	}
	
	public void testDadoUnaReservaConsultoSuFechaDeEntradaYMeLoResponde() {
		assertEquals(unaReserva.getFechaEntrada(), fechaInicio);
	}
	
	public void testDadoUnaReservaConsultoLaCantidadDePasajerosYMeLoResponde() {
		assertEquals(unaReserva.getCantidadPasajeros(), (Integer) 5);
	}
	
	public void testDadoUnaReservaConsultoSuUsuarioYMeLoResponde() {
		assertEquals(unaReserva.getUsuario(), unPasajero);
	}
	
	public void testDadoUnaReservaConsultoSuHotelYMeLoResponde() {
		assertEquals(unaReserva.getHotel(), unHotel);
	}
	
	public void testDadoUnaReservaConsultoLaCiudadDondeEstaUbicadoElHotelDeLaReservaYMeLoResponde() {
		assertEquals(unaReserva.getCiudad(), "Quilmes");
	}
//finGetters&Setters------------------------------------------------------------------------
}
