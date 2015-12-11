package test;

import hotel.Servicio;
import junit.framework.TestCase;

/** Test- Servicio
 * @author abel*/
public class ServicioTest extends TestCase {

	private Servicio unServicio = new Servicio("Pileta", "Pileta climatizada", 50.0);
	
	protected void setUp() throws Exception {
		super.setUp();
	}

//Getters&Setters---------------------------------------------------------------
	/**@author abel*/
	public void testDadoUnServicioConsultoSuNombreYMeLoResponde() {
		assertEquals(unServicio.getNombre(), "Pileta");		
	}

	/**@author abel*/
	public void testDadoUnServicioConsutoSuBreveDescripcionYMeLoResponde() {
		assertEquals(unServicio.getBreveDescripcion(), "Pileta climatizada");
	}
	
	/**@author abel*/
	public void testDadoUnServicioConsultoSuPrecioYMeLoResponde() {
		assertEquals(unServicio.getPrecio(), 50.0);
	}
	
	/**@author abel*/
	public void testDadoUnServicioLeAsignoUnNuevoPrecio() {
		unServicio.setPrecioDelServicio(60.0);
		
		assertEquals(unServicio.getPrecio(), 60.0);
	}
//finGetters&Setters------------------------------------------------------------
	
}