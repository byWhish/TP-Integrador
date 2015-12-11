package sistema;

import java.util.Collection;

import hotel.Habitacion;
import usuario.Hotelero;
import usuario.Usuario;

public class FiltroCompuesto extends FiltroComponente{
	
	private FiltroComponente and;
	private FiltroComponente or;

	@Override
	public boolean cumpleConLaBusqueda( Filtrable filtrable, String ciudad ) {
		// TODO Auto-generated method stub
		return false;
	}

}
