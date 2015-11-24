package hotel;

import org.joda.time.DateTime;


/** Promocion representa un periodo de fechas en donde el precio de reserva para una determinada Habitacion cambia a un precio promocional, que es
 * distinto del precio base de dicha habitación.
 * Es el administrador del Hotel donde se encuentra la Habitación el que se encarga de crear y asignar una determinada promoción a una determinada
 * habitación.*/
public class Promocion {

	private PeriodoDeFechas periodoDePromocion;
	private Double precioPorDia;

	/** Constructor que recibe como parámetro una fechaInicio, una fechaFin y el precio para cada una de las fechas dentro de este periodo
	 * de días.*/
	public Promocion(DateTime fechaInicio, DateTime fechaFin, Double precioPorDia) {
		this.periodoDePromocion= new PeriodoDeFechas(fechaInicio, fechaFin);
		this.precioPorDia = precioPorDia;
	}
	
	
	/** Se responde con el periodo de fechas en que la promoción está vigente.*/
	public PeriodoDeFechas getPeriodoDePromocion() {
		return this.periodoDePromocion;
	}
	
	
	/** Se responde con el precio por día para cada uno de los días de esta promoción.*/
	public Double getPrecioPorDia() {
		return this.precioPorDia;
	}
}