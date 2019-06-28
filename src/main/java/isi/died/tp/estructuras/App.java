package isi.died.tp.estructuras;

import java.util.*;

import isi.died.tp.dominio.*;

public class App {

	public static void main(String[] args) {
		
		/*Insumo i1 = new Insumo(0,1,null,Medida.KILO,false,0,0);
		Insumo i2 = new Insumo(0,2,null,Medida.KILO,false,0,0);
		Insumo i3 = new Insumo(0,3,null,Medida.KILO,false,0,0);
		Insumo i4 = new Insumo(0,4,null,Medida.KILO,false,0,0);
		Insumo i5 = new Insumo(0,5,null,Medida.KILO,false,0,0);
		Insumo i6 = new Insumo(0,6,null,Medida.KILO,false,0,0);
		
		//Insumo inicial = new Insumo(0,2,null,Medida.KILO,false,0,0);
		//Insumo fin = new Insumo(0,4,null,Medida.KILO,false,0,0);
	
		ArbolBinarioBusqueda<Insumo> arbol1 = new ArbolBinarioBusqueda<Insumo>(i1);
		arbol1.agregar(i2);
		arbol1.agregar(i3);
		arbol1.agregar(i4);
		arbol1.agregar(i5);
		arbol1.agregar(i6);
		
		ArrayList<Insumo> lista = (ArrayList<Insumo>) arbol1.rango(2,4);
		for(Insumo i :lista) {
			System.out.println(i.getStock());
		}*/
		
		Insumo i1 = new Insumo(0,1,null,Medida.KILO,false,25,0);
		Insumo i2 = new Insumo(0,2,null,Medida.KILO,false,13,0);
		Insumo i3 = new Insumo(0,3,null,Medida.KILO,false,2,0);
		Insumo i4 = new Insumo(0,4,null,Medida.KILO,false,1,0);
		
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
		listaStock.add(s1);
		listaStock.add(s2);
		listaStock.add(s3);
		listaStock.add(s4);
		
		Planta pl = new Planta();
		pl.setStock(listaStock);
		
		System.out.println(pl.costoTotal());
		System.out.println(pl.stockEntre(32, 41).toString());
		System.out.println(pl.necesitaInsumo(i4));
		
	}

}
