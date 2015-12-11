package sistema;

import java.util.Collection;

import hotel.Habitacion;
import usuario.Hotelero;
import usuario.Usuario;

public abstract class FiltroComponente {
	
	public abstract boolean cumpleConLaBusqueda( Filtrable filtrable, String ciudad  );

}
