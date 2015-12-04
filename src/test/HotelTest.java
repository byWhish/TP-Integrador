package test;

import java.util.ArrayList;

import hotel.Habitacion;
import hotel.Hotel;
import hotel.Servicio;
import junit.framework.TestCase;

public class HotelTest extends TestCase {

	private Hotel unHotel;
	private Habitacion unaHabitacion;
	private Servicio unServicio;
	
	protected void setUp() throws Exception {
		super.setUp();	
		unHotel = new Hotel(new ArrayList<Habitacion>() , new ArrayList<Servicio>(), "Quilmes");
	}

	
	/** Dados una Habitacion y un Hotel, agrego la habitacion al hotel y testeo si fue agregada.*/ 
	public void testDadoUnHotelYUnaHabitacionAgregoLaHabitacionAlHotel() {
		unaHabitacion = new Habitacion(250.0, unHotel, 2, "Dos camas de una plaza");
		unHotel.agregarHabitacion(unaHabitacion);
		
		assertTrue(unHotel.getHabitaciones().contains(unaHabitacion));
	}
	
	/** Dados un Hotel y un Servicio, agrego el servicio al hotel y testeo si fue agregado.*/
	public void testDadosUnHotelYUnServicioAgregoElServicioAlHotel() {
		unServicio = new Servicio("Pileta", "Pileta climatizada", 50.0);
		unHotel.agregarNuevoServicio(unServicio);
		
		assertTrue(unHotel.getServicios().contains(unServicio));
		
	}
}
