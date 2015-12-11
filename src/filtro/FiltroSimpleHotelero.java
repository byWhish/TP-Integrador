package filtro;

import usuario.Hotelero;

public class FiltroSimpleHotelero extends FiltroComponente{
	
	private Hotelero hotelero;
	
	public FiltroSimpleHotelero( Hotelero hotelero ) {
		this.hotelero = hotelero;
	}

	@Override
	public boolean cumple(Filtrable item) {
		
		return item.getHotel() == this.hotelero.getHotel();
	}

}
