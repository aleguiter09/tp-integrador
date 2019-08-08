package isi.died.tp.dominio;

import java.util.*;

public class Insumo implements Comparable<Insumo> {
	protected Integer id;
	protected String descripcion, nombre;
	protected Medida unidadDeMedida;
	protected boolean esRefrigerado;
	protected float costo, peso;
	protected List<Stock> stocks;
	
	public Insumo() {}
	
	public Insumo(int id, String nombre, String descripcion, Medida unidadDeMedida, boolean esRefrigerado, float costo, float peso) {
		this.nombre = nombre;
		this.id = id;
		this.descripcion = descripcion;
		this.unidadDeMedida = unidadDeMedida;
		this.esRefrigerado = esRefrigerado;
		this.costo = costo;
		this.peso = peso;
		stocks = new ArrayList<Stock>();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Medida getUnidadDeMedida() {
		return unidadDeMedida;
	}
	public void setUnidadDeMedida(Medida unidadDeMedida) {
		this.unidadDeMedida = unidadDeMedida;
	}
	public boolean isEsRefrigerado() {
		return esRefrigerado;
	}
	public void setEsRefrigerado(boolean esRefrigerado) {
		this.esRefrigerado = esRefrigerado;
	}
	public float getCosto() {
		return costo;
	}
	public void setCosto(float costo) {
		this.costo = costo;
	}
	public float getPeso() {
		return peso;
	}
	public void setPeso(float peso) {
		this.peso = peso;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void agregarStock(Stock s) {
		stocks.add(s);
	}
	
	public int calcularTotal() {
		int contador=0;
		for(int i=0; i<stocks.size(); i++) {
			contador += stocks.get(i).getCantidad();
		}
		return contador;
	}
	
	@Override
	public int compareTo(Insumo i) {
		return this.id- i.getId();
	}
	
	public boolean equals(Object i) {
		Insumo aux = (Insumo) i;
		if(this.id == aux.getId())
			return true;
		else return false;
	}
	
}
