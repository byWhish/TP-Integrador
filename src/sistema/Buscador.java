package sistema;

import org.joda.time.DateTime;

public class Buscador {
	
	private DateTime fechaIngreso;
	private DateTime fechaSalida;
	private Integer cantidadPasajeros;
	private String ciudad;
	
	public Buscador( DateTime checkIn, DateTime checkOut,Integer cantPasajeros,String  city ){
		
		this.fechaIngreso = checkIn;
		this.fechaSalida = checkOut;
		this.cantidadPasajeros = cantPasajeros;
		this.ciudad = city;
	}

}
