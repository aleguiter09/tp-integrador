package isi.died.tp.dominio;

public class Camion {
	
	private int id, anio;
	private boolean aptoLiquidos;
	private String marca, modelo, patente;
	private float costoPorKM, capacidad;
	
	public Camion(int id, float capacidad, int anio, boolean aptoLiquidos, String marca, String modelo,
			String patente, float costoPorKM) {
		super();
		this.id = id;
		this.capacidad = capacidad;
		this.anio = anio;
		this.aptoLiquidos = aptoLiquidos;
		this.marca = marca;
		this.modelo = modelo;
		this.patente = patente;
		this.costoPorKM = costoPorKM;
	} 
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Float getCapacidad() {
		return capacidad;
	}
	public void setCapacidad(Float capacidad) {
		this.capacidad = capacidad;
	}
	public Integer getAnio() {
		return anio;
	}
	public void setAnio(Integer anio) {
		this.anio = anio;
	}
	public boolean isAptoLiquidos() {
		return aptoLiquidos;
	}
	public void setAptoLiquidos(boolean aptoLiquidos) {
		this.aptoLiquidos = aptoLiquidos;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public String getPatente() {
		return patente;
	}
	public void setDominio(String patente) {
		this.patente = patente;
	}
	public Float getCostoPorKM() {
		return costoPorKM;
	}
	public void setCostoPorKM(Float costoPorKM) {
		this.costoPorKM = costoPorKM;
	}
	
	
}
