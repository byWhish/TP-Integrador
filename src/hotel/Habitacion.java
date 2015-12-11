package hotel;

import java.util.ArrayList;
import java.util.Collection;

import org.joda.time.DateTime;
import org.joda.time.Days;

import sistema.Filtrable;
import sistema.Filtro;
import usuario.Hotelero;

/** Habitacion modela toda la información relevante de una habitación de un determinado Hotel.
 * Posee la siguiente información:
 * 
 * - una capacidad máxima: cantidad máxima de pasajeros que pueden ocupar la habitación;
 * - descripción y cant. de camas: cantidad y tipo de camas disponibles en la habitación;
 * - lista de servicios: servicios disponibles en la habitación;
 * 
 * Cada Habitación es administrada por el administrador del Hotel donde esta se encuentra dicha habitación
 * y este administrador es quien da de alta una habitación y le asigna sus caracteristicas.
 * 
 * El administrador también se encarga de asignar un precio base de reserva por día y 
 * distintos periodos de promociones, en los que el precio de la habitación cambia a un precio
 * promocional dentro de las fechas dadas por este periodo.
 * 
 * El administrador puede asignar fechas no-reservables, las cuales son, periodos de fechas en
 * los que la habitación no puede ser reservada por nadie.
 * @author abel */
public class Habitacion implements Filtrable{

		private Integer capacidadMaxima;
		private String camas;
		private Collection<String> servicios;
		private Double precioBasePorNoche;
		private Collection<PeriodoDeFechas> fechasCanceladas = new ArrayList<PeriodoDeFechas>();
		private Collection<Promocion> promociones = new ArrayList<Promocion>();
		private Hotel hotel;

		/** Constructor de Habitacion que recibe como parámetros un precioBase por noche, un hotel, una capMax (capacidad máxima)
		 * y las camas.
		 * @param precioBase Double
		 * @param hotel Hotel
		 * @param capMax Integer
		 * @param camas String
		 * @author abel*/
		public Habitacion(Double precioBase, Hotel hotel, Integer capMax, String camas) {
			this.setPrecioBasePorNoche(precioBase);
			this.capacidadMaxima = capMax;
			this.camas = camas;
			this.hotel = hotel;
		}
		

		/** Se obtiene el precio de una estadía de la habitación entre las fechas
		 * que va desde una fechaEntrada hasta una fechaSalida.
		 * 
		 * AVISO: 
		 * 		Este mensaje solo calcula el precio de la estadía en el periodo de fechas dado, no consulta
		 * 		ni toma en cuenta si la habitación está disponible o no en ese periodo de fechas.
		 * 		Esa	responsabilidad es del Sistema.
		 * @author abel*/
		public Double precioPorEstadia(DateTime fechaEntrada, DateTime fechaSalida) {
			Double precioPorLaEstadia = 0.0;
			Integer cantidadDeDias = Days.daysBetween(fechaEntrada, fechaSalida).getDays();//...cantidad de días entre fechaEntrada y fechaSalida
			
			// Calculo el precio de los días de promoción, si es que hay alguno...
			for(Promocion unaPromocion: promociones) {
				if(unaPromocion.getPeriodoDePromocion().seIntersectaConElPeriodo(fechaEntrada, fechaSalida)) {
					Integer diasDePromocion = unaPromocion.getPeriodoDePromocion().cantidadDeDiasIncluidosEnElPeriodo(fechaEntrada, fechaSalida);
					
					precioPorLaEstadia += unaPromocion.getPrecioPorDia() * diasDePromocion;
					cantidadDeDias -= diasDePromocion;
				}
			}
			//...al precio total de la/s promoción/es le sumo la cantidad de días x precio básico por noche...
			precioPorLaEstadia += cantidadDeDias * this.getPrecioBasePorNoche();
			
			return precioPorLaEstadia;
		}
		
		
		/** Se responde si la habitación tiene permitido concretar reservas entre las fechas fechaInicio 
		 * y fechaFin.
		 * 	Todas las habitaciones pueden tener uno o varios periodos de fechas en que no puede ser reservada.
		 * 	Este periodo de fechas es dado por el administrador del Hotel.
		 * @author abel*/
		public boolean noEstaCanceladaParaLasFechas(DateTime fechaInicio, DateTime fechaFin) {
			for(PeriodoDeFechas unPeriodoNoReservable: fechasCanceladas) {
				if(unPeriodoNoReservable.seIntersectaConElPeriodo(fechaInicio, fechaFin)) {
					return false;
				}
			}
			return true;
		}
		
		
		/** Se agrega un periodo de promoción a las promociones de la habitación.
		 * @author abel*/
		public void agregarNuevoPeriodoDePromocion(DateTime fechaInicio, DateTime fechaFin, Double precio) {
			this.promociones.add(new Promocion(fechaInicio, fechaFin, precio));			
		}
		
		
		/** Dados una fechaInicio y una fechaFin, se agrega un nuevo periodo de fechas en los que la habitación
		 * no estará disponible.
		 * Este periodo de fechas puede ser cargado por el administrador del hotel al que pertenece
		 * esta habitación.
		 * @author abel*/
		public void agregarNuevoPeriodoNoReservable(DateTime fechaInicio, DateTime fechaFin) {
			this.fechasCanceladas.add(new PeriodoDeFechas(fechaInicio, fechaFin));
		}

//Getters&Setters-----------------------------------------------------------------------------
		
		/** Se responde con el Hotel al que pertenece la habitación.
		 * @author abel*/
		public Hotel getHotel() {
			return hotel;
		}
		
		/** Dado un precio, se actualiza el valor del precio base para habitacion recibidora.
		 * @author abel*/
		public void setPrecioBasePorNoche(Double precioBase) {
			this.precioBasePorNoche = precioBase;			
		}
		
		/** Se responde cuál es el precio básico por noche para la habitación.
		 * @author abel*/
		public Double getPrecioBasePorNoche() {
			return this.precioBasePorNoche;
		}
		
		//Con esto devuelvo la ciudad de la habitacion
		public String getCiudad(){
			return this.getHotel().getCiudad();
		}

		/** Se responde con la capacidad m�xima de pasajeros que la habitaci�n puede albergar.*/
		public int getCapacidadMaxima() {
			return this.capacidadMaxima;
		}
		
		/** Se responde con la informaci�n de camas de la habitaci�n.
		 * @author abel*/
		public String getCamas() {
			return this.camas;
		}
		
		/** Se responde con la lista de servicios ofrecidos por la habitaci�n.
		 * @author abel*/
		public Collection<String> getServicios() {
			return this.servicios;
		}
//finSetters&Getters--------------------------------------------------------------------------

		//
		public boolean esDelHotelero( Hotelero hotelero ) {
			// TODO Auto-generated method stub
			return this.getHotel() == hotelero.getHotel();
		}


		@Override
		public boolean cumple(String ciudad) {
			
			return this.getCiudad() == ciudad;
		}
}