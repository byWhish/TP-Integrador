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
	@Mock private Buscador myBuscador;
	
	private Sistema miSistema = new Sistema();
	
	private Usuario miPasajero = new Pasajero( "tito@gmail.com","1234" );
	//usuarios de control para testeo:
	private Usuario controlPasajero1 = new Pasajero( "tito@gmail.com","4321" );
	private Usuario controlPasajero2 = new Pasajero( "control@gmail.com","1234" );
	
	private Hotel miHotel = new Hotel("Mi Hotel", "Argentina", "CABA", "calle falsa 123", "555-5555", "mihotel@hotel.com", 3/*categoria*/, 3, 3);
	private Habitacion miHabitacion = new Habitacion(10.0, miHotel, 2, "1 cama");	
	
	public void setUp(){
		
		MockitoAnnotations.initMocks(this);
		
		miSistema.registrarUsuario( miPasajero );	
		miSistema.registrarUsuario(controlPasajero1);
		miSistema.registrarUsuario(controlPasajero2);
		
		miSistema.registrarHotel(miHotel);
	}
	
	public void testLogIn(){
		//Aca testeo que me devuelva el usuario que se loguea
		assertEquals( miPasajero, miSistema.logIn( "tito@gmail.com","1234" ));
	}


}
