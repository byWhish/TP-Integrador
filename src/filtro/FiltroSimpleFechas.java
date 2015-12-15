package filtro;

import hotel.PeriodoDeFechas;

public class FiltroSimpleFechas extends FiltroComponente{

	PeriodoDeFechas unPeriodoDeFechas;
	
	public FiltroSimpleFechas( PeriodoDeFechas unPeriodoDeFechas ) {
		this.unPeriodoDeFechas = unPeriodoDeFechas;
	}
	@Override
	public boolean cumple(Filtrable item) {
		return unPeriodoDeFechas.estaIncluidoEnElPeriodoLaFecha( item.getFechaEntrada() ) && 
				unPeriodoDeFechas.estaIncluidoEnElPeriodoLaFecha( item.getFechaSalida() );
	}

}
