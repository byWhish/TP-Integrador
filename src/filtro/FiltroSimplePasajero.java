package filtro;

import usuario.Pasajero;

public class FiltroSimplePasajero extends FiltroComponente{

	private Pasajero pasajero;
	
	@Override
	public boolean cumple(Filtrable item) {
		
		return item.getUsuario() == this.pasajero;
	}
	
	
	public FiltroSimplePasajero( Pasajero pasajero ){
		this.pasajero = pasajero; 
	}

}
