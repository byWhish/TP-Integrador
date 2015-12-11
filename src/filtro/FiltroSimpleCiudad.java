package filtro;

import java.util.Collection;

import hotel.Habitacion;
import usuario.Hotelero;
import usuario.Pasajero;
import usuario.Usuario;

public class FiltroSimpleCiudad extends FiltroComponente{

	public String ciudad;

	public FiltroSimpleCiudad( String ciudad ){
		this.ciudad = ciudad;
	}
	
	@Override
	public boolean cumple(Filtrable item) {
		return item.getCiudad() == this.ciudad;
	}
}
