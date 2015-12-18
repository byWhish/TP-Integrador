package filtro;

import org.joda.time.DateTime;
import org.joda.time.ReadableInstant;

import hotel.Hotel;
import hotel.PeriodoDeFechas;
import usuario.Usuario;

public interface Filtrable {
	
	public String getCiudad();

	public Usuario getUsuario();

	public Hotel getHotel();

	public int getCapacidadMaxima();

	public ReadableInstant getFechaEntrada();

}
