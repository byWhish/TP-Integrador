package filtro;

import org.joda.time.DateTime;

import hotel.PeriodoDeFechas;

public class FiltroSimpleFechasFuturas extends FiltroComponente{

	int cantidadDias;
	DateTime fechaFiltro;
	
	public FiltroSimpleFechasFuturas( int cantidadDias ) {
		this.fechaFiltro = new DateTime().plusDays(cantidadDias);
	}
	@Override
	public boolean cumple(Filtrable item) {
		return fechaFiltro.isBefore(item.getFechaEntrada());
	}

}
