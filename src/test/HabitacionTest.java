package test;

import org.joda.time.DateTime;

import hotel.Habitacion;
import hotel.Hotel;
import junit.framework.TestCase;

public class HabitacionTest extends TestCase {

	private Habitacion unaHabitacion;
	private DateTime fechaInicio;
	private DateTime fechaFin;
	
	protected void setUp() throws Exception {
		super.setUp();
		fechaInicio = new DateTime(2015, 01, 15, 0, 0);
		fechaFin = new DateTime(2015, 01, 20, 0, 0);
		unaHabitacion = new Habitacion(/*$*/50.0,new Hotel(), 2/*personas*/, "2 camas de 1 plaza");
	}
	
	
	/** Dadas unaHabitacion y una fechaInicio y una fechaFin, se consulta si la habitacion es reservable entre esas fechas.
	 * Como para las fechas dadas la habitación no tiene fechas no-reservables cargadas, se responde true.
	 * */
	public void test1_DadosUnaHabitacionConFechasNoReservablesYDosFechasConsultoSiLaPrimeraEsReservableEntreLasFechasDadasYMeRespondeQueSi() {
		DateTime inicioPeriodo = new DateTime(2015, 01, 01, 0, 0);
		DateTime finPeriodo = new DateTime(2015, 01, 14, 0, 0);
		
		unaHabitacion.agregarNuevoPeriodoNoReservable(inicioPeriodo, finPeriodo);
		
		assertTrue(unaHabitacion.reservableParaLasFechas(fechaInicio, fechaFin));
	}

	
	/** Dadas unaHabitacion con fechas no-reservables y una fechaInicio y una fechaFin, se consulta si la habitacion es reservable entre esas fechas.
	 * Como para las fechas dadas la habitación tiene un periodo de fechas no-reservables cargadas, se responde false.
	 * */
	public void test2_DadosUnaHabitacionConFechasNoReservablesYDosFechasConsultoSiLaPrimeraEsReservableEntreLasFechasDadasYMeRespondeQueNo() {
		DateTime inicioPeriodo = new DateTime(2015, 01, 01, 0, 0);
		DateTime finPeriodo = new DateTime(2015, 01, 15, 0, 0);
		
		unaHabitacion.agregarNuevoPeriodoNoReservable(inicioPeriodo, finPeriodo);
		
		assertFalse(unaHabitacion.reservableParaLasFechas(fechaInicio, fechaFin));
	}
	
	
	/** Dados unaHabitacion sin fechas promocionales y dos fechas, fechaInicio y fechaFin, consulto el precio de la estadía para esas
	 * fechas para la habitación.
	 * Como el precio base para unaHabitacion es de $50 y la cantidad de días entre las fechas es de 5 (15/01/2015 - 20/01/2015), se responde $250.
	 * */
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
	 * */
	public void test4_DadosUnaHabitacionConFechasConPromocionesYDosFechasConsultoElPrecioTotalDeLaEstadiaEntreEsasDosFechasYMeLoResponde() {
		DateTime fechaFin2 = new DateTime(2015, 01, 30, 0, 0);// fechaInicio = 15/01/2015; fechaFin = 30/01/2015.
		
		unaHabitacion.agregarNuevoPeriodoDePromocion(new DateTime(2015, 01, 10, 0, 0), new DateTime(2015, 01, 16, 0, 0), (double) 40);
		unaHabitacion.agregarNuevoPeriodoDePromocion(new DateTime(2015, 01, 20, 0, 0), new DateTime(2015, 01, 25, 0, 0), (double) 70);
		
		assertEquals(unaHabitacion.precioPorEstadia(fechaInicio, fechaFin2), 840.0);
	}

}