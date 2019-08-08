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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;

public class VerRutas extends JPanel implements MouseListener, MouseMotionListener {

	private static final long serialVersionUID = 1L;
	private static final int  ancho=1000, alto=571;
	
	private ArrayList<VerticeGrafo> vertices;
	private ArrayList<Ruta> rutas;
	private String[] nombresInsumos;
	private Insumo elegido = null;
	private Principal prin;
	private ArrayList<Vertice<Planta>> resultado;
	private ArrayList<Vertice<Planta>> flujo;
	private JTextField camionPeso;
	private float pesoFlujoMax;
	private JTextField IdOrigen;
	private JTextField IdDestino;
	private JTable table;

	private Object[][] valores;
	private String[] columnas;
	private DefaultTableModel modelo;
	
	private List<List<Vertice<Planta>>> caminosEntre;

	public VerRutas(Principal principal) {
		setBackground(new Color(139, 69, 19));
		addMouseListener(this);
		addMouseMotionListener(this);
		setBounds(350, 0, ancho, alto);
		setLayout(null);
		
		caminosEntre = new ArrayList<List<Vertice<Planta>>>();
	
		valores = new Object[caminosEntre.size()][4];
		columnas = new String[] {"Camino", "Dist. Total", "Dur. Total", "Peso Total"};
		
		modelo = new DefaultTableModel(valores, columnas) {
			private static final long serialVersionUID = 1L;
			public boolean[] columnEditables = new boolean[] {
					false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			};
		};

		
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
		lblInsumos.setBounds(763, 29, 120, 27);
		add(lblInsumos);
		
		Checkbox checkbox = new Checkbox("Calcular Flujo Maximo");
		checkbox.setForeground(Color.WHITE);
		checkbox.setFont(new Font("Dialog", Font.BOLD, 12));
		checkbox.setBounds(725, 130, 184, 22);
		add(checkbox);
		
		camionPeso = new JTextField();
		camionPeso.setEditable(false);
		camionPeso.setBounds(725, 160, 147, 22);
		add(camionPeso);
		camionPeso.setColumns(10);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(nombresInsumos));
		comboBox.setBounds(725, 69, 184, 30);
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
				
				//Caminos de acopioInicial a acopioFinal	
				Planta acopioInicial = null;
				Planta acopioFinal = null;
				for(Vertice<Planta> p: principal.grafo.vertices()) {
					if(p.getValor().getId() == -1) acopioInicial = p.getValor();
					if(p.getValor().getId() == -2) acopioFinal = p.getValor();
				}
					
				List<List<Vertice<Planta>>> caminos = principal.grafo.caminos(acopioInicial, acopioFinal);
				//Obtengo los caminos que cubren todas las plantas
				List<List<Vertice<Planta>>> caminosFlujo = new ArrayList<List<Vertice<Planta>>>();
				for(List<Vertice<Planta>> lista: caminos) {
					if(lista.containsAll(principal.grafo.vertices())) caminosFlujo.add(lista);
				}
				
				//Obtengo los caminos que cubren los que necesitan insumo
				ArrayList<Vertice<Planta>> aux = new ArrayList<Vertice<Planta>>();
				for(int i=0; i<principal.grafo.vertices().size(); i++) {
					if(principal.grafo.vertices().get(i).getValor().necesitaInsumo(elegido))
						aux.add(principal.grafo.vertices().get(i));
				}
				List<List<Vertice<Planta>>> caminosInsumos = new ArrayList<List<Vertice<Planta>>>();
				for(List<Vertice<Planta>> lista: caminos) {
					if(lista.containsAll(aux)) caminosInsumos.add(lista);
				}
					
				if(checkbox.getState() && comboBox.getSelectedIndex()==0) {
					resultado.clear();
					flujo = flujoMaximo(caminosFlujo);
					for(int i=0; i<rutas.size(); i++) rutas.get(i).setEsResultado(false);
					for(int i=0; i<vertices.size(); i++) vertices.get(i).setNecesitaInsumo(false);
					for(int i=0; i<rutas.size(); i++) {
						if(flujo.contains(rutas.get(i).getArista().getInicio()) && flujo.contains(rutas.get(i).getArista().getFin())) {
							flujo.remove(rutas.get(i).getArista().getInicio());
							rutas.get(i).setEsFlujo(true);
						}
						else rutas.get(i).setEsFlujo(false);
					}
					camionPeso.setText(pesoFlujoMax+" KG");
				}
				else if(comboBox!=null && comboBox.getSelectedIndex()!=0 && !checkbox.getState()) {
					for(VerticeGrafo v: vertices)  if(v.getVertice().getValor().necesitaInsumo(elegido)) v.setNecesitaInsumo(true);	
					flujo.clear();
					resultado = mejorRuta(caminosInsumos);
					camionPeso.setText(null);
					for(int i=0; i<rutas.size(); i++) rutas.get(i).setEsFlujo(false);
					for(int i=0; i<rutas.size(); i++) {
						if(resultado.contains(rutas.get(i).getArista().getInicio()) && resultado.contains(rutas.get(i).getArista().getFin())) {
							resultado.remove(rutas.get(i).getArista().getInicio());
							rutas.get(i).setEsResultado(true);
						}
						else rutas.get(i).setEsResultado(false);
					}
				}
		comboBox.setModel(new DefaultComboBoxModel<String>(nombresInsumos));
		repaint();
				
	}
});

		refrescar.setBounds(898, 526, 70, 22);
		add(refrescar);
		
		Button calcularCaminos = new Button("Obtener caminos");
		calcularCaminos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(IdOrigen.getText().isEmpty()||IdDestino.getText().isEmpty()) 
					JOptionPane.showMessageDialog(null, "Debe completar todos los campos","¡ERROR!", JOptionPane.WARNING_MESSAGE);
				else {
					int idO = Integer.valueOf(IdOrigen.getText().trim()).intValue();
					int idD = Integer.valueOf(IdDestino.getText().trim()).intValue();
					Planta origen = null;
					Planta destino = null;
					for(Vertice<Planta> p: principal.grafo.vertices()) {
						if(p.getValor().getId() == idO) origen = p.getValor();
						if(p.getValor().getId() == idD) destino = p.getValor();
					}
					if(origen == null) JOptionPane.showMessageDialog(null, "No se encontró planta ORIGEN \nPor favor, ingrese un ID Planta válido","¡ERROR!", JOptionPane.WARNING_MESSAGE);
					else if(destino == null) JOptionPane.showMessageDialog(null, "No se encontró planta DESTINO \nPor favor, ingrese un ID Planta válido","¡ERROR!", JOptionPane.WARNING_MESSAGE);
					else caminosEntre = principal.grafo.caminos(principal.grafo.getNodo(origen),principal.grafo.getNodo(destino));
				}
				
				String nom="[";
				float dist = 0;
				float durac = 0;
				float peso = 0;
				
				valores = new Object[caminosEntre.size()][4];
				ArrayList<Arista<Planta>> aristas = new ArrayList<>();
				
				for(int i=0; i<caminosEntre.size(); i++) {
					for(int j=0; j<caminosEntre.get(i).size(); j++) {
						if(j==caminosEntre.get(i).size()-1) nom += caminosEntre.get(i).get(j).getValor().getId()+"]";
						else nom += caminosEntre.get(i).get(j).getValor().getId()+"] [";
					}

					aristas.clear();
					dist=0;
					durac=0;
					peso=0;
					for(int w=0;w<caminosEntre.get(i).size()-1;w++) {
						aristas.add(prin.grafo.buscarArista(caminosEntre.get(i).get(w), caminosEntre.get(i).get(w+1)));
					}
						for(Arista<Planta> a: aristas) {
							dist+=a.getDistancia();
							durac+=a.getDuracion();
							peso+=a.getPesoCamion();
						}
					valores[i][0] = nom; 
					valores[i][1] = dist;
					valores[i][2] = durac;
					valores[i][3] = peso;
					nom="[";
				}
				modelo.setDataVector(valores,columnas);
			}
		});
		calcularCaminos.setFont(new Font("Dialog", Font.BOLD, 12));
		calcularCaminos.setBounds(800, 298, 108, 22);
		add(calcularCaminos);
		
		IdOrigen = new JTextField();
		IdOrigen.setBounds(822, 215, 50, 20);
		add(IdOrigen);
		IdOrigen.setColumns(10);
		IdOrigen.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char validar=e.getKeyChar();
				if(Character.isLetter(validar)) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		
		IdDestino = new JTextField();
		IdDestino.setBounds(822, 260, 50, 20);
		add(IdDestino);
		IdDestino.setColumns(10);
		IdDestino.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char validar=e.getKeyChar();
				if(Character.isLetter(validar)) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		
		JLabel lblIdPlantaOrigen = new JLabel("ID Planta origen");
		lblIdPlantaOrigen.setForeground(Color.WHITE);
		lblIdPlantaOrigen.setFont(new Font("Dialog", Font.BOLD, 12));
		lblIdPlantaOrigen.setBounds(710, 215, 113, 14);
		add(lblIdPlantaOrigen);
		
		JLabel lblIdPlantaDestino = new JLabel("ID Planta destino");
		lblIdPlantaDestino.setForeground(Color.WHITE);
		lblIdPlantaDestino.setFont(new Font("Dialog", Font.BOLD, 12));
		lblIdPlantaDestino.setBounds(710, 260, 113, 14);
		add(lblIdPlantaDestino);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(667, 338, 302, 170);
		add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(modelo);
		
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
				if(a.getPesoCamion() != 0.0)
				peso=a.getPesoCamion();
			}
			if(peso < menor){
				menor = peso;
				resultado = (ArrayList<Vertice<Planta>>) aux1.get(i);
			}
		}
		pesoFlujoMax = menor;
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

