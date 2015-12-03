package hotel;

/** La clase Servicio modela un servicio que puede ser ofrecido un/os hoteles.
 * 
 * Un Servicio posee un nombre del servicio a ofrecer, una descripción breve y un precio.*/
public class Servicio {

	private String nombre;
	private String breveDescripcion;
	private Double precio;
	
	/** Constructor de Servicio.
	 * Recibe como parámetro un nombre (String) del servicio, una breveDescripcion (String) y 
	 * el precio (Double) del servicio.*/
	public Servicio(String unNombre, String breveDescripcion, Double unPrecio) {
		this.nombre = unNombre;
		this.breveDescripcion = breveDescripcion;
		this.setPrecioDelServicio(unPrecio);
	}

	/** Se responde con el nombre del Servicio recibidor.*/
	public String getNombre() {
		return this.nombre;
	}
	
	/** Se responde con la descripción del Servicio recibidor.*/
	public String getBreveDescripcion() {
		return this.breveDescripcion;
	}
	
	/** Se reponde con el precio del Servicio recibidor.*/
	public Double getPrecio() {
		return this.precio;
	}
	
	/** Setter que asigna unPrecio al Servicio recibidor.*/
	public void setPrecioDelServicio(Double unPrecio) {
		this.precio = unPrecio;
	}
}
