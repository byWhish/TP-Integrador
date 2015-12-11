package sistema;

import java.util.Collection;

import hotel.Habitacion;
import usuario.Hotelero;
import usuario.Usuario;

public class FiltroSimple extends FiltroComponente{

	@Override
	public boolean cumpleConLaBusqueda( Filtrable filtrable, String ciudad ) {
		return filtrable.cumple( ciudad );
	}
	
}
