package test;

import java.util.ArrayList;
import java.util.Collection;

import org.joda.time.DateTime;
import org.mockito.Mock;
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
	private Buscador miBuscador;
	private Collection<Hotel> hoteles;
	private Collection<Reserva> reservas;
	private DateTime fechaEntrada;
	private DateTime fechaSalida;
	private Reserva miReserva;
	
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		
		fechaEntrada = new DateTime(2015,12,1,0,0);
		fechaSalida = new DateTime(2015,12,31,0,0);
		miReserva = new Reserva(new DateTime( 2016,01,01,0,0 ), new DateTime( 2016,01,02,0,0 ),2, miUsuario, miHabitacionReservada);
		reservas = new ArrayList<Reserva>();
		reservas.add(miReserva);
		hoteles = new ArrayList<Hotel>();
		miBuscador = new Buscador(fechaEntrada, fechaSalida, 2, "Quilmes", hoteles, reservas);
	}
	
	
	public void testConstructor(){
		assertEquals(2, miBuscador.getCantidadPasajeros());
		assertEquals(fechaEntrada, miBuscador.getFechaIngreso());
	}

}
