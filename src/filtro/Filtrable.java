package filtro;

import org.joda.time.DateTime;

import hotel.Hotel;
import usuario.Usuario;

public interface Filtrable {
	
	public String getCiudad();

	public Usuario getUsuario();

	public Hotel getHotel();

	public DateTime getFechaEntrada();

	public DateTime getFechaSalida();

	public int getCapacidadMaxima();

}
