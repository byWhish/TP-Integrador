package test;

import junit.framework.TestCase;
import sistema.Sistema;
import usuario.Pasajero;
import usuario.Usuario;

public class TestSistema extends TestCase{
	
	Sistema miSistema;
	Usuario miUsuario;
	
	public void setUp(){
		
		miSistema = new Sistema();
		miUsuario = new Pasajero( "tito@gmail.com","1234" );
		
		miSistema.agregarUsuario( miUsuario );
			
	}
	
	public void testLogIn(){
		//Aca testeo que me devuelva el usuario que se loguea
		assertEquals( miUsuario, miSistema.logIn( "tito@gmail.com","1234" ));
	}

}
