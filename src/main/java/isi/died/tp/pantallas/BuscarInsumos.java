package isi.died.tp.pantallas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

import java.util.*;
import java.util.List;

import isi.died.tp.dominio.*;
import isi.died.tp.estructuras.ArbolBinarioBusqueda;

public class BuscarInsumos extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private static final int ancho = 900, alto = 571;
	
	private JTextField costoMin;
	private JTextField costoMax;
	private JTable table;
	private JTextField nombre;
	private JTextField id;
	private Object[][] valores;
	private DefaultTableModel modelo;
	private String[] columnas;
	private ArrayList<Insumo> array;  
	private JTextField stockMin;
	private JTextField stockMax;
	private JTextField idEliminar;
	private JTextField valor;

	public BuscarInsumos(Principal principal) {
		setBounds(350, 0, ancho, alto);
		setBackground(new Color(139, 69, 19));
		setLayout(null);
		
		//JLABELS
		JLabel lblCostoMinimo = new JLabel("Costo minimo: ");
		lblCostoMinimo.setFont(new Font("Dialog", Font.BOLD, 12));
		lblCostoMinimo.setForeground(new Color(255, 255, 255));
		lblCostoMinimo.setBounds(50, 130, 120, 25);
		add(lblCostoMinimo);

		JLabel lblCostoMaximo = new JLabel("Costo maximo:");
		lblCostoMaximo.setForeground(new Color(255, 255, 255));
		lblCostoMaximo.setFont(new Font("Dialog", Font.BOLD, 12));
		lblCostoMaximo.setBounds(350, 130, 120, 25);
		add(lblCostoMaximo);
		
		JLabel lblBuscarInsumo = new JLabel("BUSCAR INSUMO");
		lblBuscarInsumo.setFont(new Font("Dialog", Font.BOLD, 20));
		lblBuscarInsumo.setForeground(new Color(255, 255, 255));
		lblBuscarInsumo.setBounds(225, 25, 200, 25);
		add(lblBuscarInsumo);
		
		JLabel lblNombre = new JLabel("Nombre: ");
		lblNombre.setForeground(Color.WHITE);
		lblNombre.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNombre.setBounds(50, 80, 120, 25);
		add(lblNombre);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setForeground(Color.WHITE);
		lblId.setFont(new Font("Dialog", Font.BOLD, 12));
		lblId.setBounds(350, 80, 120, 25);
		add(lblId);
		
		JLabel lblStockMinimo = new JLabel("Stock minimo: ");
		lblStockMinimo.setForeground(Color.WHITE);
		lblStockMinimo.setFont(new Font("Dialog", Font.BOLD, 12));
		lblStockMinimo.setBounds(50, 180, 120, 25);
		add(lblStockMinimo);
		
		JLabel lblStockMaximo = new JLabel("Stock maximo:");
		lblStockMaximo.setForeground(Color.WHITE);
		lblStockMaximo.setFont(new Font("Dialog", Font.BOLD, 12));
		lblStockMaximo.setBounds(350, 180, 120, 25);
		add(lblStockMaximo);
		
		JLabel lblIdAEliminar = new JLabel("ID a eliminar:");
		lblIdAEliminar.setForeground(Color.WHITE);
		lblIdAEliminar.setFont(new Font("Dialog", Font.BOLD, 12));
		lblIdAEliminar.setBounds(50, 500, 115, 25);
		add(lblIdAEliminar);
		
		JLabel lblValor = new JLabel("Valor:");
		lblValor.setForeground(Color.WHITE);
		lblValor.setFont(new Font("Dialog", Font.BOLD, 12));
		lblValor.setBounds(50, 460, 115, 25);
		add(lblValor);
		
		//TEXTFIELDS
		id = new JTextField();
		id.setColumns(10);
		id.setBounds(475, 80, 100, 25);
		add(id);
		id.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char validar=e.getKeyChar();
				if(Character.isLetter(validar)) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		nombre = new JTextField();
		nombre.setColumns(10);
		nombre.setBounds(175, 80, 100, 25);
		add(nombre);
		
		costoMin = new JTextField();
		costoMin.setBounds(175, 130, 100, 25);
		add(costoMin);
		costoMin.setColumns(10);
		costoMin.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char validar=e.getKeyChar();
				if(Character.isLetter(validar)) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		
		costoMax = new JTextField();
		costoMax.setBounds(475, 130, 100, 25);
		add(costoMax);
		costoMax.setColumns(10);
		costoMax.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char validar=e.getKeyChar();
				if(Character.isLetter(validar)) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		
		//TABLA
		valores = new Object[1][1];
		columnas = new String[] {"ID", "Nombre", "Descripcion","Unidad de medida","Cantidad Total","Costo","Peso","Es Refrigerado"};
		
		modelo = new DefaultTableModel(valores, columnas) {
			private static final long serialVersionUID = 1L;
			public boolean[] columnEditables = new boolean[] {
					false, false, false, false, false, false, false, false
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
					if(stockMin.getText().isEmpty() && stockMax.getText().isEmpty()) {
						if(nombre.getText().length() != 0) {
							String nom = nombre.getText();
							if(costoMin.getText().isEmpty() && costoMax.getText().isEmpty()) {
								ArrayList<Insumo> todos = principal.arbol.preOrden();
								for(int i=0; i<todos.size(); i++) {
									if(todos.get(i).getNombre().contains(nom)) {
										array.add(todos.get(i));
									}
								}
							}
							else if(!costoMin.getText().isEmpty() && costoMax.getText().isEmpty()) {
								float min =  Float.valueOf(costoMin.getText().trim()).floatValue();
								ArrayList<Insumo> todos = principal.arbol.preOrden();
								for(int i=0; i<todos.size(); i++) {
									if(todos.get(i).getNombre().contains(nom) && todos.get(i).getCosto() >= min) {
										array.add(todos.get(i));
									}
								}
							}
							else if(!costoMax.getText().isEmpty() && costoMin.getText().isEmpty()) {
								float max =  Float.valueOf(costoMin.getText().trim()).floatValue();
								ArrayList<Insumo> todos = principal.arbol.preOrden();
								for(int i=0; i<todos.size(); i++) {
									if(todos.get(i).getNombre().contains(nom) && todos.get(i).getCosto() <= max) {
										array.add(todos.get(i));
									}
								}
							}
							else if(!costoMax.getText().isEmpty() && !costoMin.getText().isEmpty()) {
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
							if(!costoMin.getText().isEmpty() && costoMax.getText().isEmpty()) {
								float min =  Float.valueOf(costoMin.getText().trim()).floatValue();
								ArrayList<Insumo> todos = principal.arbol.preOrden();
								for(int i=0; i<todos.size(); i++) {
									if(todos.get(i).getCosto() >= min) {
										array.add(todos.get(i));
									}
								}
							}
							else if(!costoMax.getText().isEmpty() && costoMin.getText().isEmpty()) {
								float max = Float.valueOf(costoMax.getText().trim()).floatValue();
								ArrayList<Insumo> todos = principal.arbol.preOrden();
								for(int i=0; i<todos.size(); i++) {
									if(todos.get(i).getCosto() <= max) {
										array.add(todos.get(i));
									}
								}
							}
							else if(!costoMax.getText().isEmpty() && !costoMin.getText().isEmpty()) {
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
					else if(!stockMin.getText().isEmpty() && stockMax.getText().isEmpty()) {
						int Smin =  Integer.valueOf(stockMin.getText().trim()).intValue();
						if(nombre.getText().length() != 0) {
							String nom = nombre.getText();
							if(costoMin.getText().isEmpty() && costoMax.getText().isEmpty()) {
								ArrayList<Insumo> todos = principal.arbol.preOrden();
								for(int i=0; i<todos.size(); i++) {
									if(todos.get(i).getNombre().contains(nom) && todos.get(i).calcularTotal() >= Smin) {
										array.add(todos.get(i));
									}
								}
							}
							else if(!costoMin.getText().isEmpty() && costoMax.getText().isEmpty()) {
								float Cmin =  Float.valueOf(costoMin.getText().trim()).floatValue();
								ArrayList<Insumo> todos = principal.arbol.preOrden();
								for(int i=0; i<todos.size(); i++) {
									if(todos.get(i).getNombre().contains(nom) && todos.get(i).getCosto() >= Cmin && todos.get(i).calcularTotal() >= Smin) {
										array.add(todos.get(i));
									}
								}
							}
							else if(!costoMax.getText().isEmpty() && costoMin.getText().isEmpty()) {
								float max =  Float.valueOf(costoMin.getText().trim()).floatValue();
								ArrayList<Insumo> todos = principal.arbol.preOrden();
								for(int i=0; i<todos.size(); i++) {
									if(todos.get(i).getNombre().contains(nom) && todos.get(i).getCosto() <= max && todos.get(i).calcularTotal() >= Smin) {
										array.add(todos.get(i));
									}
								}
							}
							else if(!costoMax.getText().isEmpty() && !costoMin.getText().isEmpty()) {
								float min =  Float.valueOf(costoMin.getText().trim()).floatValue();
								float max = Float.valueOf(costoMax.getText().trim()).floatValue();
								ArrayList<Insumo> todos = principal.arbol.preOrden();
								for(int i=0; i<todos.size(); i++) {
									if(todos.get(i).getNombre().contains(nom) && todos.get(i).getCosto() <= max && todos.get(i).getCosto() >= min && todos.get(i).calcularTotal() >= Smin) {
										array.add(todos.get(i));
									}
								}
							}
						}
						else {
							if(costoMin.getText().isEmpty() && costoMax.getText().isEmpty()) {
								ArrayList<Insumo> todos = principal.arbol.preOrden();
								for(int i=0; i<todos.size(); i++) {
									if(todos.get(i).calcularTotal() >= Smin) {
										array.add(todos.get(i));
									}
								}
							}
							else if(!costoMin.getText().isEmpty() && costoMax.getText().isEmpty()) {
								float min =  Float.valueOf(costoMin.getText().trim()).floatValue();
								ArrayList<Insumo> todos = principal.arbol.preOrden();
								for(int i=0; i<todos.size(); i++) {
									if(todos.get(i).getCosto() >= min && todos.get(i).calcularTotal() >= Smin) {
										array.add(todos.get(i));
									}
								}
							}
							else if(!costoMax.getText().isEmpty() && costoMin.getText().isEmpty()) {
								float max = Float.valueOf(costoMax.getText().trim()).floatValue();
								ArrayList<Insumo> todos = principal.arbol.preOrden();
								for(int i=0; i<todos.size(); i++) {
									if(todos.get(i).getCosto() <= max && todos.get(i).calcularTotal() >= Smin) {
										array.add(todos.get(i));
									}
								}
							}
							else if(!costoMax.getText().isEmpty() && !costoMin.getText().isEmpty()) {
								float min =  Float.valueOf(costoMin.getText().trim()).floatValue();
								float max = Float.valueOf(costoMax.getText().trim()).floatValue();
								ArrayList<Insumo> todos = principal.arbol.preOrden();
								for(int i=0; i<todos.size(); i++) {
									if(todos.get(i).getCosto() <= max && todos.get(i).getCosto() >= min && todos.get(i).calcularTotal() >= Smin) {
										array.add(todos.get(i));
									}
								}
							}	
						}
					}
					else if(stockMin.getText().isEmpty() && !stockMax.getText().isEmpty()) {
						int Smax =  Integer.valueOf(stockMax.getText().trim()).intValue();
						if(nombre.getText().length() != 0) {
							String nom = nombre.getText();
							if(costoMin.getText().isEmpty() && costoMax.getText().isEmpty()) {
								ArrayList<Insumo> todos = principal.arbol.preOrden();
								for(int i=0; i<todos.size(); i++) {
									if(todos.get(i).getNombre().contains(nom) && todos.get(i).calcularTotal() <= Smax) {
										array.add(todos.get(i));
									}
								}
							}
							else if(!costoMin.getText().isEmpty() && costoMax.getText().isEmpty()) {
								float Cmin =  Float.valueOf(costoMin.getText().trim()).floatValue();
								ArrayList<Insumo> todos = principal.arbol.preOrden();
								for(int i=0; i<todos.size(); i++) {
									if(todos.get(i).getNombre().contains(nom) && todos.get(i).getCosto() >= Cmin && todos.get(i).calcularTotal() <= Smax) {
										array.add(todos.get(i));
									}
								}
							}
							else if(!costoMax.getText().isEmpty() && costoMin.getText().isEmpty()) {
								float max =  Float.valueOf(costoMin.getText().trim()).floatValue();
								ArrayList<Insumo> todos = principal.arbol.preOrden();
								for(int i=0; i<todos.size(); i++) {
									if(todos.get(i).getNombre().contains(nom) && todos.get(i).getCosto() <= max && todos.get(i).calcularTotal() <= Smax) {
										array.add(todos.get(i));
									}
								}
							}
							else if(!costoMax.getText().isEmpty() && !costoMin.getText().isEmpty()) {
								float min =  Float.valueOf(costoMin.getText().trim()).floatValue();
								float max = Float.valueOf(costoMax.getText().trim()).floatValue();
								ArrayList<Insumo> todos = principal.arbol.preOrden();
								for(int i=0; i<todos.size(); i++) {
									if(todos.get(i).getNombre().contains(nom) && todos.get(i).getCosto() <= max && todos.get(i).getCosto() >= min && todos.get(i).calcularTotal() <= Smax) {
										array.add(todos.get(i));
									}
								}
							}
						}
						else {
							if(costoMin.getText().isEmpty() && costoMax.getText().isEmpty()) {
								ArrayList<Insumo> todos = principal.arbol.preOrden();
								for(int i=0; i<todos.size(); i++) {
									if(todos.get(i).calcularTotal() <= Smax) {
										array.add(todos.get(i));
									}
								}
							}
							
							else if(!costoMin.getText().isEmpty() && costoMax.getText().isEmpty()) {
								float min =  Float.valueOf(costoMin.getText().trim()).floatValue();
								ArrayList<Insumo> todos = principal.arbol.preOrden();
								for(int i=0; i<todos.size(); i++) {
									if(todos.get(i).getCosto() >= min && todos.get(i).calcularTotal() >= Smax) {
										array.add(todos.get(i));
									}
								}
							}
							else if(!costoMax.getText().isEmpty() && costoMin.getText().isEmpty()) {
								float max = Float.valueOf(costoMax.getText().trim()).floatValue();
								ArrayList<Insumo> todos = principal.arbol.preOrden();
								for(int i=0; i<todos.size(); i++) {
									if(todos.get(i).getCosto() <= max && todos.get(i).calcularTotal() >= Smax) {
										array.add(todos.get(i));
									}
								}
							}
							else if(!costoMax.getText().isEmpty() && !costoMin.getText().isEmpty()) {
								float min =  Float.valueOf(costoMin.getText().trim()).floatValue();
								float max = Float.valueOf(costoMax.getText().trim()).floatValue();
								ArrayList<Insumo> todos = principal.arbol.preOrden();
								for(int i=0; i<todos.size(); i++) {
									if(todos.get(i).getCosto() <= max && todos.get(i).getCosto() >= min && todos.get(i).calcularTotal() <= Smax) {
										array.add(todos.get(i));
									}
								}
							}	
						}
					}
					else {
						int Smax =  Integer.valueOf(stockMax.getText().trim()).intValue();
						int Smin =  Integer.valueOf(stockMin.getText().trim()).intValue();
						if(nombre.getText().length() != 0) {
							String nom = nombre.getText();
							if(costoMin.getText().isEmpty() && costoMax.getText().isEmpty()) {
								ArrayList<Insumo> todos = principal.arbol.preOrden();
								for(int i=0; i<todos.size(); i++) {
									if(todos.get(i).getNombre().contains(nom) && todos.get(i).calcularTotal() <= Smax && todos.get(i).calcularTotal() >= Smin) {
										array.add(todos.get(i));
									}
								}
							}
							else if(!costoMin.getText().isEmpty() && costoMax.getText().isEmpty()) {
								float Cmin =  Float.valueOf(costoMin.getText().trim()).floatValue();
								ArrayList<Insumo> todos = principal.arbol.preOrden();
								for(int i=0; i<todos.size(); i++) {
									if(todos.get(i).getNombre().contains(nom) && todos.get(i).getCosto() >= Cmin && todos.get(i).calcularTotal() <= Smax && todos.get(i).calcularTotal() >= Smin) {
										array.add(todos.get(i));
									}
								}
							}
							else if(!costoMax.getText().isEmpty() && costoMin.getText().isEmpty()) {
								float max =  Float.valueOf(costoMin.getText().trim()).floatValue();
								ArrayList<Insumo> todos = principal.arbol.preOrden();
								for(int i=0; i<todos.size(); i++) {
									if(todos.get(i).getNombre().contains(nom) && todos.get(i).getCosto() <= max && todos.get(i).calcularTotal() <= Smax && todos.get(i).calcularTotal() >= Smin) {
										array.add(todos.get(i));
									}
								}
							}
							else if(!costoMax.getText().isEmpty() && !costoMin.getText().isEmpty()) {
								float min =  Float.valueOf(costoMin.getText().trim()).floatValue();
								float max = Float.valueOf(costoMax.getText().trim()).floatValue();
								ArrayList<Insumo> todos = principal.arbol.preOrden();
								for(int i=0; i<todos.size(); i++) {
									if(todos.get(i).getNombre().contains(nom) && todos.get(i).getCosto() <= max && todos.get(i).getCosto() >= min && todos.get(i).calcularTotal() <= Smax && todos.get(i).calcularTotal() >= Smin) {
										array.add(todos.get(i));
									}
								}
							}
						}
						else {
							if(costoMin.getText().isEmpty() && costoMax.getText().isEmpty()) {
								ArrayList<Insumo> todos = principal.arbol.preOrden();
								for(int i=0; i<todos.size(); i++) {
									if(todos.get(i).calcularTotal() >= Smax && todos.get(i).calcularTotal() <= Smin) {
										array.add(todos.get(i));
									}
								}
							}
							else if(!costoMin.getText().isEmpty() && costoMax.getText().isEmpty()) {
								float min =  Float.valueOf(costoMin.getText().trim()).floatValue();
								ArrayList<Insumo> todos = principal.arbol.preOrden();
								for(int i=0; i<todos.size(); i++) {
									if(todos.get(i).getCosto() >= min && todos.get(i).calcularTotal() >= Smax && todos.get(i).calcularTotal() >= Smin) {
										array.add(todos.get(i));
									}
								}
							}
							else if(!costoMax.getText().isEmpty() && costoMin.getText().isEmpty()) {
								float max = Float.valueOf(costoMax.getText().trim()).floatValue();
								ArrayList<Insumo> todos = principal.arbol.preOrden();
								for(int i=0; i<todos.size(); i++) {
									if(todos.get(i).getCosto() <= max && todos.get(i).calcularTotal() >= Smax && todos.get(i).calcularTotal() >= Smin) {
										array.add(todos.get(i));
									}
								}
							}
							else if(!costoMax.getText().isEmpty() && !costoMin.getText().isEmpty()) {
								float min =  Float.valueOf(costoMin.getText().trim()).floatValue();
								float max = Float.valueOf(costoMax.getText().trim()).floatValue();
								ArrayList<Insumo> todos = principal.arbol.preOrden();
								for(int i=0; i<todos.size(); i++) {
									if(todos.get(i).getCosto() <= max && todos.get(i).getCosto() >= min && todos.get(i).calcularTotal() <= Smax && todos.get(i).calcularTotal() >= Smin) {
										array.add(todos.get(i));
									}
								}
							}	
						}
					}
				}
				costoMin.setText(null);
				costoMax.setText(null);
				id.setText(null);
				stockMin.setText(null);
				stockMax.setText(null);
				nombre.setText(null);
				//Quitamos la planta auxiliar 
				for(int i=0; i<array.size(); i++) {
					if(array.get(i).getId() == -1) array.remove(i);
				}
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
		
		stockMin = new JTextField();
		stockMin.setColumns(10);
		stockMin.setBounds(175, 180, 100, 25);
		add(stockMin);
		stockMin.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char validar=e.getKeyChar();
				if(Character.isLetter(validar)) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		
		stockMax = new JTextField();
		stockMax.setColumns(10);
		stockMax.setBounds(475, 180, 100, 25);
		add(stockMax);
		stockMax.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char validar=e.getKeyChar();
				if(Character.isLetter(validar)) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		
		idEliminar = new JTextField();
		idEliminar.setColumns(10);
		idEliminar.setBounds(175, 500, 100, 25);
		add(idEliminar);
		
		valor = new JTextField();
		valor.setColumns(10);
		valor.setBounds(175, 460, 100, 25);
		add(valor);
		buscar.setBounds(274, 225, 100, 25);
		add(buscar);
		idEliminar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char validar=e.getKeyChar();
				if(Character.isLetter(validar)) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		
		Button cancelar = new Button("Cancelar");
		cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int resp = JOptionPane.showConfirmDialog(null, "¿Esta seguro que desea cancelar?","ALERTA!",JOptionPane.YES_NO_OPTION);
				if(resp != 1) {
					setVisible(false);
				}
			}
		});
		cancelar.setBounds(500, 500, 70, 25);
		add(cancelar);
		
		Button eliminar = new Button("Eliminar");
		eliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(idEliminar.getText().length() != 0) {
					ArrayList<Insumo> lista = principal.arbol.preOrden();
					Insumo aux1 = new Insumo();
					int aux = Integer.valueOf(idEliminar.getText().trim()).intValue();
					aux1.setId(aux);
					Insumo x = principal.arbol.obtener(aux1);
					for(int j=0; j<lista.size(); j++) {
						if(lista.get(j).equals(x)) lista.remove(j);
					}
					ArbolBinarioBusqueda<Insumo> arbolNuevo = new ArbolBinarioBusqueda<Insumo>(principal.arbol.valor());
					for(int i=0; i<lista.size(); i++) {
						arbolNuevo.agregar(lista.get(i));
					}
					for(int i=0; i<principal.grafo.vertices().size(); i++) {
						for(int j=0; j<principal.grafo.vertices().get(i).getValor().getStock().size(); j++) {
							if(principal.grafo.vertices().get(i).getValor().getStock().get(j).getInsumo().getId() == x.getId())
								principal.grafo.vertices().get(i).getValor().getStock().remove(j);
						}
					}
					idEliminar.setText(null);
					principal.arbol = arbolNuevo;
				}
			}
		});
		eliminar.setBounds(304, 500, 70, 25);
		add(eliminar);
		
		Button modificar = new Button("Modificar");
		modificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Insumo aux1 = new Insumo();
				aux1.setId((int)valores[table.getSelectedRow()][0]);
				Insumo x = principal.arbol.obtener(aux1);
				if(table.getSelectedColumn() == 1) {
					principal.arbol.obtener(x).setNombre(valor.getText());
				}
				else if(table.getSelectedColumn() == 2) {
					principal.arbol.obtener(x).setDescripcion(valor.getText());
				}
				else if(table.getSelectedColumn() == 3) {
					principal.arbol.obtener(x).setUnidadDeMedida(Medida.valueOf(valor.getText()));
				}
				else if(table.getSelectedColumn() == 5) {
					float cos = Float.valueOf(valor.getText().trim()).floatValue();
					principal.arbol.obtener(x).setCosto(cos);
				}
				else if(table.getSelectedColumn() == 6) {
					float pes = Float.valueOf(valor.getText().trim()).floatValue();
					principal.arbol.obtener(x).setPeso(pes);
				}
				else if(table.getSelectedColumn() == 7) {
					principal.arbol.obtener(x).setEsRefrigerado(!principal.arbol.obtener(x).isEsRefrigerado());
				}
				valor.setText(null);
			}
		});
		modificar.setBounds(304, 460, 70, 25);
		add(modificar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 266, 600, 171);
		add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(modelo);
	}
}


