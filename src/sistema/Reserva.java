package sistema;

import org.joda.time.DateTime;

import hotel.Habitacion;
import hotel.Hotel;
import usuario.Usuario;

public class Reserva {

	//una reseva tiene una fecha de checkIn, checkOut, una cantidad de pasajeros un usuario y una habitacion
	private DateTime fechaEntrada;
	private DateTime fechaSalida;
	private Integer cantidadPasajeros;
	private Usuario usuario;
	private Habitacion habitacion;
	private Hotel hotel;

	public Reserva( DateTime checkIn, DateTime checkOut, Integer cantPasajeros, Usuario usuario ){
		this.setFechaEntrada(checkIn);
		this.setFechaSalida(checkOut);
		this.setCantidadPasajeros(cantPasajeros);
		this.setUsuario(usuario);
		
	}
	
	public String ciudadDeReserva(){
		return this.hotel.getCiudad();
	}
	

	public Habitacion getHabitacion() {
		return habitacion;
	}

	public void setHabitacion(Habitacion habitacion) {
		this.habitacion = habitacion;
	}



	public DateTime getFechaEntrada() {
		return fechaEntrada;
	}



	public void setFechaEntrada(DateTime fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}



	public DateTime getFechaSalida() {
		return fechaSalida;
	}



	public void setFechaSalida(DateTime fechaSalida) {
		this.fechaSalida = fechaSalida;
	}



	public Integer getCantidadPasajeros() {
		return cantidadPasajeros;
	}



	public void setCantidadPasajeros(Integer cantidadPasajeros) {
		this.cantidadPasajeros = cantidadPasajeros;
	}



	public Usuario getUsuario() {
		return usuario;
	}



	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}



	public Hotel getHotel() {
		return hotel;
	}



	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

}
