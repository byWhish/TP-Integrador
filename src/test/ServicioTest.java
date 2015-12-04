package test;

import hotel.Servicio;
import junit.framework.TestCase;

public class ServicioTest extends TestCase {

	private Servicio unServicio;
	
	protected void setUp() throws Exception {
		super.setUp();
		unServicio = new Servicio("Pileta", "Pileta climatizada", 50.0);
	}

	public void test() {
		
	}
}
