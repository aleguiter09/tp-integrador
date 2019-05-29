package isi.died.tp.estructuras;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArbolBinarioBusqueda<Integer> arbol1 = new ArbolBinarioBusqueda<Integer>(6);
		ArbolBinarioBusqueda<Integer> arbol2 = new ArbolBinarioBusqueda<Integer>(5); 
		arbol1.agregar(5);
		arbol1.agregar(9);
		arbol1.agregar(2);
		arbol1.agregar(7);
		arbol1.agregar(4);
		
		System.out.println(arbol1.contiene(6));
		System.out.println(arbol1.profundidad());
		
	}

}
