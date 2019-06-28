package isi.died.tp.dominio;
import java.util.*;
import java.util.stream.*;
public class Planta {
	
	private Integer id;
	private String nombre;
	private List<Stock> stock;
	
	
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
		Stock st = new Stock();
		st = stock.stream().filter((s)->s.getInsumo().equals(i)).collect(Collectors.toList()).get(0);
		if(st.getCantidad() > st.getPuntoPedido())
			return false;
		else return true;
	}
}
