package test;

import org.joda.time.DateTime;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import filtro.FiltroComponente;
import filtro.FiltroCompuesto;
import filtro.FiltroSimpleCantidadPasajeros;
import filtro.FiltroSimpleCiudad;
import filtro.FiltroSimpleFechasFuturas;
import filtro.FiltroSimpleHotelero;
import filtro.FiltroSimplePasajero;
import hotel.Habitacion;
import hotel.Hotel;
import hotel.PeriodoDeFechas;
import junit.framework.TestCase;
import sistema.Reserva;
import usuario.Hotelero;
import usuario.Pasajero;

public class FiltroTest extends TestCase{

	@Mock private Hotel myHotel;
	@Mock private Habitacion myHabitacion;
	@Mock private Reserva myReserva;
	@Mock private Pasajero myPasajero;
	@Mock private Hotelero myHotelero;
	
	FiltroComponente myFiltroHotelCiudad;
	FiltroComponente myFiltroReservaPasajero;
	FiltroComponente myFiltroReservaHotelero;
	FiltroCompuesto myFiltroReservaCompuesto;
	FiltroComponente myFiltroFechas;
	FiltroComponente myFiltroCantidadPasajeros;
	PeriodoDeFechas myPeriodoDeFechas;
	String ciudad;
	
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		myPeriodoDeFechas = new PeriodoDeFechas(new DateTime(2015,12,1,0,0), new DateTime(2015,12,31,0,0));
		
		myFiltroHotelCiudad = new FiltroSimpleCiudad("Roma");
		myFiltroReservaPasajero = new FiltroSimplePasajero( myPasajero );
		myFiltroReservaHotelero = new FiltroSimpleHotelero( myHotelero );
		myFiltroFechas = new FiltroSimpleFechasFuturas( 2 );
		myFiltroCantidadPasajeros = new FiltroSimpleCantidadPasajeros(2);
		
		myFiltroReservaCompuesto = new FiltroCompuesto();
		myFiltroReservaCompuesto.componerFiltro( myFiltroHotelCiudad );
		myFiltroReservaCompuesto.componerFiltro( myFiltroReservaPasajero );
		
		ciudad = "Roma";
	}
	
	public void testFiltroSimple(){
		
	Mockito.when(myHabitacion.getCapacidadMaxima()).thenReturn(4);
	Mockito.when(myHotel.getCiudad()).thenReturn("Roma");
	Mockito.when(myReserva.getUsuario()).thenReturn(myPasajero);
	Mockito.when(myReserva.getHotel()).thenReturn(myHotel);
	Mockito.when(myReserva.getCiudad()).thenReturn("Roma");
	Mockito.when(myReserva.getFechaEntrada()).thenReturn( new DateTime( 2015,12,25,0,0 ) );
	
	Mockito.when(myHotelero.getHotel()).thenReturn(myHotel);
	
	assertTrue( myFiltroHotelCiudad.cumple(myHotel) );
	assertTrue( myFiltroReservaPasajero.cumple(myReserva) );
	assertTrue( myFiltroReservaHotelero.cumple(myReserva) );
	assertTrue( myFiltroFechas.cumple( myReserva ));
	assertTrue( myFiltroCantidadPasajeros.cumple(myHabitacion));
	
	assertTrue( myFiltroReservaCompuesto.cumple(myReserva) );
		
	}
}
