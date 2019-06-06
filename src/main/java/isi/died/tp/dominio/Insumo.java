package isi.died.tp.dominio;

public class Insumo implements Comparable<Insumo> {
	protected int id, stock;
	protected String descripcion;
	protected Medida unidadDeMedida;
	protected boolean esRefrigerado;
	protected float costo, peso;
	
	public Insumo() {
	}

	public Insumo(int id, int stock, String descripcion, Medida unidadDeMedida, boolean esRefrigerado, float costo, float peso) {
		this.id = id;
		this.stock = stock;
		this.descripcion = descripcion;
		this.unidadDeMedida = unidadDeMedida;
		this.esRefrigerado = esRefrigerado;
		this.costo = costo;
		this.peso = peso;
	}
	
	public void setStock(int s) {
		this.stock = s;
	}
	
	public int getStock() {
		return this.stock;
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

	@Override
	public int compareTo(Insumo i) {
		return this.getStock() - i.getStock();
	}
	
	public float calcularPeso() {
		return stock*peso;
	}

	
	
}
