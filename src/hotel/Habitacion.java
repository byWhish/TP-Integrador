package hotel;

import java.util.ArrayList;
import java.util.Collection;

import org.joda.time.DateTime;
import org.joda.time.Days;

import sistema.Filtrable;
import sistema.Filtro;
import usuario.Hotelero;

/** Habitacion modela toda la informaci贸n relevante de una habitaci贸n de un determinado Hotel.
 * Posee la siguiente informaci贸n:
 * 
 * - una capacidad m谩xima: cantidad m谩xima de pasajeros que pueden ocupar la habitaci贸n;
 * - descripci贸n y cant. de camas: cantidad y tipo de camas disponibles en la habitaci贸n;
 * - lista de servicios: servicios disponibles en la habitaci贸n;
 * 
 * Cada Habitaci贸n es administrada por el administrador del Hotel donde esta se encuentra dicha habitaci贸n
 * y este administrador es quien da de alta una habitaci贸n y le asigna sus caracteristicas.
 * 
 * El administrador tambi茅n se encarga de asignar un precio base de reserva por d铆a y 
 * distintos periodos de promociones, en los que el precio de la habitaci贸n cambia a un precio
 * promocional dentro de las fechas dadas por este periodo.
 * 
 * El administrador puede asignar fechas no-reservables, las cuales son, periodos de fechas en
 * los que la habitaci贸n no puede ser reservada por nadie.
 * @author abel */
public class Habitacion implements Filtrable{

		private Integer capacidadMaxima;
		private String camas;
		private Collection<String> servicios;
		private Double precioBasePorNoche;
		private Collection<PeriodoDeFechas> fechasCanceladas = new ArrayList<PeriodoDeFechas>();
		private Collection<Promocion> promociones = new ArrayList<Promocion>();
		private Hotel hotel;

		/** Constructor de Habitacion que recibe como par谩metros un precioBase por noche, un hotel, una capMax (capacidad m谩xima)
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
		

		/** Se obtiene el precio de una estad铆a de la habitaci贸n entre las fechas
		 * que va desde una fechaEntrada hasta una fechaSalida.
		 * 
		 * AVISO: 
		 * 		Este mensaje solo calcula el precio de la estad铆a en el periodo de fechas dado, no consulta
		 * 		ni toma en cuenta si la habitaci贸n est谩 disponible o no en ese periodo de fechas.
		 * 		Esa	responsabilidad es del Sistema.
		 * @author abel*/
		public Double precioPorEstadia(DateTime fechaEntrada, DateTime fechaSalida) {
			Double precioPorLaEstadia = 0.0;
			Integer cantidadDeDias = Days.daysBetween(fechaEntrada, fechaSalida).getDays();//...cantidad de d铆as entre fechaEntrada y fechaSalida
			
			// Calculo el precio de los d铆as de promoci贸n, si es que hay alguno...
			for(Promocion unaPromocion: promociones) {
				if(unaPromocion.getPeriodoDePromocion().seIntersectaConElPeriodo(fechaEntrada, fechaSalida)) {
					Integer diasDePromocion = unaPromocion.getPeriodoDePromocion().cantidadDeDiasIncluidosEnElPeriodo(fechaEntrada, fechaSalida);
					
					precioPorLaEstadia += unaPromocion.getPrecioPorDia() * diasDePromocion;
					cantidadDeDias -= diasDePromocion;
				}
			}
			//...al precio total de la/s promoci贸n/es le sumo la cantidad de d铆as x precio b谩sico por noche...
			precioPorLaEstadia += cantidadDeDias * this.getPrecioBasePorNoche();
			
			return precioPorLaEstadia;
		}
		
		
		/** Se responde si la habitaci贸n tiene permitido concretar reservas entre las fechas fechaInicio 
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
		
		
		/** Se agrega un periodo de promoci贸n a las promociones de la habitaci贸n.
		 * @author abel*/
		public void agregarNuevoPeriodoDePromocion(DateTime fechaInicio, DateTime fechaFin, Double precio) {
			this.promociones.add(new Promocion(fechaInicio, fechaFin, precio));			
		}
		
		
		/** Dados una fechaInicio y una fechaFin, se agrega un nuevo periodo de fechas en los que la habitaci贸n
		 * no estar谩 disponible.
		 * Este periodo de fechas puede ser cargado por el administrador del hotel al que pertenece
		 * esta habitaci贸n.
		 * @author abel*/
		public void agregarNuevoPeriodoNoReservable(DateTime fechaInicio, DateTime fechaFin) {
			this.fechasCanceladas.add(new PeriodoDeFechas(fechaInicio, fechaFin));
		}

//Getters&Setters-----------------------------------------------------------------------------
		
		/** Se responde con el Hotel al que pertenece la habitaci贸n.
		 * @author abel*/
		public Hotel getHotel() {
			return hotel;
		}
		
		/** Dado un precio, se actualiza el valor del precio base para habitacion recibidora.
		 * @author abel*/
		public void setPrecioBasePorNoche(Double precioBase) {
			this.precioBasePorNoche = precioBase;			
		}
		
		/** Se responde cu谩l es el precio b谩sico por noche para la habitaci贸n.
		 * @author abel*/
		public Double getPrecioBasePorNoche() {
			return this.precioBasePorNoche;
		}
		
		//Con esto devuelvo la ciudad de la habitacion
		public String getCiudad(){
			return this.getHotel().getCiudad();
		}

		/** Se responde con la capacidad m醲ima de pasajeros que la habitaci髇 puede albergar.*/
		public int getCapacidadMaxima() {
			return this.capacidadMaxima;
		}
		
		/** Se responde con la informaci髇 de camas de la habitaci髇.
		 * @author abel*/
		public String getCamas() {
			return this.camas;
		}
		
		/** Se responde con la lista de servicios ofrecidos por la habitaci髇.
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