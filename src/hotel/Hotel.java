package hotel;

import java.util.Collection;

public class Hotel {
	
	private Collection<Habitacion> habitaciones;
	private String ciudad;
	
	public Collection<Habitacion> getHabitaciones(){
		return this.habitaciones;
	}
	
	public String getCiudad(){
		return this.ciudad;
	}
	

}
