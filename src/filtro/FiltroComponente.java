package filtro;

import java.util.Collection;

import hotel.Habitacion;
import usuario.Hotelero;
import usuario.Pasajero;
import usuario.Usuario;

public abstract class FiltroComponente {
	
	public abstract boolean cumple( Filtrable item);

}
