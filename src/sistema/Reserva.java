package sistema;

import org.joda.time.DateTime;

import filtro.Filtrable;
import hotel.Habitacion;
import hotel.Hotel;
import hotel.PeriodoDeFechas;
import usuario.Hotelero;
import usuario.Pasajero;
import usuario.Usuario;

public class Reserva implements Filtrable{

	//una reseva tiene una fecha de entrada, de salida, una cantidad de pasajeros un usuario y una habitacion
	private DateTime fechaEntrada;
	private DateTime fechaSalida;
	private Integer cantidadPasajeros;
	private Usuario usuario;
	private Habitacion habitacion;

	/** Constructor de la clase Reserva.
	 * @param fechaEntrada	DateTime
	 * @param fechaSalida	DateTime
	 * @param cantPasajero	Integer
	 * @param usuario		Usuario
	 * @param habitacion	Habitacion*/
	public Reserva( DateTime fechaEntrada, DateTime fechaSalida, Integer cantPasajeros, Usuario usuario, Habitacion habitacion ){
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.cantidadPasajeros = cantPasajeros;
		this.usuario = usuario;
		this.habitacion = habitacion;
	}
	
	public Habitacion getHabitacion() {
		return habitacion;
	}

	/* CREO QUE ESTE MENSAJE NO ES NECESARIO... abel
	 
	 * public void setHabitacion(Habitacion habitacion) {
		this.habitacion = habitacion;
	}*/

	/** Se responde con un periodo de fechas que va desde la fecha de entrada 
	 * hasta la fecha de salida de la reserva recibidora.
	 * @author abel*/
	public PeriodoDeFechas periodoDeLaReserva() {
		return new PeriodoDeFechas(this.fechaEntrada, this.fechaSalida);
	}

	public DateTime getFechaEntrada() {
		return fechaEntrada;
	}

	public Integer getCantidadPasajeros() {
		return cantidadPasajeros;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public Hotel getHotel() {
		return this.habitacion.getHotel();
	}
	
	public String getCiudad(){
		return this.habitacion.getCiudad();
	}

}
