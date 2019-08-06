package isi.died.tp.estructuras;

/*import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
*/
import isi.died.tp.dominio.Insumo;
//import isi.died.tp.dominio.Medida;
import isi.died.tp.dominio.Planta;
//import isi.died.tp.dominio.Stock;

public class PlantaTest {
	Planta pl;
	Insumo i1,i2,i3,i4;
/*	
	@Before
	public void preTest() {
		i1 = new Insumo(0,1,null,Medida.KILO,false,25,0);
		i2 = new Insumo(0,2,null,Medida.KILO,false,13,0);
		i3 = new Insumo(0,3,null,Medida.KILO,false,2,0);
		i4 = new Insumo(0,4,null,Medida.KILO,false,1,0);
		
		Stock s1 = new Stock();
		s1.setCantidad(13);
		s1.setInsumo(i1);
		s1.setPuntoPedido(12);
		Stock s2 = new Stock();
		s2.setCantidad(25);
		s2.setInsumo(i2);
		s2.setPuntoPedido(34);
		Stock s3 = new Stock();
		s3.setCantidad(42);
		s3.setInsumo(i3);
		s3.setPuntoPedido(30);
		Stock s4 = new Stock();
		s4.setCantidad(32);
		s4.setInsumo(i4);
		s4.setPuntoPedido(51);
		
		List<Stock> listaStock = new ArrayList<Stock>();
		pl = new Planta();
		listaStock.add(s1);
		listaStock.add(s2);
		listaStock.add(s3);
		listaStock.add(s4);	
		pl.setStock(listaStock);

	}
	
	@Test
	public void testCostoTotal() {
		Double esperado = 766.0;
		assertEquals(esperado, pl.costoTotal());
	}
	@Test
	public void testStockEntre() {
		List<Insumo> esperado = new ArrayList<Insumo>();
		esperado.add(i3);
		esperado.add(i4);
		assertEquals(esperado, pl.stockEntre(32, 42));
	}
	@Test
	public void testNecesitaInsumo() {
		assertTrue(pl.necesitaInsumo(i4));
	}*/
 }
