package test;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import filtro.FiltroComponente;
import filtro.FiltroCompuesto;
import filtro.FiltroSimpleCiudad;
import filtro.FiltroSimpleHotelero;
import filtro.FiltroSimplePasajero;
import hotel.Habitacion;
import hotel.Hotel;
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
	String ciudad;
	
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		myFiltroHotelCiudad = new FiltroSimpleCiudad("Roma");
		myFiltroReservaPasajero = new FiltroSimplePasajero( myPasajero );
		myFiltroReservaHotelero = new FiltroSimpleHotelero( myHotelero );
		
		myFiltroReservaCompuesto = new FiltroCompuesto();
		myFiltroReservaCompuesto.componerFiltro( myFiltroHotelCiudad );
		myFiltroReservaCompuesto.componerFiltro( myFiltroReservaPasajero );
		
		ciudad = "Roma";
	}
	
	public void testFiltroSimple(){
		
	Mockito.when(myHotel.getCiudad()).thenReturn("Roma");
	Mockito.when(myReserva.getUsuario()).thenReturn(myPasajero);
	Mockito.when(myReserva.getHotel()).thenReturn(myHotel);
	Mockito.when(myReserva.getCiudad()).thenReturn("Roma");
	Mockito.when(myHotelero.getHotel()).thenReturn(myHotel);
	
	assertTrue( myFiltroHotelCiudad.cumple(myHotel) );
	assertTrue( myFiltroReservaPasajero.cumple(myReserva) );
	assertTrue( myFiltroReservaHotelero.cumple(myReserva) );
	
	assertTrue( myFiltroReservaCompuesto.cumple(myReserva) );
		
	}
}
