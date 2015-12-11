package filtro;

import hotel.Hotel;
import usuario.Hotelero;
import usuario.Pasajero;
import usuario.Usuario;

public interface Filtrable {
	
	public String getCiudad();

	public Usuario getUsuario();

	public Hotel getHotel();

}
