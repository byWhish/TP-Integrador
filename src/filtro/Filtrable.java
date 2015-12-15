package filtro;

import org.joda.time.DateTime;

import hotel.Hotel;
import hotel.PeriodoDeFechas;
import usuario.Usuario;

public interface Filtrable {
	
	public String getCiudad();

	public Usuario getUsuario();

	public Hotel getHotel();

	public int getCapacidadMaxima();

	public PeriodoDeFechas getPeriodoDeFecha();

}
