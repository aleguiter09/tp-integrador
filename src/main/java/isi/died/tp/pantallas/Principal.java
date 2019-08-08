package isi.died.tp.pantallas;

import isi.died.tp.estructuras.*;
import isi.died.tp.dominio.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class Principal {
	private int ancho = 1250, alto = 610; 
	private JFrame frame;
	private RegistroInsumo registroI;
	private RegistroPlanta registroP;
	private RegistroCamion registroC;
	private RegistrarRuta registroR;
	private BuscarInsumos buscarI;
	private BuscarPlantas buscarP;
	private VerCamiones verC;
	private VerRutas verR;
	
	public GrafoPlanta grafo;
	public ArbolBinarioBusqueda<Insumo> arbol;
	public ArrayList<Camion> camiones;
	
	private Planta acopioPuerto, acopioFinal;

	public static void main(String[] args) {
		new Principal();
	}

	public Principal() {
		//Instanciamos estructuras
		grafo = new GrafoPlanta();
		acopioPuerto = new Planta(-1, "Acopio Puerto");
		acopioFinal = new Planta(-2, "Acopio Final");
		grafo.addNodo(acopioPuerto);
		grafo.addNodo(acopioFinal);
		
		Insumo inicio = new Insumo(-1,"aux",null,null,false,0,0);
		arbol = new ArbolBinarioBusqueda<Insumo>(inicio);
		camiones = new ArrayList<Camion>();
		//Instanciamos prueba
		instanciarPruebas();
		//Instancio pantallas
		iniciarFrame();
		iniciarPanelPrincipal();
		iniciarPantallas();
		frame.setVisible(true);
	}
	
	public void iniciarFrame() {
		frame = new JFrame();
		frame.setSize(new Dimension(ancho, alto));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Gestion de plantas");
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setBackground(new Color(139, 69, 19));
	}
	
	public void iniciarPanelPrincipal() {
		JPanel panel = new JPanel();
		panel.setBackground(new Color(139, 69, 19));
		panel.setBounds(0, 0, 350, 571);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		//Icono caja
		JLabel lblPlanta = new JLabel("");
		lblPlanta.setBounds(74, 20, 188, 197);
		ImageIcon imgIcon = new ImageIcon(getClass().getResource("/isi/died/tp/pantallas/Caja.png"));
        Image imgEscalada = imgIcon.getImage().getScaledInstance(lblPlanta.getWidth(),lblPlanta.getHeight(), Image.SCALE_SMOOTH);
        Icon iconoEscalado = new ImageIcon(imgEscalada);
		lblPlanta.setIcon(iconoEscalado);
		panel.add(lblPlanta);
		//
		Button registrarI = new Button("Registrar insumo");
		registrarI.setForeground(Color.WHITE);
		registrarI.setFont(new Font("Dialog", Font.BOLD, 12));
		registrarI.setBackground(new Color(50, 205, 50));
		registrarI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				esconderPantallas();
				registroI.setVisible(true);
			}
		});
		registrarI.setBounds(45, 235, 110, 60);
		panel.add(registrarI);
		
		Button registrarP = new Button("Registrar planta");
		registrarP.setForeground(Color.WHITE);
		registrarP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				esconderPantallas();
				registroP.setVisible(true);
			}
		});
		
		registrarP.setFont(new Font("Dialog", Font.BOLD, 12));
		registrarP.setBackground(new Color(50, 205, 50));
		registrarP.setBounds(45, 320, 110, 60);
		panel.add(registrarP);
		
		Button buscarInsumo = new Button("Buscar insumo");
		buscarInsumo.setForeground(Color.WHITE);
		buscarInsumo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				esconderPantallas();
				buscarI.setVisible(true);
			}
		});
		
		buscarInsumo.setFont(new Font("Dialog", Font.BOLD, 12));
		buscarInsumo.setBackground(new Color(50, 205, 50));
		buscarInsumo.setBounds(190, 235, 110, 60);
		panel.add(buscarInsumo);
		
		Button buscarPlanta = new Button("Buscar planta");
		buscarPlanta.setForeground(Color.WHITE);
		buscarPlanta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				esconderPantallas();
				buscarP.setVisible(true);
			}
		});
		buscarPlanta.setFont(new Font("Dialog", Font.BOLD, 12));
		buscarPlanta.setBackground(new Color(50, 205, 50));
		buscarPlanta.setBounds(190, 320, 110, 60);
		panel.add(buscarPlanta);
		
		Button registrarC = new Button("Registrar camión");
		registrarC.setForeground(Color.WHITE);
		registrarC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				esconderPantallas();
				registroC.setVisible(true);		
			}
		});
		registrarC.setFont(new Font("Dialog", Font.BOLD, 12));
		registrarC.setBackground(new Color(50, 205, 50));
		registrarC.setBounds(45, 405, 110, 60);
		panel.add(registrarC);
		
		Button verRuta = new Button("Ver ruta");
		verRuta.setForeground(Color.WHITE);
		verRuta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				esconderPantallas();
				verR.setVisible(true);
			}
		});
		verRuta.setFont(new Font("Dialog", Font.BOLD, 12));
		verRuta.setBackground(new Color(50, 205, 50));
		verRuta.setBounds(190, 490, 110, 60);
		panel.add(verRuta);
		
		Button verCamiones = new Button("Ver camiones");
		verCamiones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				esconderPantallas();
				verC.setVisible(true);
			}
		});
		verCamiones.setForeground(new Color(255, 255, 255));
		verCamiones.setFont(new Font("Dialog", Font.BOLD, 12));
		verCamiones.setBackground(new Color(50, 205, 50));
		verCamiones.setBounds(190, 405, 110, 60);
		panel.add(verCamiones);
		
		Button cerrar = new Button("Registrar Ruta");
		cerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				esconderPantallas();
				registroR.setVisible(true);
			}
		});
		cerrar.setForeground(new Color(255, 255, 255));
		cerrar.setFont(new Font("Dialog", Font.BOLD, 12));
		cerrar.setBackground(new Color(50, 205, 50));
		cerrar.setBounds(45, 490, 110, 60);
		panel.add(cerrar);
		
	}

	public void iniciarPantallas() {
		registroI = new RegistroInsumo(this);
		registroP = new RegistroPlanta(this);
		registroC = new RegistroCamion(this);
		registroR = new RegistrarRuta(this);
		buscarI = new BuscarInsumos(this);
		buscarP = new BuscarPlantas(this);
		verC = new VerCamiones(this);
		verR = new VerRutas(this);
		
		frame.getContentPane().add(registroI);
		frame.getContentPane().add(registroC);
		frame.getContentPane().add(registroP);
		frame.getContentPane().add(registroR);
		frame.getContentPane().add(buscarI);
		frame.getContentPane().add(buscarP);
		frame.getContentPane().add(verR);
		frame.getContentPane().add(verC);
		
		esconderPantallas();
	}
	
	public void esconderPantallas() {
		registroI.setVisible(false);
		registroC.setVisible(false);
		registroP.setVisible(false);
		registroR.setVisible(false);
		buscarI.setVisible(false);
		buscarP.setVisible(false);
		verC.setVisible(false);
		verR.setVisible(false);
	}
	
	public void instanciarPruebas() {
		Camion c0 = new Camion(0,20,2010, true, "Ford", "A1", "MXX-963", 45);
		Camion c1 = new Camion(1,200,2005, false, "Ford", "A2", "LMN-471", 421);
		Camion c2 = new Camion(2,900,2004, false, "Mercedes", "M12", "QER-100", 698);
		Camion c3 = new Camion(3,1000,2010, true, "Mercedes", "A19", "BZZ-699", 721);
		Camion c4 = new Camion(4,400,2007, false, "Renault Truck", "R369", "CXX-741", 410);
		Camion c5 = new Camion(5,350,2008, false, "Iveco", "I85", "AAS-450", 440);
		Camion c6 = new Camion(6,960,2001, true, "Scania", "S55-1", "QTY-666", 675);
		Camion c7 = new Camion(7,410,2009, false, "Scania", "S96-2", "FGT-755", 423);
		Camion c8 = new Camion(8,740,2011, false, "Ford", "A1B", "PLO-745", 522);
		Camion c9 = new Camion(9,1200,2012, true, "Iveco", "I96", "ALE-417", 796);
		Camion c10 = new Camion(10,300,2015, false, "Iveco", "I99", "FIO-300", 404);
		Camion c11 = new Camion(11,400,2015, false, "Mercedes", "M20", "HHG-200", 412);
		Camion c12 = new Camion(12,800,2003, false, "Renault Truck", "R451", "GFF-633", 543);
		Camion c13 = new Camion(13,850,2000, false, "Renault Truck", "R114", "ASW-966", 560);
		Camion c14 = new Camion(14,1400,2001, true, "Scania", "S10-6", "EXE-417", 842);
		Camion c15 = new Camion(15,1500,2014, true, "Scania", "S99-6", "ÑPP-700", 918);
				
		camiones.add(c0);
		camiones.add(c1);
		camiones.add(c2);
		camiones.add(c3);
		camiones.add(c4);
		camiones.add(c5);
		camiones.add(c6);
		camiones.add(c7);
		camiones.add(c8);
		camiones.add(c9);
		camiones.add(c10);
		camiones.add(c11);
		camiones.add(c12);
		camiones.add(c13);
		camiones.add(c14);
		camiones.add(c15);
		
		Planta p0 = new Planta(10, "Milkaut");
		Planta p1 = new Planta(11, "Nestle");
		Planta p2 = new Planta(12, "Great Value");
		Planta p3 = new Planta(13, "Ilolay");
		Planta p4 = new Planta(14, "Arcor");
		Planta p5 = new Planta(15, "Terrabusi");
		Planta p6 = new Planta(16, "La Serenisima");
		
		grafo.addNodo(p0);
		grafo.addNodo(p1);
		grafo.addNodo(p2);
		grafo.addNodo(p3);
		grafo.addNodo(p4);
		grafo.addNodo(p5);
		grafo.addNodo(p6);
				
		Insumo i0 = new Insumo(0,"Leche",null,Medida.LITRO,false,1,10);
		Insumo i1 = new Insumo(1,"Manteca",null,Medida.KILO,true,24,1);
		Insumo i2 = new Insumo(2,"Queso",null,Medida.KILO,true,33,1);
		Insumo i3 = new Insumo(3,"Masitas",null,Medida.PIEZA,false,19,0);
		Insumo i4 = new Insumo(4,"Cafe",null,Medida.KILO,false,19,1);
		Insumo i5 = new Insumo(5,"Aceite",null,Medida.LITRO,false,14,920);
		Insumo i6 = new Insumo(6,"Yogurt",null,Medida.LITRO,true,16,1030);
		Insumo i7 = new Insumo(7,"Dulce de Leche",null,Medida.KILO,false,29,1);
		Insumo i8 = new Insumo(8,"Crema",null,Medida.KILO,true,26,1);
		Insumo i9 = new Insumo(9,"Queso Untable",null,Medida.KILO,true,28,1);
		Insumo i10 = new Insumo(10,"Fideos Moñito",null,Medida.KILO,false,24,1);
		Insumo i11 = new Insumo(11,"Fideos Mostacholi",null,Medida.KILO,false,17,1);
		Insumo i12 = new Insumo(12,"Fideos Capelletinis",null,Medida.KILO,false,21,1);
		Insumo i13 = new Insumo(13,"Fideos Coditos",null,Medida.KILO,false,14,1);
		Insumo i14 = new Insumo(14,"Vinagre de Manzana",null, Medida.LITRO,false,27,1000);
		Insumo i15 = new Insumo(15,"Vinagre de Vino",null,Medida.LITRO,false,29,1000);
		Insumo i16 = new Insumo(16,"Snacks Papitas",null,Medida.PIEZA,false,33,0);
		Insumo i17 = new Insumo(17,"Snacks Palitos",null,Medida.PIEZA,false,20,0);
		
		arbol.agregar(i0);
		arbol.agregar(i1);
		arbol.agregar(i2);
		arbol.agregar(i3);
		arbol.agregar(i4);
		arbol.agregar(i5);
		arbol.agregar(i6);
		arbol.agregar(i7);
		arbol.agregar(i8);
		arbol.agregar(i9);
		arbol.agregar(i10);
		arbol.agregar(i11);
		arbol.agregar(i12);
		arbol.agregar(i13);
		arbol.agregar(i14);
		arbol.agregar(i15);
		arbol.agregar(i16);
		arbol.agregar(i17);
				
		Stock s0 = new Stock(0,500, 1300, i0);
		Stock s1 = new Stock(1,303, 150, i1);
		Stock s2 = new Stock(2,951, 500, i0);
		Stock s3 = new Stock(3,88, 100, i4);
		Stock s4 = new Stock(4,1710, 1000, i5);
		Stock s5 = new Stock(5,1371, 500, i3);
		Stock s6 = new Stock(6,622, 700, i5);
		Stock s7 = new Stock(7,273, 400, i6);
		Stock s8 = new Stock(8,804, 600, i6);
		Stock s9 = new Stock(9,571, 500, i7);
		Stock s10 = new Stock(10,1322, 800, i8);
		Stock s11 = new Stock(11,991, 1000, i7);
		Stock s12 = new Stock(12,1544, 1000, i7);
		Stock s13 = new Stock(13,1755, 1300, i8);
		Stock s14 = new Stock(14,410, 800, i8);
		Stock s15 = new Stock(15,1438, 1000, i9);
		Stock s16 = new Stock(16,209, 500, i9);
		Stock s17 = new Stock(17,410, 500, i10);
		Stock s18 = new Stock(18,896, 700, i11);
		Stock s19 = new Stock(19,560, 500, i12);
		Stock s20 = new Stock(20,740, 800, i13);
		Stock s21 = new Stock(21,1120, 1000, i10);
		Stock s22 = new Stock(22,1456, 1000, i11);
		Stock s23 = new Stock(23,971, 1000, i12);
		Stock s24 = new Stock(24,506, 1000, i13);
		Stock s25 = new Stock(25,461, 500, i14);
		Stock s26 = new Stock(26,844, 500, i15);
		Stock s27 = new Stock(27,1661,900, i16);
		Stock s28 = new Stock(28,995, 900, i17);
		Stock s29 = new Stock(29,1230,1500, i16);
		Stock s30 = new Stock(30,1887,1500, i17);
		Stock s31 = new Stock(31,1478, 1000, i2);
		Stock s32 = new Stock(32,1250, 1300, i2);
		
		p0.getStock().add(s0);
		p0.getStock().add(s1);
		p0.getStock().add(s2);
		p1.getStock().add(s3);
		p2.getStock().add(s4);
		p2.getStock().add(s5);
		p2.getStock().add(s6);
		p3.getStock().add(s7);
		p6.getStock().add(s8);
		p0.getStock().add(s9);
		p0.getStock().add(s10);
		p3.getStock().add(s11);
		p6.getStock().add(s12);
		p6.getStock().add(s13);
		p3.getStock().add(s14);
		p0.getStock().add(s15);
		p3.getStock().add(s16);
		p4.getStock().add(s17);
		p4.getStock().add(s18);
		p4.getStock().add(s19);
		p4.getStock().add(s20);
		p5.getStock().add(s21);
		p5.getStock().add(s22);
		p5.getStock().add(s23);
		p5.getStock().add(s24);
		p2.getStock().add(s25);
		p2.getStock().add(s26);
		p2.getStock().add(s27);
		p2.getStock().add(s28);
		p1.getStock().add(s29);
		p1.getStock().add(s30);
		p0.getStock().add(s31);
		p6.getStock().add(s32);
		
	
		grafo.conectar(acopioPuerto, p0, 0, 0, 0);
		grafo.conectar(p0, p1, 600, 1, 2000);
		grafo.conectar(p1, p2, 1000, (float)0.5, 1000);
		grafo.conectar(p2, p3, 800, (float)0.2, 1500);
		grafo.conectar(p3, p4, 400, (float)1.1, 2500);
		grafo.conectar(p4, p5, 1500, (float)0.3, 900);
		grafo.conectar(p5, p6, 900, (float)0.4, 800);
		grafo.conectar(p6, acopioFinal, 0, 0, 0);
		
	}
}