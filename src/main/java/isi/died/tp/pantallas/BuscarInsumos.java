package isi.died.tp.pantallas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.util.*;
import isi.died.tp.dominio.*;

public class BuscarInsumos extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private static final int ancho = 650, alto = 571;
	
	private JTextField costoMin;
	private JTextField costoMax;
	private JTable table;
	private JTextField nombre;
	private JTextField id;
	private Object[][] valores;
	private DefaultTableModel modelo;
	private String[] columnas;
	private ArrayList<Insumo> array;  

	public BuscarInsumos(Principal principal, JPanel aux) {
		setBounds(350, 0, ancho, alto);
		setBackground(new Color(139, 69, 19));
		setLayout(null);
		
		//JLABELS
		JLabel lblCostoMinimo = new JLabel("Costo minimo: ");
		lblCostoMinimo.setFont(new Font("Dialog", Font.BOLD, 12));
		lblCostoMinimo.setForeground(new Color(255, 255, 255));
		lblCostoMinimo.setBounds(50, 150, 120, 25);
		add(lblCostoMinimo);

		JLabel lblCostoMaximo = new JLabel("Costo maximo:");
		lblCostoMaximo.setForeground(new Color(255, 255, 255));
		lblCostoMaximo.setFont(new Font("Dialog", Font.BOLD, 12));
		lblCostoMaximo.setBounds(350, 150, 120, 25);
		add(lblCostoMaximo);
		
		JLabel lblBuscarInsumo = new JLabel("BUSCAR INSUMO");
		lblBuscarInsumo.setFont(new Font("Dialog", Font.BOLD, 20));
		lblBuscarInsumo.setForeground(new Color(255, 255, 255));
		lblBuscarInsumo.setBounds(225, 25, 200, 25);
		add(lblBuscarInsumo);
		
		JLabel lblNombre = new JLabel("Nombre: ");
		lblNombre.setForeground(Color.WHITE);
		lblNombre.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNombre.setBounds(50, 100, 120, 25);
		add(lblNombre);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setForeground(Color.WHITE);
		lblId.setFont(new Font("Dialog", Font.BOLD, 12));
		lblId.setBounds(350, 100, 120, 25);
		add(lblId);
		
		//TEXTFIELDS
		id = new JTextField();
		id.setColumns(10);
		id.setBounds(475, 100, 100, 25);
		add(id);
		
		nombre = new JTextField();
		nombre.setColumns(10);
		nombre.setBounds(175, 100, 100, 25);
		add(nombre);
		
		costoMin = new JTextField();
		costoMin.setBounds(175, 150, 100, 25);
		add(costoMin);
		costoMin.setColumns(10);
		
		costoMax = new JTextField();
		costoMax.setBounds(475, 150, 100, 25);
		add(costoMax);
		costoMax.setColumns(10);
		
		//TABLA
		valores = new Object[1][1];
		columnas = new String[] {"ID", "Nombre", "Descripcion", "Unidad de medida","Cantidad Total","Costo", "Peso", "Es Refrigerado"};
		
		modelo = new DefaultTableModel(valores, columnas) {
			private static final long serialVersionUID = 1L;
			public boolean[] columnEditables = new boolean[] {
					false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			};	
		};
		
		//BOTONES
		Button buscar = new Button("Buscar");
		buscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				array = new ArrayList<Insumo>();
				//SI INGRESO UN ID, SOLO DEBEMOS TENER UN INSUMO EN EL ARRAY
				if(id.getText().length() != 0) {
					int idAux = Integer.valueOf(id.getText().trim()).intValue();
					Insumo aux1 = new Insumo();
					aux1.setId(idAux);
					Insumo i = principal.arbol.obtener(aux1);
					array.add(i);
				}
				else {
					if(nombre.getText().length() != 0) {
						String nom = nombre.getText();
						if(costoMin.getText().length() == 0 && costoMax.getText().length() == 0) {
							ArrayList<Insumo> todos = principal.arbol.preOrden();
							for(int i=0; i<todos.size(); i++) {
								if(todos.get(i).getNombre().contains(nom)) {
									array.add(todos.get(i));
								}
							}
						}
						else if(costoMin.getText().length() != 0 && costoMax.getText().length() == 0) {
							float min =  Float.valueOf(costoMin.getText().trim()).floatValue();
							ArrayList<Insumo> todos = principal.arbol.preOrden();
							for(int i=0; i<todos.size(); i++) {
								if(todos.get(i).getNombre().contains(nom) && todos.get(i).getCosto() >= min) {
									array.add(todos.get(i));
								}
							}
						}
						else if(costoMax.getText().length() != 0 && costoMin.getText().length() == 0) {
							float max =  Float.valueOf(costoMin.getText().trim()).floatValue();
							ArrayList<Insumo> todos = principal.arbol.preOrden();
							for(int i=0; i<todos.size(); i++) {
								if(todos.get(i).getNombre().contains(nom) && todos.get(i).getCosto() <= max) {
									array.add(todos.get(i));
								}
							}
						}
						else if(costoMax.getText().length() != 0 && costoMin.getText().length() != 0) {
							float min =  Float.valueOf(costoMin.getText().trim()).floatValue();
							float max = Float.valueOf(costoMax.getText().trim()).floatValue();
							ArrayList<Insumo> todos = principal.arbol.preOrden();
							for(int i=0; i<todos.size(); i++) {
								if(todos.get(i).getNombre().contains(nom) && todos.get(i).getCosto() <= max && todos.get(i).getCosto() >= min) {
									array.add(todos.get(i));
								}
							}
						}
					}
					else {
						if(costoMin.getText().length() != 0 && costoMax.getText().length() == 0) {
							float min =  Float.valueOf(costoMin.getText().trim()).floatValue();
							ArrayList<Insumo> todos = principal.arbol.preOrden();
							for(int i=0; i<todos.size(); i++) {
								if(todos.get(i).getCosto() >= min) {
									array.add(todos.get(i));
								}
							}
						}
						else if(costoMax.getText().length() != 0 && costoMin.getText().length() == 0) {
							float max = Float.valueOf(costoMax.getText().trim()).floatValue();
							ArrayList<Insumo> todos = principal.arbol.preOrden();
							for(int i=0; i<todos.size(); i++) {
								if(todos.get(i).getCosto() <= max) {
									array.add(todos.get(i));
								}
							}
						}
						else if(costoMax.getText().length() != 0 && costoMin.getText().length() != 0) {
							float min =  Float.valueOf(costoMin.getText().trim()).floatValue();
							float max = Float.valueOf(costoMax.getText().trim()).floatValue();
							ArrayList<Insumo> todos = principal.arbol.preOrden();
							for(int i=0; i<todos.size(); i++) {
								if(todos.get(i).getCosto() <= max && todos.get(i).getCosto() >= min) {
									array.add(todos.get(i));
								}
							}
						}	
					}
				}
				//Quitamos la planta auxiliar
				if(array.get(0).getId() == -1) array.remove(0);
				//Creo la matriz valores
				valores = new Object[array.size()][8]; 
				for(int i=0; i<array.size(); i++) {
					valores[i][0] = array.get(i).getId();
					valores[i][1] = array.get(i).getNombre();
					valores[i][2] = array.get(i).getDescripcion();
					valores[i][3] = array.get(i).getUnidadDeMedida();
					valores[i][4] = array.get(i).calcularTotal();
					valores[i][5] = array.get(i).getCosto();
					valores[i][6] = array.get(i).getPeso();
					valores[i][7] = array.get(i).isEsRefrigerado();
				}
				modelo.setDataVector(valores, columnas);
			}
		});
		buscar.setBounds(274, 210, 100, 25);
		add(buscar);
		
		Button cancelar = new Button("Cancelar");
		cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int resp = JOptionPane.showConfirmDialog(null, "¿Esta seguro que desea cancelar?","ALERTA!",JOptionPane.YES_NO_OPTION);
				if(resp != 1) {
					setVisible(false);
					aux.setVisible(true);
				}
			}
		});
		cancelar.setBounds(500, 500, 70, 25);
		add(cancelar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 266, 600, 215);
		add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(modelo);
	}
}


