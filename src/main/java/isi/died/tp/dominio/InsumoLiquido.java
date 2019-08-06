package isi.died.tp.dominio;

public class InsumoLiquido extends Insumo {
	private float densidad;
	
	public InsumoLiquido() {
		super();
		this.densidad = 0;
	}
	public InsumoLiquido(int id, String nombre, String descripcion, Medida unidadDeMedida, boolean esRefrigerado, float costo, float peso, float densidad) {
		super (id, nombre, descripcion, unidadDeMedida, esRefrigerado, costo, peso);
		this.densidad = densidad;
	}
	public float getDensidad() {
		return densidad;
	}
	public void setDensidad(float densidad) {
		this.densidad = densidad;
	}
	public float calcularPeso() {
		return peso*densidad;
	}
}
