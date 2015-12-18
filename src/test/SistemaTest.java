package test;

import org.joda.time.DateTime;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import hotel.Habitacion;
import hotel.Hotel;
import junit.framework.TestCase;
import sistema.Buscador;
import sistema.Reserva;
import sistema.Sistema;
import usuario.Pasajero;
import usuario.Usuario;

public class SistemaTest extends TestCase{
	
	private Reserva miReserva;
	
	private Buscador miBusqueda;
	private DateTime fechaInicio;
	private DateTime fechaFin;
	
	private Sistema miSistema = new Sistema();
	
	private Usuario miPasajero = new Pasajero( "tito@gmail.com","1234" );
	
	private Hotel miHotel = new Hotel("Mi Hotel", "Argentina", "CABA", "calle falsa 123", "555-5555", "mihotel@hotel.com", 3/*categoria*/, 3, 3);
	private Habitacion miHabitacion = new Habitacion(10.0, miHotel, 2, "1 cama");	
	
	public void setUp(){
		
		MockitoAnnotations.initMocks(this);
		
		miSistema.registrarUsuario( miPasajero );	
		miSistema.registrarHotel(miHotel);
		
		miReserva = new Reserva(fechaInicio, fechaFin, 5, miPasajero, miHabitacion);
	}
	
	public void testLogIn(){
		//Aca testeo que me devuelva el usuario que se loguea
		assertEquals( miPasajero, miSistema.logIn( "tito@gmail.com","1234" ));
	}

	public void testNuevaBusqueda(){
		
		miBusqueda = miSistema.nuevaBusqueda(fechaInicio, fechaFin, 2, "Hudson");
		
		assertEquals( miBusqueda.getHoteles(), miSistema.getHoteles() );
		assertEquals( miBusqueda.getReservas(), miSistema.getReservas() );
	}
	
	public void testCancelarReserva(){
		miSistema.registrarReserva(miReserva);
		assertEquals(1, miSistema.getReservas().size());
		
		miSistema.cancelarReserva(miReserva);
		assertEquals(0, miSistema.getReservas().size());
	}
	
	public void testReservarHabitacion(){
		
		assertEquals(0, miSistema.getReservas().size());
		
		miBusqueda = miSistema.nuevaBusqueda(fechaInicio, fechaFin, 2, "Hudson");
		miSistema.reservarHabitacion(miHabitacion, miPasajero, miBusqueda);
		assertEquals(1, miSistema.getReservas().size());
	}

}
