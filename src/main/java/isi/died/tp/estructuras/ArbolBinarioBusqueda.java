package isi.died.tp.estructuras;

import java.util.ArrayList;
import java.util.List;

public class ArbolBinarioBusqueda<E extends Comparable<E>> extends Arbol<E> {

	protected Arbol<E> izquierdo;
	protected Arbol<E> derecho;
	
	public ArbolBinarioBusqueda(){
		this.valor=null;
		this.izquierdo=new ArbolVacio<E>();
		this.derecho=new ArbolVacio<E>();
	}
	
	public ArbolBinarioBusqueda(E e){
		this.valor=e;
		this.izquierdo=new ArbolVacio<E>();
		this.derecho=new ArbolVacio<E>();
	}
	
	public ArbolBinarioBusqueda(E e,Arbol<E> i,Arbol<E> d){
		this.valor=e;
		this.izquierdo=i;
		this.derecho=d;
	}
	
	@Override
	public List<E> preOrden() {
		List<E> lista = new ArrayList<E>();
		lista.add(this.valor);
		lista.addAll(this.izquierdo.preOrden());
		lista.addAll(this.derecho.preOrden());
		return lista;
	}
	@Override
	public List<E> inOrden() {
		List<E> lista = new ArrayList<E>();
		lista.addAll(this.izquierdo.preOrden());
		lista.add(this.valor);
		lista.addAll(this.derecho.preOrden());
		return lista;
	}
	@Override
	public List<E> posOrden() {
		List<E> lista = new ArrayList<E>();
		lista.addAll(this.izquierdo.preOrden());
		lista.addAll(this.derecho.preOrden());
		lista.add(this.valor);
		return lista;

	}
	@Override
	public boolean esVacio() {
		return false;
	}
        
	@Override
	public E valor() {
		return this.valor;
	}
	
	@Override
	public Arbol<E> izquierdo() {
		return this.izquierdo;
	}
	
	@Override
	public Arbol<E> derecho() {
		return this.derecho;
	}


	@Override
	public void agregar(E a) {
		if(this.valor.compareTo(a)<1) {
			if (this.derecho.esVacio()) this.derecho = new ArbolBinarioBusqueda<E>(a);
			else this.derecho.agregar(a);
		}else {
			if (this.izquierdo.esVacio()) this.izquierdo= new ArbolBinarioBusqueda<E>(a);
			else this.izquierdo.agregar(a);
		}
	}
	
	@Override
	public boolean equals(Arbol<E> unArbol) {
		return this.valor.equals(unArbol.valor()) && this.izquierdo.equals(unArbol.izquierdo()) && this.derecho.equals(unArbol.derecho());
	}

	@Override
	public boolean contiene(E unValor) {
		// TODO LISTO 1.a
		return this.valor.equals(unValor) || this.izquierdo.contiene(unValor) || this.derecho.contiene(unValor);
	}

	@Override
	public int profundidad() {
		// TODO LISTO 1.b
		if(this.izquierdo.esVacio() && this.derecho.esVacio()) 
			return 0;
		else
			return 1+Math.max(izquierdo.profundidad(), derecho.profundidad());
	}

	@Override
	public int cuentaNodosDeNivel(int nivel) {
		// TODO LISTO 1.c
		if(this.valor == null) return 0;
		int cantIz= izquierdo.cuentaNodosDeNivel(nivel-1); 
		int cantDe= derecho.cuentaNodosDeNivel(nivel-1);
		
		if(nivel == 0) {
			return cantIz + cantDe + 1;
		}
		return cantIz+cantDe;
	}

	@Override
	public boolean esCompleto() {
		// TODO LISTO 1.d
		if(this.esLleno())
			return true;
		
		else if(this.izquierdo.profundidad() - this.derecho.profundidad() > 1)
			return false;
		
		else if(this.izquierdo.profundidad() - this.derecho.profundidad() <= 1) 
			return this.izquierdo.esLleno();
		
		else return false;
	}

	@Override
	public boolean esLleno() {
		// TODO LISTO 1.e
		if(this.izquierdo.esVacio() && this.derecho.esVacio()) 
			return true;
		else if(this.izquierdo.esLleno() && this.derecho.esLleno() && this.izquierdo.profundidad() == this.derecho.profundidad())
			return true;
		else
			return false;
	}

}
