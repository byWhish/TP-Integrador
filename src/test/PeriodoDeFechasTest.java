package test;

import org.joda.time.DateTime;

import hotel.PeriodoDeFechas;
import junit.framework.TestCase;

public class PeriodoDeFechasTest extends TestCase {

	private DateTime fechaInicio;
	private DateTime fechaFin;
	private PeriodoDeFechas unPeriodo;
	
	protected void setUp() throws Exception {
		super.setUp();
		unPeriodo = new PeriodoDeFechas(new DateTime(2015, 01, 05, 0, 0), new DateTime(2015, 02, 15, 0, 0));
		// unPeriodo va del 05/01/2015 al 15/02/2015
	}

	/** Test- Dados unPeriodo de fechas, unafechaInicio y unafechaFin, se consulta si el periodo dado por las fechas incluye en algún punto 
	 * con unPeriodo.
	 * Como ambas, fechaInicio y fechaFin, se incluyen dentro de unPeriodo, se responde true. 
	 * @author abel*/
	public void test1_DadosUnPeriodoUnaFechaDeInicioYUnaFechaDeFinSeConsultaSiElPeriodoRepresentadoPorLasFechasDadasSeIncluyeDentroDelPeriodoYMeRespondeQueSi() {
		// unPeriodo va del 05/01/2015 al 15/02/2015
		this.fechaInicio = new DateTime(2015, 01, 06, 0, 0 );//se incluye
		this.fechaFin 	 = new DateTime(2015, 02, 14, 0, 0 );//se incluye
		
		assertTrue(unPeriodo.seIntersectaConElPeriodo(fechaInicio, fechaFin));
	}
	
	/** Test- Dados unPeriodo de fechas, unafechaInicio y unafechaFin, se consulta si el periodo dado por las fechas incluye en algún punto 
	 * con unPeriodo.
	 * Como fechaInicio se incluyen dentro de unPeriodo, se responde true.
	 * @author abel*/
	public void test2_DadosUnPeriodoUnaFechaDeInicioYUnaFechaDeFinSeConsultaSiElPeriodoRepresentadoPorLasFechasDadasSeIncluyeDentroDelPeriodoYMeRespondeQueSi() {
		// unPeriodo va del 05/01/2015 al 15/02/2015
		this.fechaInicio = new DateTime(2015, 01, 06, 0, 0 );//se incluye
		this.fechaFin 	 = new DateTime(2015, 02, 16, 0, 0 );
		
		assertTrue(unPeriodo.seIntersectaConElPeriodo(fechaInicio, fechaFin));
	}
	
	/** Test- Dados unPeriodo de fechas, unafechaInicio y unafechaFin, se consulta si el periodo dado por las fechas incluye en algún punto 
	 * con unPeriodo.
	 * Como fechaFin se incluye dentro de unPeriodo, se responde true.
	 * @author abel*/
	public void test3_DadosUnPeriodoUnaFechaDeInicioYUnaFechaDeFinSeConsultaSiElPeriodoRepresentadoPorLasFechasDadasSeIncluyeDentroDelPeriodoYMeRespondeQueSi() {
		// unPeriodo va del 05/01/2015 al 15/02/2015
		this.fechaInicio = new DateTime(2015, 01, 04, 0, 0 );
		this.fechaFin 	 = new DateTime(2015, 02, 14, 0, 0 );//se incluye
		
		assertTrue(unPeriodo.seIntersectaConElPeriodo(fechaInicio, fechaFin));
	}
	
	/** Test- Dados unPeriodo de fechas, unafechaInicio y unafechaFin, se consulta si el periodo dado por las fechas incluye en algún punto 
	 * con unPeriodo.
	 * Como fechaInicio es igual a la fecha de inicio de unPeriodo, se responde true.
	 * @author abel*/
	public void test4_DadosUnPeriodoUnaFechaDeInicioYUnaFechaDeFinSeConsultaSiElPeriodoRepresentadoPorLasFechasDadasSeIncluyeDentroDelPeriodoYMeRespondeQueSi() {
		// unPeriodo va del 05/01/2015 al 15/02/2015
		this.fechaInicio = new DateTime(2015, 01, 05, 0, 0 );//se incluye
		this.fechaFin 	 = new DateTime(2015, 02, 16, 0, 0 );
		
		assertTrue(unPeriodo.seIntersectaConElPeriodo(fechaInicio, fechaFin));
	}
	
	/** Test- Dados unPeriodo de fechas, unafechaInicio y unafechaFin, se consulta si el periodo dado por las fechas incluye en algún punto 
	 * con unPeriodo.
	 * Como fechaInicio es igual a la fecha de fin de unPeriodo, se responde true.
	 * @author abel*/
	public void test5_DadosUnPeriodoUnaFechaDeInicioYUnaFechaDeFinSeConsultaSiElPeriodoRepresentadoPorLasFechasDadasSeIncluyeDentroDelPeriodoYMeRespondeQueSi() {
		// unPeriodo va del 05/01/2015 al 15/02/2015
		this.fechaInicio = new DateTime(2015, 01, 15, 0, 0 );//se incluye
		this.fechaFin 	 = new DateTime(2015, 02, 16, 0, 0 );
		
		assertTrue(unPeriodo.seIntersectaConElPeriodo(fechaInicio, fechaFin));
	}
	
	/** Test- Dados unPeriodo de fechas, unafechaInicio y unafechaFin, se consulta si el periodo dado por las fechas incluye en algún punto 
	 * con unPeriodo.
	 * Como fechaFin es igual a la fecha de inicio de unPeriodo, se responde true.
	 * @author abel*/
	public void test6_DadosUnPeriodoUnaFechaDeInicioYUnaFechaDeFinSeConsultaSiElPeriodoRepresentadoPorLasFechasDadasSeIncluyeDentroDelPeriodoYMeRespondeQueSi() {
		// unPeriodo va del 05/01/2015 al 15/02/2015
		this.fechaInicio = new DateTime(2015, 01, 04, 0, 0 );
		this.fechaFin 	 = new DateTime(2015, 02, 05, 0, 0 );//se incluye
		
		assertTrue(unPeriodo.seIntersectaConElPeriodo(fechaInicio, fechaFin));
	}
	
	/** Test- Dados unPeriodo de fechas, unafechaInicio y unafechaFin, se consulta si el periodo dado por las fechas incluye en algún punto 
	 * con unPeriodo.
	 * Como fechaFin es igual a la fecha de fin de unPeriodo, se responde true.
	 * @author abel*/
	public void test7_DadosUnPeriodoUnaFechaDeInicioYUnaFechaDeFinSeConsultaSiElPeriodoRepresentadoPorLasFechasDadasSeIncluyeDentroDelPeriodoYMeRespondeQueSi() {
		// unPeriodo va del 05/01/2015 al 15/02/2015
		this.fechaInicio = new DateTime(2015, 01, 04, 0, 0 );
		this.fechaFin 	 = new DateTime(2015, 02, 15, 0, 0 );//se incluye
		
		assertTrue(unPeriodo.seIntersectaConElPeriodo(fechaInicio, fechaFin));
	}

	/** Test- Dados unPeriodo de fechas, unafechaInicio y unafechaFin, se consulta si el periodo dado por las fechas incluye en algún punto 
	 * con unPeriodo.
	 * Como ambas, fechaInicio y fechaFin, son fechas anteriores a la fecha de inicio de unPeriodo, se responde false.
	 * @author abel*/
	public void test8_DadosUnPeriodoUnaFechaDeInicioYUnaFechaDeFinSeConsultaSiElPeriodoRepresentadoPorLasFechasDadasSeIncluyeDentroDelPeriodoYMeRespondeQueNo() {
		// unPeriodo va del 05/01/2015 al 15/02/2015
		this.fechaInicio = new DateTime(2015, 01, 03, 0, 0 );
		this.fechaFin 	 = new DateTime(2015, 01, 04, 0, 0 );
		
		assertFalse(unPeriodo.seIntersectaConElPeriodo(fechaInicio, fechaFin));
	}
	
	/** Test- Dados unPeriodo de fechas, unafechaInicio y unafechaFin, se consulta si el periodo dado por las fechas incluye en algún punto 
	 * con unPeriodo.
	 * Como ambas, fechaInicio y fechaFin, son fechas posteriores a la fecha de fin de unPeriodo, se responde false.
	 * @author abel*/
	public void test9_DadosUnPeriodoUnaFechaDeInicioYUnaFechaDeFinSeConsultaSiElPeriodoRepresentadoPorLasFechasDadasSeIncluyeDentroDelPeriodoYMeRespondeQueNo() {
		// unPeriodo va del 05/01/2015 al 15/02/2015
		this.fechaInicio = new DateTime(2015, 02, 16, 0, 0 );
		this.fechaFin 	 = new DateTime(2015, 02, 17, 0, 0 );
		
		assertFalse(unPeriodo.seIntersectaConElPeriodo(fechaInicio, fechaFin));
	}
	
	/** Test- Dados unPeriodo de fechas, unaFechaInicio y unaFechaFin, se consulta la cantidad de dias que incluye entre unPeriodo y las fechas dadas.
	 * @author abel*/
	public void test10_DadosUnPeriodoUnaFechaDeInicioYUnaFechaDeFinSeConsultaLaCantidadDeDiasIncludidosEnLaInterseccionDeUnPeriodoYLasFechasDadasYMeLoResponde() {
		// unPeriodo va del 05/01/2015 al 15/02/2015
		
		this.fechaInicio = new DateTime(2015, 01, 16, 0, 0 );//se incluye
		this.fechaFin 	 = new DateTime(2015, 02, 01, 0, 0 );//se incluye
		
		assertEquals(unPeriodo.cantidadDeDiasIncluidosEnElPeriodo(fechaInicio, fechaFin), (Integer) 16);
		
		this.fechaInicio = new DateTime(2015, 01, 04, 0, 0 );
		this.fechaFin 	 = new DateTime(2015, 02, 01, 0, 0 );//se incluye
		
		assertEquals(unPeriodo.cantidadDeDiasIncluidosEnElPeriodo(fechaInicio, fechaFin), (Integer) 27);
		
		this.fechaInicio = new DateTime(2015, 01, 31, 0, 0 );//se incluye
		this.fechaFin 	 = new DateTime(2015, 02, 06, 0, 0 );
		
		assertEquals(unPeriodo.cantidadDeDiasIncluidosEnElPeriodo(fechaInicio, fechaFin), (Integer) 06);
		
		this.fechaInicio = new DateTime(2015, 01, 05, 0, 0 );//es igual a la fecha de inicio de unPeriodo
		this.fechaFin 	 = new DateTime(2015, 02, 06, 0, 0 );
		
		assertEquals(unPeriodo.cantidadDeDiasIncluidosEnElPeriodo(fechaInicio, fechaFin), (Integer) 32);
				
		this.fechaInicio = new DateTime(2015, 02, 15, 0, 0 );//es igual a la fecha de fin de unPeriodo
		this.fechaFin 	 = new DateTime(2015, 02, 28, 0, 0 );
		
		assertEquals(unPeriodo.cantidadDeDiasIncluidosEnElPeriodo(fechaInicio, fechaFin), (Integer) 00);

		this.fechaInicio = new DateTime(2014, 11, 01, 0, 0 );
		this.fechaFin 	 = new DateTime(2015, 01, 05, 0, 0 );//es igual a la fecha de inicio de unPeriodo
		
		assertEquals(unPeriodo.cantidadDeDiasIncluidosEnElPeriodo(fechaInicio, fechaFin), (Integer) 00);
		
		this.fechaInicio = new DateTime(2014, 11, 01, 0, 0 );
		this.fechaFin 	 = new DateTime(2015, 02, 15, 0, 0 );//es igual a la fecha de fin de unPeriodo
		
		assertEquals(unPeriodo.cantidadDeDiasIncluidosEnElPeriodo(fechaInicio, fechaFin), (Integer) 41);
	}
	
	
	/** Test- Dados unPeriodo de fechas, unafechaInicio y unafechaFin, se consulta si unPeriodo incluye en algún punto 
	 * con las fechas dadas como parámetro.
	 * Como las fechas incluidas en unPeriodo están dentro de las fechas dadas como parámetro, se responde true.
	 * @author abel*/
	public void test11_DadosUnPeriodoUnaFechaDeInicioYUnaFechaDeFinSeConsultaSiUnPeriodoSeIncluyeDentroDeLasFechasDadasComoParametroYMeRespondeQueSi() {
		// unPeriodo va del 05/01/2015 al 15/02/2015
		
		this.fechaInicio = new DateTime(2014, 12, 30, 0, 0 );
		this.fechaFin 	 = new DateTime(2015, 02, 17, 0, 0 );
		//unPeriodo se incluye dentro de estas fechas
		
		assertTrue(unPeriodo.seIntersectaConElPeriodo(fechaInicio, fechaFin));
	}
	
	
	/** Test- Dados unPeriodo de fechas y unaFecha, se consulta si la fecha se incluye dentro del periodo.
	 * Como la fecha est� incluida dentro del periodo se responde true.
	 * @author abel*/
	public void test12_DadoUnPeriodoDeFechasYUnaFechaConsultoSiLaFechaSeEncuentraDentroDelPeriodoYMeRespondeQueSi() {
		// unPeriodo va del 05/01/2015 al 15/02/2015
		
		DateTime unaFecha = new DateTime(2015, 01, 10, 0, 0);
		
		assertTrue(unPeriodo.estaIncluidoEnElPeriodoLaFecha(unaFecha));
		
		unaFecha = new DateTime(2015, 01, 5, 0, 0);
		
		assertTrue(unPeriodo.estaIncluidoEnElPeriodoLaFecha(unaFecha));
		
		unaFecha = new DateTime(2015, 02, 15, 0, 0);
		
		assertTrue(unPeriodo.estaIncluidoEnElPeriodoLaFecha(unaFecha));
	}


	/** Test- Dados unPeriodo de fechas y unaFecha, se consulta si la fecha se incluye dentro del periodo.
	 * Como la fecha no est� incluida dentro del periodo se responde false.
	 * @author abel*/
	public void test13_DadoUnPeriodoDeFechasYUnaFechaConsultoSiLaFechaSeEncuentraDentroDelPeriodoYMeRespondeQueNo() {
		// unPeriodo va del 05/01/2015 al 15/02/2015
		DateTime unaFecha = new DateTime(2014, 01, 01, 0, 0);
		
		assertFalse(unPeriodo.estaIncluidoEnElPeriodoLaFecha(unaFecha));
	}	


	/** Test- Dados dos periodos de fechas consulto si ambos periodos son iguales. Como ambos periodos de fechas
	 * tienen distintas fechas de inicio y de fin me responde false.
	 * @author abel*/
	public void test14_DadosDosPeriodosDeFechasConsultoSiAmbosPeriodosSonIgualesYMeRespondeQueNo() {
		PeriodoDeFechas periodo1 = new PeriodoDeFechas(new DateTime(), (new DateTime()).plusDays(5));
		PeriodoDeFechas periodo2 = new PeriodoDeFechas(new DateTime(), (new DateTime()).plusDays(6));

		assertNotSame(periodo1, periodo2);
	}
	
	
	/** Test- Dados dos periodos de fechas consulto si ambos periodos son iguales. Como ambos periodos de fechas
	 * tienen mismas fechas de inicio y de fin me responde true.
	 * @author abel*/
	public void test15_DadosDosPeriodosDeFechasConsultoSiAmbosPeriodosSonIgualesYMeRespondeQueSi() {
		PeriodoDeFechas periodo1 = new PeriodoDeFechas(new DateTime(), (new DateTime()).plusDays(5));
		PeriodoDeFechas periodo2 = new PeriodoDeFechas(new DateTime(), (new DateTime()).plusDays(5));

		assertEquals(periodo1, periodo2);
	}
}