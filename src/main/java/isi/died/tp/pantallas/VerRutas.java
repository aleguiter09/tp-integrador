package isi.died.tp.pantallas;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.*;

import isi.died.tp.dominio.*;
import isi.died.tp.estructuras.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VerRutas extends JPanel implements MouseListener, MouseMotionListener {

	private static final long serialVersionUID = 1L;
	private static final int  ancho=650, alto=571;
	
	private ArrayList<VerticeGrafo> vertices;
	private ArrayList<Ruta> rutas;

	public VerRutas(Principal principal) {
		setBackground(new Color(139, 69, 19));
		addMouseListener(this);
		addMouseMotionListener(this);
		setBounds(350, 0, ancho, alto);
		setLayout(null);
		
		vertices = new ArrayList<VerticeGrafo>();
		rutas = new ArrayList<Ruta>();
		
		for(Vertice<Planta> p: principal.grafo.vertices()) vertices.add(new VerticeGrafo(p));
		
		Button refrescar = new Button("Refrescar");
		refrescar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VerticeGrafo fi = null;
				VerticeGrafo in = null;
				vertices.clear();
				rutas.clear();
				for(int i=0; i<principal.grafo.vertices().size(); i++) {
					VerticeGrafo v = new VerticeGrafo();
					v.setVertice(principal.grafo.vertices().get(i));
					vertices.add(v);
				}
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
				repaint();
			}
		});
		refrescar.setBounds(535, 512, 70, 22);
		add(refrescar);
			
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor((new Color(139, 69, 19)));
		g.fillRect(0, 0, 650, 571);
		g.setColor(Color.white);
		for(int i=0; i<vertices.size();i++) {
			g.fillOval(vertices.get(i).getX(),vertices.get(i).getY(), 40, 40);
			g.drawString("Planta "+vertices.get(i).getVertice().getValor().getNombre()+" - ID: "+vertices.get(i).getVertice().getValor().getId() , vertices.get(i).getX()-20 , vertices.get(i).getY()+55);
		}
		for(int i=0; i<rutas.size(); i++) {
			g.drawLine(rutas.get(i).getInicio().getX()+20, rutas.get(i).getInicio().getY()+20, rutas.get(i).getFin().getX()+20, rutas.get(i).getFin().getY()+20);
		}
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

