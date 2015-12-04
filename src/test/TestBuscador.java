package test;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import hotel.Habitacion;
import hotel.Hotel;
import junit.framework.TestCase;
import sistema.Buscador;
import usuario.Hotelero;


public class TestBuscador extends TestCase {
	Buscador miBuscador;
	Hotel hotelZ; Hotel hotelF; 
	Hotelero luis ;
	Hotelero gianLuigi; 
	Habitacion hab1;
	Habitacion hab2; 
	List<Habitacion> habitaciones; List<Hotel> hoteles; 
	
	public void setUp(){
		miBuscador = new Buscador(new DateTime(2015,11,5,0,0),new DateTime(2015,11,10,0,0),2,"Cancun"); 
		hab1 = new Habitacion(2); 
		hab2 = new Habitacion(3); 
		habitaciones = new ArrayList<Habitacion>(); 
		luis = new Hotelero ("luis_14", "1234");  
		gianLuigi = new Hotelero("gian24", "124"); 
		hotelZ = new Hotel("Cancun", luis, habitaciones); 
		hotelF = new Hotel ("Florencia",gianLuigi,  habitaciones);
		hoteles = new ArrayList<Hotel>();
		habitaciones.add(hab1); habitaciones.add(hab2);
		hoteles.add(hotelZ); //hoteles.add(hotelF); 
	}
	
	public void testBuscarHoteles(){
		assertEquals(hoteles,miBuscador.buscarHoteles(hoteles));
	}
	
	public void testBuscarHabitacion(){
		assertEquals(habitaciones,miBuscador.buscarHabitaciones(hotelZ));
	}
}
