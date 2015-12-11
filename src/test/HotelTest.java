package test;

import hotel.Habitacion;
import hotel.Hotel;
import hotel.Servicio;
import junit.framework.TestCase;

/** Testeo de la clase Hotel
 * @author abel*/
public class HotelTest extends TestCase {

	private Hotel unHotel;
	private Habitacion unaHabitacion;
	private Servicio unServicio;
	
	protected void setUp() throws Exception {
		super.setUp();
		unHotel = new Hotel("hotel1", "Argentina", "Quilmes", "123 nº 456", "4200-0000",
							"hotel1@hotel.com", 3 ,3, 4);
	}

//GettersYSetters--------------------------------------------------------	
	/** Dado un unHotel, consulto su nombre y me lo responde.
	 * @author abel*/
	public void testDadoUnHotelConsultoSuNombreYMeLoResponde() {
		assertEquals(unHotel.getNombre(), "hotel1");
	}
	
	/** Dado un unHotel, consulto el país donde está ubicado y me lo responde.
	 * @author abel*/
	public void testDadoUnHotelConsultoSuPaisYMeLoResponde() {
		assertEquals(unHotel.getPais(), "Argentina");
	}
	
	/** Dado un unHotel, consulto la ciudad donde está ubicado y me lo responde.
	 * @author abel*/
	public void testDadoUnHotelConsultoSuCiudadYMeLoResponde() {
		assertEquals(unHotel.getCiudad(), "Quilmes");
	}
	
	/** Dado un unHotel, consulto su dirección y me lo responde.
	 * @author abel*/
	public void testDadoUnHotelConsultoSuDireccionYMeLoResponde() {
		assertEquals(unHotel.getDireccion(), "123 nº 456");
	}
	
	/** Dado un unHotel, consulto su E-Mail y me lo responde.
	 * @author abel*/
	public void testDadoUnHotelConsultoSuEMailYMeLoResponde() {
		assertEquals(unHotel.getEmail(), "hotel1@hotel.com");
	}
	
	/** Dado un unHotel, consulto su categoría y me lo responde.
	 * @author abel*/
	public void testDadoUnHotelConsultoSuCategoriaYMeLoResponde() {
		assertEquals(unHotel.getCategoria(), "3 estrellas.");
	}
	
	/** Dado un unHotel, consulto su horario de check-in y me lo responde.
	 * @author abel*/
	public void testDadoUnHotelConsultoSuHorarioDeCheckInYMeLoResponde() {
		assertEquals(unHotel.getHorarioDeCheckIn(), (Integer) 3);
	}
	
	/** Dado un unHotel, consulto su horario de check-out y me lo responde.
	 * @author abel*/
	public void testDadoUnHotelConsultoSuHorarioDeCheckOutYMeLoResponde() {
		assertEquals(unHotel.getHorarioDeCheckOut(), (Integer) 4);
	}
	
//finGettersYSetters-----------------------------------------------------
	
	/** Dados una Habitacion y un Hotel, agrego la habitacion al hotel y testeo si fue agregada.
	 * @author abel*/ 
	public void testDadoUnHotelYUnaHabitacionAgregoLaHabitacionAlHotel() {
		unaHabitacion = new Habitacion(250.0, unHotel, 2, "Dos camas de una plaza");
		unHotel.agregarHabitacion(unaHabitacion);
		
		assertTrue(unHotel.getHabitaciones().contains(unaHabitacion));
	}
	
	/** Dados un Hotel y un Servicio, agrego el servicio al hotel y testeo si fue agregado.
	 * @athor abel*/
	public void testDadosUnHotelYUnServicioAgregoElServicioAlHotel() {
		unServicio = new Servicio("Pileta", "Pileta climatizada", 50.0);
		unHotel.agregarNuevoServicio(unServicio);
		
		assertTrue(unHotel.getServicios().contains(unServicio));		
	}
	
	/** Dados un Hotel y una forma de pago aceptada por el hotel, agrego la forma de pago al hotel y testeo si fue agregado.
	 * @athor abel*/
	public void testDadosUnHotelYUnaFormaDePagoAceptadaAgregoLaFormaDePagoAlHotel() {
		unHotel.agregarUnaNuevaFormaDePagoAceptada("VISA");
		
		assertTrue(unHotel.getFormasDePagoAceptadas().contains("VISA"));		
	}
}
