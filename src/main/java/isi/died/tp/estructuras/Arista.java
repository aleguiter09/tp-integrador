package isi.died.tp.estructuras;

public class Arista<T> {
	private Vertice<T> inicio;
	private Vertice<T> fin;
	private Number valor;

	private float distancia, duracion, pesoCamion;
	
	public Arista(Vertice<T> inicio, Vertice<T> fin, Number valor) {
		this.inicio = inicio;
		this.fin = fin;
		this.valor = valor;
	}
	
	public Arista(Vertice<T> inicio, Vertice<T> fin, Number valor, float distancia, float duracion, float pesoCamion) {
		super();
		this.inicio = inicio;
		this.fin = fin;
		this.valor = 1.0;
		this.distancia = distancia;
		this.duracion = duracion;
		this.pesoCamion = pesoCamion;
	}
	
	public Arista(Vertice<T> ini,Vertice<T> fin){
		this.inicio = ini;
		this.fin = fin;
	}
	
	public Number getValor() {
		return valor;
	}

	public void setValor(Number valor) {
		this.valor = valor;
	}
	
	public Vertice<T> getInicio() {
		return inicio;
	}
	
	public void setInicio(Vertice<T> inicio) {
		this.inicio = inicio;
	}
	
	public Vertice<T> getFin() {
		return fin;
	}
	
	public void setFin(Vertice<T> fin) {
		this.fin = fin;
	}	
	
	public float getDistancia() {
		return distancia;
	}
	public void setDistancia(float distancia) {
		this.distancia = distancia;
	}
	public float getDuracion() {
		return duracion;
	}
	public void setDuracion(float duracion) {
		this.duracion = duracion;
	}
	public float getPesoCamion() {
		return pesoCamion;
	}
	public void setPesoCamion(float pesoCamion) {
		this.pesoCamion = pesoCamion;
	}
	@Override
	public String toString() {
		return "( "+this.inicio.getValor()+" --> "+this.fin.getValor()+" )";
	}
	
	@Override
	public boolean equals(Object obj) {
	 if( ((obj instanceof Arista<?>) && ((Arista<?>)obj).getInicio().equals(this.inicio)) && 
		 (((Arista<?>)obj).getFin().equals(this.fin)) &&
		 (((Arista<?>)obj).getDistancia() == (this.distancia)) &&
		 (((Arista<?>)obj).getDuracion() == (this.duracion)) &&
		 (((Arista<?>)obj).getPesoCamion() == (this.pesoCamion))) return true;
	 else return false;
	}
}