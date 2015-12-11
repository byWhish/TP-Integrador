package hotel;

/** La clase Servicio modela un servicio que puede ser ofrecido un/os hoteles.
 * 
 * Un Servicio posee un nombre del servicio a ofrecer, una descripción breve y un precio.
 * @author abel*/
public class Servicio {

	private String nombre;
	private String breveDescripcion;
	private Double precio;
	
	/** Constructor de la clase Servicio.
	 * @param unNombre String
	 * @param breveDescripcion String
	 * @param unPrecio Double
	 * @author abel*/
	public Servicio(String unNombre, String breveDescripcion, Double unPrecio) {
		this.nombre = unNombre;
		this.breveDescripcion = breveDescripcion;
		this.setPrecioDelServicio(unPrecio);
	}

	/** Se responde con el nombre del Servicio recibidor.
	 * @author abel*/
	public String getNombre() {
		return this.nombre;
	}
	
	/** Se responde con la descripción del Servicio recibidor.
	 * @author abel*/
	public String getBreveDescripcion() {
		return this.breveDescripcion;
	}
	
	/** Se reponde con el precio del Servicio recibidor.
	 * @author abel*/
	public Double getPrecio() {
		return this.precio;
	}
	
	/** Setter que asigna unPrecio al Servicio recibidor.
	 * @author abel*/
	public void setPrecioDelServicio(Double unPrecio) {
		this.precio = unPrecio;
	}
}
