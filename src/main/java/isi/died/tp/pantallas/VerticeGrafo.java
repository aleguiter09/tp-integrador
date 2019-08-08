package isi.died.tp.pantallas;

import isi.died.tp.dominio.*;
import isi.died.tp.estructuras.*;
public class VerticeGrafo {
	
	private int x,y;
	private Vertice<Planta> vertice;
	private boolean seleccionado;
	private boolean necesitaInsumo = false;
	
	public boolean getSeleccionado() {
		return seleccionado;
	}
	
	public void setSeleccionado(boolean b) {
		seleccionado = b;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}
	
	public Vertice<Planta> getVertice() {
		return vertice;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public void setVertice(Vertice<Planta> p) {
		this.vertice = p;
	}
	
	public boolean isNecesitaInsumo() {
		return necesitaInsumo;
	}

	public void setNecesitaInsumo(boolean necesitaInsumo) {
		this.necesitaInsumo = necesitaInsumo;
	}

	public VerticeGrafo() {
		x= (int) Math.floor(Math.random()*(601));
		y= (int) Math.floor(Math.random()*(501));
		seleccionado = false;
	}
	
	public VerticeGrafo(Vertice<Planta> vertice) {
		super();
		x= (int) Math.floor(Math.random()*(601));
		y= (int) Math.floor(Math.random()*(501));
		seleccionado = false;
		this.vertice = vertice;
	}
	
	
}
