package filtro;

public class FiltroSimpleCantidadPasajeros extends FiltroComponente{

	Integer cantidadPasajeros;
	
public FiltroSimpleCantidadPasajeros( int cantidadPasajeros){
		this.cantidadPasajeros = cantidadPasajeros;
	}

	@Override
	public boolean cumple(Filtrable item) {
		
		return item.getCapacidadMaxima() >= cantidadPasajeros;
	}

}
