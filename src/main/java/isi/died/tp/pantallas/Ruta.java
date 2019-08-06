package isi.died.tp.pantallas;

import isi.died.tp.dominio.*;
import isi.died.tp.estructuras.*;

public class Ruta {
	private VerticeGrafo inicio, fin;
	private Arista<Planta> arista;
	
	public Ruta(VerticeGrafo inicio, VerticeGrafo fin) {
		this.inicio = inicio;
		this.fin = fin;
	}
	
	public void setArista(Arista<Planta> p) {
		arista = p;
	}
	
	public Arista<Planta> getArista() {
		return arista;
	}
	
	public VerticeGrafo getInicio() {
		return inicio;
	}
	public void setInicio(VerticeGrafo inicio) {
		this.inicio = inicio;
	}
	public VerticeGrafo getFin() {
		return fin;
	}
	public void setFin(VerticeGrafo fin) {
		this.fin = fin;
	}
	
	

}
