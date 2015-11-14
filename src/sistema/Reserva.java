package sistema;

import org.joda.time.DateTime;

import usuario.Usuario;

public class Reserva {

	//una reseva tiene una fecha de checkIn, checkOut, una cantidad de pasajeros un usuario y una habitacion
	private DateTime fechaEntrada;
	private DateTime fechaSalida;
	private Integer cantidadPasajeros;
	private Usuario usuario;
	//private Habitacion habitacion;

	public Reserva( DateTime checkIn, DateTime checkOut, Integer cantPasajeros, Usuario usuario ){
		this.fechaEntrada = checkIn;
		this.fechaSalida = checkOut;
		this.cantidadPasajeros = cantPasajeros;
		this.usuario = usuario;
		
	}
	
	public DateTime getFechaEntrada() {
		return this.fechaEntrada;
	}
	
	public DateTime getFechaSalida(){
		return this.fechaSalida;
	}
	
	public Integer getcantidadPasajeros(){
		return this.cantidadPasajeros;
	}

	public Usuario getUsuario() {
		return usuario;
	}

}
