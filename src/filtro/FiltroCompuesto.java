package filtro;

import java.util.Collection;

import hotel.Habitacion;
import usuario.Hotelero;
import usuario.Pasajero;
import usuario.Usuario;

public class FiltroCompuesto extends FiltroComponente{
	
	private Collection<FiltroComponente> filtros;
	
	
	//agrego un filtro nuevo
	public void componerFiltro( FiltroComponente filtro){
		filtros.add(filtro);
	}

	//recorrotodos los fltros para ver si cumple
	
	@Override
	public boolean cumple(Filtrable item) {
		boolean result = true;
		for(FiltroComponente f : filtros){
			result = result && f.cumple(item);
		}
		return result;
	}


}
