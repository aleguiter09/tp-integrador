package isi.died.tp.pantallas;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.*;
import java.util.List;

import javax.swing.*;

import isi.died.tp.dominio.*;
import isi.died.tp.estructuras.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VerRutas extends JPanel implements MouseListener, MouseMotionListener {

	private static final long serialVersionUID = 1L;
	private static final int  ancho=950, alto=571;
	
	private ArrayList<VerticeGrafo> vertices;
	private ArrayList<Ruta> rutas;
	private String[] nombresInsumos;
	private Insumo elegido = null;
	private Principal prin;
	private ArrayList<Vertice<Planta>> resultado;
	private ArrayList<Vertice<Planta>> flujo;
	private JTextField camionPeso;
	private float pesoFlujoMax;

	public VerRutas(Principal principal) {
		setBackground(new Color(139, 69, 19));
		addMouseListener(this);
		addMouseMotionListener(this);
		setBounds(350, 0, ancho, alto);
		setLayout(null);
		
		vertices = new ArrayList<VerticeGrafo>();
		rutas = new ArrayList<Ruta>();
		resultado = new ArrayList<Vertice<Planta>>();
		flujo = new ArrayList<Vertice<Planta>>();
		prin=principal;
		
		for(Vertice<Planta> p: principal.grafo.vertices()) 
			vertices.add(new VerticeGrafo(p));
		
		ArrayList<Insumo> insumos = principal.arbol.inOrden();
		nombresInsumos = new String[insumos.size()];
		nombresInsumos[0]="<Seleccionar>";
		for(int i=1; i<insumos.size(); i++) nombresInsumos[i]= insumos.get(i).getNombre();
		
		
		
		JLabel lblInsumos = new JLabel("Insumos");
		lblInsumos.setForeground(Color.WHITE);
		lblInsumos.setFont(new Font("Dialog", Font.BOLD, 25));
		lblInsumos.setBounds(713, 29, 120, 27);
		add(lblInsumos);
		
		Checkbox checkbox = new Checkbox("Calcular Flujo Maximo");
		checkbox.setForeground(Color.WHITE);
		checkbox.setFont(new Font("Dialog", Font.BOLD, 12));
		checkbox.setBounds(675, 150, 184, 22);
		add(checkbox);
		
		camionPeso = new JTextField();
		camionPeso.setBounds(675, 194, 147, 22);
		add(camionPeso);
		camionPeso.setColumns(10);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(nombresInsumos));
		comboBox.setBounds(675, 69, 184, 30);
		add(comboBox);
		
		
		Button refrescar = new Button("Refrescar");
		refrescar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VerticeGrafo fi = null;
				VerticeGrafo in = null;
				vertices.clear();
				rutas.clear();
				resultado.clear();
				flujo.clear();
				//Agrego vertices
				for(int i=0; i<principal.grafo.vertices().size(); i++) {
					VerticeGrafo v = new VerticeGrafo();
					v.setVertice(principal.grafo.vertices().get(i));
					vertices.add(v);
				}
				//Agrego rutas
				for(int i=0; i<principal.grafo.getAristas().size(); i++) {
					for(int j=0; j<vertices.size(); j++) {
						if(vertices.get(j).getVertice().equals(principal.grafo.getAristas().get(i).getInicio()))
							in = vertices.get(j);
						else if(vertices.get(j).getVertice().equals(principal.grafo.getAristas().get(i).getFin()))
							fi = vertices.get(j);
					}
					Ruta r = new Ruta(in, fi);
					r.setArista(principal.grafo.getAristas().get(i));
					rutas.add(r);
				}
				//ComboBox
				ArrayList<Insumo> insumos = principal.arbol.inOrden();
				nombresInsumos = new String[insumos.size()];
				nombresInsumos[0]="<Seleccionar>";
				for(int i=1; i<insumos.size(); i++) nombresInsumos[i]= insumos.get(i).getNombre();
				//Obtengo elegido
				elegido = insumos.get(comboBox.getSelectedIndex());
				//PintoVertices
				for(VerticeGrafo v: vertices)  if(v.getVertice().getValor().necesitaInsumo(elegido)) v.setNecesitaInsumo(true);	
				//Caminos de acopioInicial a acopioFinal	
				Planta acopioInicial = null;
				Planta acopioFinal = null;
				for(Vertice<Planta> p: principal.grafo.vertices()) {
					if(p.getValor().getId() == -1) acopioInicial = p.getValor();
					if(p.getValor().getId() == -2) acopioFinal = p.getValor();
				}
					
				List<List<Vertice<Planta>>> caminos = principal.grafo.caminos(acopioInicial, acopioFinal);
				//Obtengo los caminos que cubren los que necesitan insumo
				ArrayList<Vertice<Planta>> aux = new ArrayList<Vertice<Planta>>();
				for(int i=0; i<principal.grafo.vertices().size(); i++) {
					if(principal.grafo.vertices().get(i).getValor().necesitaInsumo(elegido))
						aux.add(principal.grafo.vertices().get(i));
				}
				List<List<Vertice<Planta>>> aux1 = new ArrayList<List<Vertice<Planta>>>();
				for(List<Vertice<Planta>> lista: caminos) {
					if(lista.containsAll(aux)) aux1.add(lista);
				}
				//Obtengo resultado y flujo

				
					
				if(checkbox.getState()) {
					flujo = flujoMaximo(caminos);
					for(int i=0; i<rutas.size(); i++) rutas.get(i).setEsResultado(false);
					for(int i=0; i<rutas.size(); i++) {
						if(flujo.contains(rutas.get(i).getArista().getInicio()) && flujo.contains(rutas.get(i).getArista().getFin()))
							rutas.get(i).setEsFlujo(true);
						else rutas.get(i).setEsFlujo(false);
					}
					camionPeso.setText(pesoFlujoMax+" KG");
				}
				else if(comboBox!=null && comboBox.getSelectedIndex()!=0 && !checkbox.getState()) {
					resultado = mejorRuta(aux1);
					camionPeso.setText(null);
					for(int i=0; i<rutas.size(); i++) rutas.get(i).setEsFlujo(false);
					for(int i=0; i<rutas.size(); i++) {
						if(resultado.contains(rutas.get(i).getArista().getInicio()) && resultado.contains(rutas.get(i).getArista().getFin()))
							rutas.get(i).setEsResultado(true);
						else rutas.get(i).setEsResultado(false);
					}
				}
		comboBox.setModel(new DefaultComboBoxModel<String>(nombresInsumos));
		repaint();
				
	}
});

		refrescar.setBounds(535, 512, 70, 22);
		add(refrescar);
		
		
		repaint();	
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);

		for(int i=0; i<rutas.size(); i++) {
			if(rutas.get(i).isEsResultado()) g.setColor(new Color(50, 205, 50));
			else if(rutas.get(i).isEsFlujo()) g.setColor(Color.BLUE);
			else g.setColor(Color.white);
			g.drawLine(rutas.get(i).getInicio().getX()+20, rutas.get(i).getInicio().getY()+20, rutas.get(i).getFin().getX()+20, rutas.get(i).getFin().getY()+20);
			g.drawString("Distancia: "+rutas.get(i).getArista().getDistancia(),(rutas.get(i).getInicio().getX()+rutas.get(i).getFin().getX())/2 , (rutas.get(i).getInicio().getY()+rutas.get(i).getFin().getY())/2);
			g.drawString("Duracion: "+rutas.get(i).getArista().getDuracion(),(rutas.get(i).getInicio().getX()+rutas.get(i).getFin().getX())/2 , ((rutas.get(i).getInicio().getY()+rutas.get(i).getFin().getY())/2)-12);
			g.drawString("Peso Camion: "+rutas.get(i).getArista().getPesoCamion(),(rutas.get(i).getInicio().getX()+rutas.get(i).getFin().getX())/2 , ((rutas.get(i).getInicio().getY()+rutas.get(i).getFin().getY())/2)-24);
		}
		for(int i=0; i<vertices.size();i++) {
			if(vertices.get(i).isNecesitaInsumo()) g.setColor(new Color(50, 205, 50));
			else g.setColor(Color.white);
			g.fillOval(vertices.get(i).getX(),vertices.get(i).getY(), 40, 40);
			if(vertices.get(i).getVertice().getValor().getId()==-1 || vertices.get(i).getVertice().getValor().getId()== -2) {
				g.drawString(vertices.get(i).getVertice().getValor().getNombre() , vertices.get(i).getX()-20 , vertices.get(i).getY()+55);
			}
			else g.drawString("Planta "+vertices.get(i).getVertice().getValor().getNombre()+" - ID: "+vertices.get(i).getVertice().getValor().getId() , vertices.get(i).getX()-20 , vertices.get(i).getY()+55);
		}
		g.setColor(Color.white);
		g.drawLine(650, 0, 650, 571);
		g.drawLine(651, 0, 651, 571);
		g.drawLine(652, 0, 652, 571);
	}
	
	public ArrayList<Vertice<Planta>> mejorRuta(List<List<Vertice<Planta>>> aux1) {
		ArrayList<Vertice<Planta>> resultado = new ArrayList<Vertice<Planta>>();
		ArrayList<Arista<Planta>> aristas = new ArrayList<>();
		float menor = Float.MAX_VALUE;
		
		for(int i=0;i<aux1.size();i++) {
			aristas.clear();
			float dist = 0;
			for(int j=0;j<aux1.get(i).size() - 1;j++) 
			aristas.add(prin.grafo.buscarArista(aux1.get(i).get(j), aux1.get(i).get(j+1)));
			for(Arista<Planta> a: aristas) {
				dist+=a.getDistancia();
			}
			if(dist < menor) {
				menor = dist;
				resultado = (ArrayList<Vertice<Planta>>) aux1.get(i);
			}
		}
		return resultado;
	}
	
	public ArrayList<Vertice<Planta>> flujoMaximo(List<List<Vertice<Planta>>> aux1) {
		ArrayList<Vertice<Planta>> resultado = new ArrayList<Vertice<Planta>>();
		ArrayList<Arista<Planta>> aristas = new ArrayList<>();
		float menor = Float.MAX_VALUE;
		
		for(int i=0;i<aux1.size();i++) {
			aristas.clear();
			float peso = 0;
			for(int j=0;j<aux1.get(i).size() - 1;j++) 
			aristas.add(prin.grafo.buscarArista(aux1.get(i).get(j), aux1.get(i).get(j+1)));
			for(Arista<Planta> a: aristas) {
				peso+=a.getPesoCamion();
			}
			if(peso < menor){
				menor = peso;
				pesoFlujoMax = peso;
				resultado = (ArrayList<Vertice<Planta>>) aux1.get(i);
			}
		}
		return resultado;
	}
	

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {
		for(int i=0; i<vertices.size();i++) {
			if(!vertices.get(i).getSeleccionado()) clickDentroDeCirculo(e.getX(), e.getY());
			if(vertices.get(i).getSeleccionado()) moveCircle(e.getX(), e.getY(), vertices.get(i));
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		for(int i=0; i<vertices.size(); i++) {
			vertices.get(i).setSeleccionado(false);
		}
	}
	
	public void clickDentroDeCirculo(int xC, int yC) {
		for(int i=0; i<vertices.size();i++) {
			if(xC >= vertices.get(i).getX() && xC<= vertices.get(i).getX()+40 && yC>=vertices.get(i).getY() && yC<=vertices.get(i).getY()+40)
				vertices.get(i).setSeleccionado(true);
			}
	}
	
	
	public void moveCircle(int xC, int yC, VerticeGrafo v) {
			v.setX(xC);
			v.setY(yC);
	}
	

	@Override
	public void mouseDragged(MouseEvent e) {
		for(int i=0; i<vertices.size();i++) 
		if(vertices.get(i).getSeleccionado()) {
			moveCircle(e.getX(), e.getY(), vertices.get(i));
		}
		repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {}
}

