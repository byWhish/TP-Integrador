package test;

import org.joda.time.DateTime;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import hotel.Habitacion;
import hotel.Hotel;
import junit.framework.TestCase;

/** Test Habitacion.
 * @author abel*/
public class HabitacionTest extends TestCase {

	@Mock private Hotel unHotel;
	private Habitacion unaHabitacion;
	private DateTime fechaInicio;
	private DateTime fechaFin;
	
	/** @author abel*/
	protected void setUp() throws Exception {
		super.setUp();
		MockitoAnnotations.initMocks(this);

		fechaInicio = new DateTime(2015, 01, 15, 0, 0);
		fechaFin = new DateTime(2015, 01, 20, 0, 0);
		unaHabitacion = new Habitacion(/*$*/50.0, unHotel, 2/*personas*/, "2 camas de 1 plaza");
	}
	
//Getters&Setters----------------------------------------------------------------------------

	/** Dada unaHabitacion, consulto el precio base por noche y me lo responde.
	 * @author abel*/
	public void testDadoUnaHabitacionConsultoCualEsElPrecioBaseDeLaHabitacionYMeLoResponde() {
		assertEquals(unaHabitacion.getPrecioBasePorNoche() , (Double) 50.0);
	}
	
	/** Dada unaHabitacion, le asigno un precio base por noche.
	 * @author abel*/
	public void testDadoUnaHabitacionLeAsignoUnPrecioBasePorNoche() {
		unaHabitacion.setPrecioBasePorNoche(5.99);
		
		assertEquals(unaHabitacion.getPrecioBasePorNoche() , (Double) 5.99);
	}
	
	/** Dada unaHabitacion, consulto a qu� hotel pertenece y me lo responde.
	 * @author abel*/
	public void testDadaUnaHabitacionConsultoCualEsElHotelAlQuePerteneceYMeLoResponde() {
		assertEquals(unaHabitacion.getHotel(), unHotel);
	}
	
	/** Dada unaHabitacio, consulto cu�l es la capacidad m�xima de pasajeros que puede albergar
	 * y me lo responde.
	 * @author abel*/
	public void testDadaUnaHabitacionConsultoCualEsSuCapacidadMaximaYMeLoResponde() {
		assertEquals(unaHabitacion.getCapacidadMaxima(), 2);
	}
	
//finGetters&Setters-------------------------------------------------------------------------
	/** Dadas unaHabitacion y una fechaInicio y una fechaFin, se consulta si la habitacion es reservable entre esas fechas.
	 * Como para las fechas dadas la habitación no tiene fechas canceladas cargadas, se responde true.
	 * @author abel*/
	public void test1_DadosUnaHabitacionConFechasNoReservablesYDosFechasConsultoSiLaPrimeraEsReservableEntreLasFechasDadasYMeRespondeQueSi() {
		DateTime inicioPeriodo = new DateTime(2015, 01, 01, 0, 0);
		DateTime finPeriodo = new DateTime(2015, 01, 14, 0, 0);
		
		unaHabitacion.agregarNuevoPeriodoNoReservable(inicioPeriodo, finPeriodo);
		
		assertTrue(unaHabitacion.noEstaCanceladaParaLasFechas(fechaInicio, fechaFin));
	}

	
	/** Dadas unaHabitacion con fechas canceladas y una fechaInicio y una fechaFin, se consulta si la habitacion es reservable entre esas fechas.
	 * Como para las fechas dadas la habitación tiene un periodo de fechas canceladas cargadas, se responde false.
	 * @author abel*/
	public void test2_DadosUnaHabitacionConFechasNoReservablesYDosFechasConsultoSiLaPrimeraEsReservableEntreLasFechasDadasYMeRespondeQueNo() {
		DateTime inicioPeriodo = new DateTime(2015, 01, 01, 0, 0);
		DateTime finPeriodo = new DateTime(2015, 01, 15, 0, 0);
		
		unaHabitacion.agregarNuevoPeriodoNoReservable(inicioPeriodo, finPeriodo);
		
		assertFalse(unaHabitacion.noEstaCanceladaParaLasFechas(fechaInicio, fechaFin));
	}
	
	
	/** Dados unaHabitacion sin fechas promocionales y dos fechas, fechaInicio y fechaFin, consulto el precio de la estadía para esas
	 * fechas para la habitación.
	 * Como el precio base para unaHabitacion es de $50 y la cantidad de días entre las fechas es de 5 (15/01/2015 - 20/01/2015), se responde $250.
	 * @author abel*/
	public void test3_DadosUnaHabitacionSinFechasConPromocionesYDosFechasConsultoElPrecioTotalDeLaEstadiaEntreEsasDosFechasYMeLoResponde() {
		assertEquals(unaHabitacion.precioPorEstadia(fechaInicio, fechaFin), 250.0);
	}


	/** Dados unaHabitacion con fechas promocionales y dos fechas, fechaInicio y fechaFin, consulto el precio de la estadía para esas
	 * fechas para la habitación.
	 * Como el precio base para unaHabitacion es de 50 y la cantidad de días entre las fechas es de 15 (15/01/2015 - 30/01/2015) y entre
	 * esas fechas hay dos promociones:
	 * 
	 * promo1- 10/01/2015-16/01/2015 con precio por dia de 40;
	 * promo2- 20/01/2015-25/01/2015 con precio por dia de 70;
	 * 
	 * se responde (diasPromo1: 1) x $40 + (diasPromo2: 6) x $70 + (diasComunes 15 - 6) x $50 = $840.
	 * @author abel*/
	public void test4_DadosUnaHabitacionConFechasConPromocionesYDosFechasConsultoElPrecioTotalDeLaEstadiaEntreEsasDosFechasYMeLoResponde() {
		DateTime fechaFin2 = new DateTime(2015, 01, 30, 0, 0);// fechaInicio = 15/01/2015; fechaFin = 30/01/2015.
		
		unaHabitacion.agregarNuevoPeriodoDePromocion(new DateTime(2015, 01, 10, 0, 0), new DateTime(2015, 01, 16, 0, 0), (double) 40);
		unaHabitacion.agregarNuevoPeriodoDePromocion(new DateTime(2015, 01, 20, 0, 0), new DateTime(2015, 01, 25, 0, 0), (double) 70);
		
		assertEquals(unaHabitacion.precioPorEstadia(fechaInicio, fechaFin2), 840.0);
	}
	
	//aserto la ciudad para usarla en el patron composite
	public void testCumple(){
		Mockito.when(unHotel.getCiudad()).thenReturn("Roma");
		assertTrue(unaHabitacion.cumple("Roma"));
	}

}