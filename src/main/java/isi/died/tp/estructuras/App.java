package isi.died.tp.estructuras;

import java.util.*;

import isi.died.tp.dominio.*;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Insumo i1 = new Insumo(0,1,null,Medida.KILO,false,0,0);
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
		}
	}

}
