package isi.died.tp.estructuras;

import static org.junit.Assert.*;

import org.junit.*;

public class ArbolBinarioBusquedaTest {
	private ArbolBinarioBusqueda<Integer> arbol1;
	private ArbolBinarioBusqueda<Integer> arbol2; 
	
	@Before
	public void preTest() {
		arbol1 = new ArbolBinarioBusqueda<Integer>(6);
		arbol2 = new ArbolBinarioBusqueda<Integer>(5); 
		arbol1.agregar(5);
		arbol1.agregar(9);
		arbol1.agregar(2);
		arbol1.agregar(7);
		arbol1.agregar(4);
	}
	@Test
	public void testContiene() {
		Boolean esperado = true;
		Boolean obtenido = arbol1.contiene(6);
		assertEquals(esperado, obtenido);
	}

	@Test
	public void testEqualsArbolOfE() {
		fail("Not yet implemented");
	}

	@Test
	public void testAgregar() {
		Integer esperado = arbol1.cuentaNodosDeNivel(arbol1.profundidad()) + 1;
		arbol1.agregar(23);
		Integer obtenido = arbol1.cuentaNodosDeNivel(arbol1.profundidad());
		assertEquals(esperado, obtenido);
		//fail("Not yet implemented");
	}

	@Test
	public void testProfundidad() {
		Integer esperado = 2;
		Integer obtenido = arbol1.profundidad();
		assertEquals(esperado, obtenido);
	}

	@Test
	public void testCuentaNodosDeNivel() {
		Integer esperado = 3;
		Integer obtenido = arbol1.cuentaNodosDeNivel(1);
		assertEquals(esperado, obtenido);
	}

	@Test
	public void testEsCompleto() {
		Boolean esperado = true;
		Boolean obtenido = arbol1.esCompleto();
		assertEquals(esperado, obtenido);
	}

	@Test
	public void testEsLleno() {
		Boolean esperado = false;
		Boolean obtenido = arbol1.esLleno();
		assertEquals(esperado, obtenido);
	}

}
