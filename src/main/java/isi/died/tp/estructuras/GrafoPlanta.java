package isi.died.tp.estructuras;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Stack;

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
	if(inicio.getValor().necesitaInsumo(i)) return inicio.getValor();
	else { 
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
 }
 // b
 public Planta buscarPlanta(Planta inicial, Insumo i) {
	 Map<Planta, Integer> caminos = this.caminosMinimoDikstra(inicial);
	 HashSet<Planta> aux = (HashSet<Planta>) caminos.keySet(); 
	 List<Integer> cam = new ArrayList<Integer>();
	 for(Planta p : aux) {
		 if(p.necesitaInsumo(i))
			 cam.add(caminos.get(p));
	 }
	 int minimo = cam.get(0);
	 for (int x = 0;  x< 100; x++){
			if (minimo > cam.get(x))
				minimo = cam.get(x);
		}
	 for(Planta p : aux) {
		 if(caminos.get(p) == minimo)
				 return p;
	 }
	 return null;
 }
 // c
 public Planta buscarPlanta(Insumo i) {
	 Planta inicial = this.vertices().get(0).getValor();
	 Map<Planta, Integer> caminos = this.caminosMinimoDikstra(inicial);
	 HashSet<Planta> aux = (HashSet<Planta>) caminos.keySet(); 
	 List<Integer> cam = new ArrayList<Integer>();
	 for(Planta p : aux) {
		 if(p.necesitaInsumo(i))
			 cam.add(p.cantNecesaria(i));
	 }
	 int minimo = cam.get(0);
	 for (int x = 0;  x< 100; x++){
			if (minimo > cam.get(x))
				minimo = cam.get(x);
		}
	 for(Planta p : aux) {
		 if(p.cantNecesaria(i) == minimo)
				 return p;
	 }
	 return null;
 }

}
