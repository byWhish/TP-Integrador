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

	public Reserva( DateTime checkIn, DateTime checkOut, Integer cantPasajeros, Usuario usuario, Habitacion habitacion ){
		this.fechaEntrada = checkIn;
		this.fechaSalida = checkOut;
		this.cantidadPasajeros = cantPasajeros;
		this.usuario = usuario;
		this.habitacion = habitacion;
		
	}
	
	//devuelvo la ciudad de la reserva
	public String ciudadDeReserva(){
		return this.habitacion.getCiudad();
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


	public Integer getCantidadPasajeros() {
		return cantidadPasajeros;
	}


	public Usuario getUsuario() {
		return usuario;
	}

}
