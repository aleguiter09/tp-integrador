package isi.died.tp.dominio;
import java.util.*;
import java.util.stream.*;
public class Planta {
	
	private Integer id;
	private String nombre;
	private List<Stock> stock;
	
	public Planta(Integer id_aux,String nom) {
		id=id_aux;
		nombre=nom;
		stock = new ArrayList<Stock>();	
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public List<Stock> getStock() {
		return stock;
	}
	public void setStock(List<Stock> stock) {
		this.stock = stock;
	}
	public Double costoTotal() {
		return stock.stream().mapToDouble((s)->s.getInsumo().getCosto()*s.getCantidad()).sum();
	}
	public List<Insumo> stockEntre(Integer v1, Integer v2) {
		return stock.stream().filter((s)->s.getCantidad() <= v2 && s.getCantidad() >= v1).map((s)->s.getInsumo()).collect(Collectors.toList());
	}
	public Boolean necesitaInsumo(Insumo i) {
		Stock st = new Stock(1,1,1,i);  //VER ESTO
		st = stock.stream().filter((s)->s.getInsumo().equals(i)).collect(Collectors.toList()).get(0);
		if(st.getCantidad() > st.getPuntoPedido())
			return false;
		else return true;
	}
	public Integer cantNecesaria(Insumo i) {
		Stock st = new Stock(1,1,1,i);  //VER ESTO
		st = stock.stream().filter((s)->s.getInsumo().equals(i)).collect(Collectors.toList()).get(0);
		if(st.getCantidad() > st.getPuntoPedido())
			return 0;
		else return st.getPuntoPedido()-st.getCantidad();
	}
}
