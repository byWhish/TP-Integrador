package test;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import hotel.Habitacion;
import hotel.Hotel;
import junit.framework.TestCase;
import sistema.FiltroSimple;
import usuario.Hotelero;

public class FiltroTest extends TestCase{

	@Mock private Hotel myHotel;
	@Mock private Habitacion myHabitacion;
	FiltroSimple myFiltroHotel;
	FiltroSimple myFiltroHabitacion;
	String ciudad;
	
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		myFiltroHotel = new FiltroSimple();
		myFiltroHabitacion = new FiltroSimple();
		ciudad = "Roma";
	}
	
	public void testFiltro(){
		
		Mockito.when(myHotel.cumple( ciudad )).thenReturn( true );
		Mockito.when(myHabitacion.cumple( ciudad )).thenReturn( true );
		
		assertTrue( myFiltroHotel.cumpleConLaBusqueda( myHotel, ciudad ));
		assertTrue( myFiltroHabitacion.cumpleConLaBusqueda( myHabitacion, ciudad ));
	}
}
