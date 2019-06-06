package isi.died.tp.estructuras;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.*;

import isi.died.tp.dominio.Insumo;
import isi.died.tp.dominio.Medida;

public class TestRangoABB {
		private Insumo i1, i2, i3, i4, i5, i6;
		private ArbolBinarioBusqueda<Insumo> arbol1;
		
	@Before	
	public void preTest() {
		i1 = new Insumo(0,1,null,Medida.KILO,false,0,0);
		i2 = new Insumo(0,2,null,Medida.KILO,false,0,0);
		i3 = new Insumo(0,3,null,Medida.KILO,false,0,0);
		i4 = new Insumo(0,4,null,Medida.KILO,false,0,0);
		i5 = new Insumo(0,5,null,Medida.KILO,false,0,0);
		i6 = new Insumo(0,6,null,Medida.KILO,false,0,0);
		
		inicial = new Insumo(0,2,null,Medida.KILO,false,0,0);
		fin = new Insumo(0,4,null,Medida.KILO,false,0,0);

		arbol1 = new ArbolBinarioBusqueda<Insumo>(i1);
		arbol1.agregar(i2);
		arbol1.agregar(i3);
		arbol1.agregar(i4);
		arbol1.agregar(i5);
		arbol1.agregar(i6);		
	}
	
	@Test
	public void testRango() {
		ArrayList<Insumo> esperado = new ArrayList<Insumo>();
		esperado.add(i2);
		esperado.add(i3);
		esperado.add(i4);
		ArrayList<Insumo> resultado = (ArrayList<Insumo>) arbol1.rango(2,4);
		assertEquals(esperado,resultado);
	}

}
