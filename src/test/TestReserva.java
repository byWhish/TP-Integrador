package test;

import org.joda.time.DateTime;

import junit.framework.TestCase;
import sistema.Reserva;
import usuario.Pasajero;

public class TestReserva extends TestCase{
	
	private Reserva miReserva;
	
	public void setUp(){
	
		miReserva = new Reserva(new DateTime(2015,12,1,0,0), new DateTime(2015,12,1,0,0), 2, new Pasajero( "tito@gmail.com", "1234"));
	}

	public void testFechaIngreso(){
		//con esto me aseguro que la fecha sea futura
		assert( miReserva.getFechaEntrada().isAfterNow());
	}
}
