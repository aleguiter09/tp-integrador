package isi.died.tp.pantallas;

import isi.died.tp.dominio.*;
import isi.died.tp.estructuras.*;

public class Ruta {
	private VerticeGrafo inicio, fin;
	private Arista<Planta> arista;
	private boolean esResultado = false;
	private boolean esFlujo = false;
	
	public boolean isEsFlujo() {
		return esFlujo;
	}

	public void setEsFlujo(boolean esFlujo) {
		this.esFlujo = esFlujo;
		this.esResultado = !esFlujo;
	}

	public boolean isEsResultado() {
		return esResultado;
	}

	public void setEsResultado(boolean esResultado) {
		this.esResultado = esResultado;
		this.esFlujo = !esResultado;
	}

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
