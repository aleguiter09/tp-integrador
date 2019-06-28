package isi.died.tp.estructuras;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import died.guia07.ejercicio03.Vertice;
import isi.died.tp.dominio.Insumo;
import isi.died.tp.dominio.Planta;
public class GrafoPlanta extends Grafo<Planta> {

public void imprimirDistanciaAdyacentes(Planta inicial) {
 List<Planta> adyacentes = super.getAdyacentes(inicial);
 	for(Planta unAdyacente: adyacentes) {
 			Arista<Planta> camino = super.buscarArista(inicial, unAdyacente);
 			System.out.println("camino de "+inicial.getNombre()+" a "+
 			unAdyacente.getNombre()+ " tiene valor de "+ camino.getValor() );
 		}
 	}
 // a
 public Planta buscarPlanta(Planta inicial, Insumo i, Integer saltos) {
	Vertice<Planta> inicio = new Vertice<Planta>(inicial);
	Stack<Vertice<Planta>> visitar = new Stack<Vertice<Planta>>();
	HashSet<Vertice<Planta>> visitados = new HashSet<Vertice<Planta>>();
	visitar.push(inicio);
	int n = 0;
		
		while(!visitar.empty()) {
			n++;
			Vertice<Planta> vInicio = visitar.pop();
			for(Vertice<Planta> unAdya : this.getAdyacentes(vInicio)) {
				if(n <= saltos && unAdya.getValor().necesitaInsumo(i)) return unAdya.getValor();
				if(!visitados.contains(unAdya)) {
					visitar.push(unAdya);
					visitados.add(unAdya);
				}
			}
		}
		return null;
 }
 // b
 public Planta buscarPlanta(Planta inicial, Insumo i) {}
 // c
 public Planta buscarPlanta(Insumo i) {}

}
