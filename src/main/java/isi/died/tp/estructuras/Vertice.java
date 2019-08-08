package isi.died.tp.estructuras;

public class Vertice<T> implements Comparable<Vertice<T>> {

	private T valor;
	private int PageRank;
	
	public Vertice(){}
	 
	public int getPageRank() {
		return this.PageRank;
	}
	
	public void setPageRank(int pr) {
		this.PageRank = pr;
	}
	
	public Vertice(T v){
		this.valor = v;
	}
	
	public void setValor(T v){
		this.valor = v;
	}
	
	public T getValor(){
		return this.valor;
	}
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((valor == null) ? 0 : valor.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vertice other = (Vertice) obj;
		if (valor == null) {
			if (other.valor != null)
				return false;
		} else if (!valor.equals(other.valor))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return valor.toString();
	}

	@Override
	public int compareTo(Vertice<T> o) {
		return this.PageRank - o.getPageRank();
	}
	
	
}