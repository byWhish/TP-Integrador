package hotel;

import org.joda.time.DateTime;
import org.joda.time.Days;

/** PeriodoDeFechas representa a un conjunto de fechas, que va desde una fecha de inicio
 * hasta una fecha final.
 * @author abel*/
public class PeriodoDeFechas {

	private DateTime fechaInicio;
	private DateTime fechaFin;
	
	
	/** Constructor de la clase PeriodoDeFechas.
	 * @param fechaInicio DateTime
	 * @param fechaFin DateTime
	 * @author abel*/
	public PeriodoDeFechas(DateTime fechaInicio, DateTime fechaFin) {
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
	}
	
	
	/** Se responde si unaFecha dada como par�metro est� incluido dentro del periodo recibidor.
	 * @author abel*/
	public boolean estaIncluidoEnElPeriodoLaFecha(DateTime unaFecha) {
		return 	( unaFecha.isAfter(this.fechaInicio) && unaFecha.isBefore(this.fechaFin) ) ||
				( unaFecha.isEqual(this.fechaInicio)  || unaFecha.isEqual(this.fechaFin) );
	}
		
	
	/** Dado un periodo de fechas fechaInicio y fechaFin, se responde si el periodo dado se incluye dentro del periodo
	 * recibidor.
	 * @author abel*/
	public boolean seIntersectaConElPeriodo(DateTime fechaInicio, DateTime fechaFin) {
		return 	( fechaInicio.isAfter(this.fechaInicio) && fechaInicio.isBefore(this.fechaFin) ) ||	// (  [  )  :: () = fechas this; [] = fechas dadas como parámetro 
				( fechaFin.isAfter(this.fechaInicio) && fechaFin.isBefore(this.fechaFin) ) ||		// (  ]  )
				( fechaInicio.isEqual(this.fechaInicio) || fechaInicio.isEqual(this.fechaFin) ) ||	// ([  )[     ]
				( fechaFin.isEqual(this.fechaInicio) || fechaFin.isEqual(this.fechaFin) ) ||		// [     ](  ])
				( this.fechaInicio.isAfter(fechaInicio) && this.fechaInicio.isBefore(fechaFin));	// [  ()  ]
	}

	/** Dado unPeriodo de fechas, se responde si el periodo dado se incluye dentro del periodo
	 * recibidor.
	 * @author abel*/
	public boolean seIntersectaConElPeriodo(PeriodoDeFechas unPeriodo) {
		return this.seIntersectaConElPeriodo(unPeriodo.getFechaInicio(), unPeriodo.getFechaFin());
	}
	
	/** Se responde con la fecha de fin del periodo recibor.
	 * @author abel*/
	private DateTime getFechaFin() {
		return this.fechaFin;
	}


	/** Se responde con la fecha de inicio del periodo recibor.
	 * @author abel*/
	private DateTime getFechaInicio() {
		return this.fechaInicio;
	}


	/** Dados una fechaInicio y una fechaFin, se responde con la cantidad de días que intersectan
	 * al periodo recibidor (es decir, se responde con un Integer que representa la cantidad de 
	 * fechas en común para ambos periodos).
	 * 
	 * PRECONDICION:
	 * 				Se presupone que las fechas del periodo recibidor intersectan en algún punto con
	 * 				las fechas dadas como parámetro.
	 * @author abel*/
	public Integer cantidadDeDiasIncluidosEnElPeriodo(DateTime fechaInicio, DateTime fechaFin) {
		DateTime cotaInf = this.fechaInicio;
		DateTime cotaSup = this.fechaFin;
		//Si fechaInicio es superior a la fecha cotaInf...
		if(fechaInicio.isAfter(cotaInf)) {
			cotaInf = fechaInicio;
		}
		//Si fechaFin es inferior a la fecha cotaSup...
		if(fechaFin.isBefore(cotaSup)) {
			cotaSup = fechaFin;
		}
		
		return Days.daysBetween(cotaInf, cotaSup).getDays();//...devuelvo la cantidad de días entre estas dos fechas.
	}
	
	@Override
	/** Dado unPeriodoDeFechas, comparo con el periodo recibidor si ambas fechas de inicio y fin son iguales.
	 * @author abel*/
	public boolean equals(Object unPeriodo) {
		PeriodoDeFechas periodo = (PeriodoDeFechas) unPeriodo;
		return this.getFechaInicio().isEqual(periodo.getFechaInicio()) && this.getFechaFin().isEqual(periodo.getFechaFin());
	}
	
}