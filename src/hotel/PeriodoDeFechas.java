package hotel;

import org.joda.time.DateTime;
import org.joda.time.Days;

/** PeriodoDeFechas representa a un conjunto de fechas, que va desde una fecha de inicio
 * hasta una fecha final.*/
public class PeriodoDeFechas {

	private DateTime fechaInicio;
	private DateTime fechaFin;
	
	
	/** Constructor de PeriodoDeFechas que recibe como parámetro la fechaInicio y fechaFin de un periodo de 
	 * fechas.
	 * */
	public PeriodoDeFechas(DateTime fechaInicio, DateTime fechaFin) {
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
	}
	
	
	/** Dado un periodo de fechas fechaInicio y fechaFin, se responde si el periodo dado se incluye dentro del periodo
	 * recibidor.
	 * */
	public boolean seIntersectaConElPeriodo(DateTime fechaInicio, DateTime fechaFin) {
		return 	( fechaInicio.isAfter(this.fechaInicio) && fechaInicio.isBefore(this.fechaFin) ) ||	// (  [  )  :: () = fechas this; [] = fechas dadas como parámetro 
				( fechaFin.isAfter(this.fechaInicio) && fechaFin.isBefore(this.fechaFin) ) ||		// (  ]  )
				( fechaInicio.isEqual(this.fechaInicio) || fechaInicio.isEqual(this.fechaFin) ) ||	// ([  )[     ]
				( fechaFin.isEqual(this.fechaInicio) || fechaFin.isEqual(this.fechaFin) ) ||		// [     ](  ])
				( this.fechaInicio.isAfter(fechaInicio) && this.fechaInicio.isBefore(fechaFin));	// [  ()  ]
	}

	/** Dado unPeriodo de fechas, se responde si el periodo dado se incluye dentro del periodo
	 * recibidor.
	 * */
	public boolean seIntersectaConElPeriodo(PeriodoDeFechas unPeriodo) {
		return this.seIntersectaConElPeriodo(unPeriodo.getFechaInicio(), unPeriodo.getFechaFin());
	}
	
	/** Se responde con la fecha de fin del periodo recibor.*/
	private DateTime getFechaFin() {
		return this.fechaFin;
	}


	/** Se responde con la fecha de inicio del periodo recibor.*/
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
	 * */
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
	
}