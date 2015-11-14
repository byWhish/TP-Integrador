package test;

import org.joda.time.DateTime;

import junit.framework.TestCase;
import sistema.Reserva;
import sistema.Sistema;
import usuario.Pasajero;
import usuario.Usuario;

public class TestSistema extends TestCase{
	
	Sistema miSistema;
	Usuario miUsuario;
	Reserva miReserva;
	
	public void setUp(){
		
		miSistema = new Sistema();
		miUsuario = new Pasajero( "tito@gmail.com","1234" );
		miReserva = new Reserva(new DateTime(2015,12,1,0,0), new DateTime(2015,12,1,0,0), 2, new Pasajero( "tito@gmail.com", "1234"));
		
		miSistema.agregarUsuario( miUsuario );
			
	}
	
	public void testLogIn(){
		//Aca testeo que me devuelva el usuario que se loguea
		assertEquals( miUsuario, miSistema.logIn( "tito@gmail.com","1234" ));
	}
	
	public void testFechaIngreso(){
		//con esto me aseguro que la fecha sea futura
		assert( miReserva.getFechaEntrada().isAfterNow());
	}
}
