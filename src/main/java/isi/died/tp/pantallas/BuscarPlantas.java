package isi.died.tp.pantallas;

import isi.died.tp.dominio.*;
import isi.died.tp.estructuras.*;

import java.awt.Color;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BuscarPlantas extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final int ancho = 900, alto = 571;
	
	private JTable table;
	private JTextField idPlanta;
	private Planta plantaEncontrada;
	private Object[][] valores;
	private DefaultTableModel modelo;
	private String[] columnas;
	private JTextField valor;
	private JTextField puntoPedido;
	private JTextField cantidad;
	
	private int idStock=0;
	private JTextField id;
	private JTextField idSeleccionada;
	private JTextField nombreNuevo;
	
	public BuscarPlantas(Principal principal) {
		setBounds(350, 0, ancho, alto);
		setBackground(new Color(139, 69, 19));
		setLayout(null);
		
		//JLABELS
		JLabel lblBuscarPlanta = new JLabel("BUSCAR PLANTA");
		lblBuscarPlanta.setForeground(new Color(255, 255, 255));
		lblBuscarPlanta.setFont(new Font("Dialog", Font.BOLD, 20));
		lblBuscarPlanta.setBounds(225, 25, 200, 25);
		add(lblBuscarPlanta);
		
		JLabel lblValor = new JLabel("Valor:");
		lblValor.setFont(new Font("Dialog", Font.BOLD, 12));
		lblValor.setForeground(Color.WHITE);
		lblValor.setBounds(25, 400, 43, 20);
		add(lblValor);

		JLabel lblCantidad = new JLabel("Cantidad:");
		lblCantidad.setForeground(Color.WHITE);
		lblCantidad.setFont(new Font("Dialog", Font.BOLD, 12));
		lblCantidad.setBounds(25, 450, 63, 20);
		add(lblCantidad);
		
		JLabel lblPuntoPedido = new JLabel("Punto pedido:");
		lblPuntoPedido.setForeground(Color.WHITE);
		lblPuntoPedido.setFont(new Font("Dialog", Font.BOLD, 12));
		lblPuntoPedido.setBounds(146, 450, 80, 20);
		add(lblPuntoPedido);
		
		JLabel lblInsumo = new JLabel("ID insumo:");
		lblInsumo.setForeground(Color.WHITE);
		lblInsumo.setFont(new Font("Dialog", Font.BOLD, 12));
		lblInsumo.setBounds(285, 450, 67, 20);
		add(lblInsumo);
		
		JLabel lblId = new JLabel("Id Planta:");
		lblId.setFont(new Font("Dialog", Font.BOLD, 12));
		lblId.setForeground(new Color(255, 255, 255));
		lblId.setBounds(50, 80, 120, 25);
		add(lblId);
		
		JLabel lblIdPlantaSeleccionada = new JLabel("Planta seleccionada:");
		lblIdPlantaSeleccionada.setForeground(Color.WHITE);
		lblIdPlantaSeleccionada.setFont(new Font("Dialog", Font.BOLD, 12));
		lblIdPlantaSeleccionada.setBounds(50, 118, 150, 25);
		add(lblIdPlantaSeleccionada);
		
		valor = new JTextField();
		valor.setBounds(90, 400, 100, 20);
		add(valor);
		valor.setColumns(10);
		
		puntoPedido = new JTextField();
		puntoPedido.setBounds(225, 451, 50, 20);
		add(puntoPedido);
		puntoPedido.setColumns(10);
		
		cantidad = new JTextField();
		cantidad.setBounds(90, 450, 50, 20);
		add(cantidad);
		cantidad.setColumns(10);

		id = new JTextField();
		id.setBounds(350, 451, 35, 20);
		add(id);
		id.setColumns(10);
		
		idPlanta = new JTextField();
		idPlanta.setBounds(150, 80, 100, 25);
		add(idPlanta);
		idPlanta.setColumns(10);
		
		idSeleccionada = new JTextField();
		idSeleccionada.setEditable(false);
		idSeleccionada.setColumns(10);
		idSeleccionada.setBounds(198, 118, 200, 25);
		add(idSeleccionada);
		
		//TABLA
		valores = new Object[1][1];
		columnas = new String[] {"ID", "Nombre", "Cantidad de Insumos", "Punto de Reposición"};
		
		modelo = new DefaultTableModel(valores, columnas) {
			private static final long serialVersionUID = 1L;
			public boolean[] columnEditables = new boolean[] {
					false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			};	
		};
		
		//BOTONES
		Button cancelar = new Button("Cancelar");
		cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int resp = JOptionPane.showConfirmDialog(null, "¿Esta seguro que desea cancelar?","ALERTA!",JOptionPane.YES_NO_OPTION);
				if(resp != 1) {
					setVisible(false);
				}
			}
		});
		
		nombreNuevo = new JTextField();
		nombreNuevo.setBounds(25, 500, 86, 25);
		add(nombreNuevo);
		nombreNuevo.setColumns(10);
		cancelar.setBounds(530, 530, 70, 25);
		add(cancelar);
		
		Button buscar = new Button("Buscar");
		buscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean encontrado = false;
				int id = Integer.valueOf(idPlanta.getText().trim()).intValue();
				for(int i=0; i < principal.grafo.vertices().size() && !encontrado; i++) {
					if(principal.grafo.vertices().get(i).getValor().getId() == id) {
						plantaEncontrada = principal.grafo.vertices().get(i).getValor();
						encontrado = true;
					}
					if(plantaEncontrada != null) {
						valores = new Object[plantaEncontrada.getStock().size()][4]; 
						for(int j=0; j<plantaEncontrada.getStock().size(); j++) {
							valores[j][0] = plantaEncontrada.getStock().get(j).getId();
							valores[j][1] = plantaEncontrada.getStock().get(j).getInsumo().getNombre();
							valores[j][2] = plantaEncontrada.getStock().get(j).getCantidad();
							valores[j][3] = plantaEncontrada.getStock().get(j).getPuntoPedido();
						}
					}
				}
				modelo.setDataVector(valores, columnas);
				idSeleccionada.setText(plantaEncontrada.getNombre()+" - ID: ["+plantaEncontrada.getId()+"]");
				if(!encontrado) {
					JOptionPane.showMessageDialog(null, "Planta no encontrada");
				}
			}
		});
		buscar.setBounds(350, 80, 120, 25);
		add(buscar);
		
		Button modificar = new Button("Modificar");
		modificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Modifico vista
				int colu = table.getSelectedColumn();
				int fil = table.getSelectedRow();
				int value = Integer.valueOf(valor.getText().trim()).intValue();
				//Modifico Stock
				if(colu == 2) {
					table.setValueAt(value, fil, colu);
					plantaEncontrada.getStock().get(fil).setCantidad(value);
				}
				else if(colu == 3) {
					table.setValueAt(value, fil, colu);
					plantaEncontrada.getStock().get(fil).setPuntoPedido(value); 	
				}
			}
		});
		modificar.setBounds(225, 400, 70, 25);
		add(modificar);
		
		Button registrar = new Button("Registrar");
		registrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(plantaEncontrada != null) {
					int aux = Integer.valueOf(id.getText().trim()).intValue();
					Insumo aux1 = new Insumo();
					aux1.setId(aux);
					Insumo i = principal.arbol.obtener(aux1);
					int punto = (int) Float.valueOf(puntoPedido.getText().trim()).floatValue();
					int cant = (int) Float.valueOf(cantidad.getText().trim()).floatValue();
					Stock s = new Stock(idStock, cant, punto, i);
					idStock++;
					plantaEncontrada.getStock().add(s);
				}
				else JOptionPane.showMessageDialog(null, "Debe buscar la planta a la que desea registar el stock");
			}
		});
		registrar.setBounds(400, 450, 70, 25);
		add(registrar);
		
		Button eliminar = new Button("Eliminar Planta");
		eliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				principal.grafo.vertices().remove(principal.grafo.getNodo(plantaEncontrada));
			}
		});
		eliminar.setBounds(266, 500, 110, 25);
		add(eliminar);
		
		Button cambiar = new Button("Cambiar Nombre");
		cambiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				plantaEncontrada.setNombre(nombreNuevo.getText());
			}
		});
		cambiar.setBounds(146, 500, 110, 25);
		add(cambiar);
		
		Button eliminarStock = new Button("Eliminar Stock");
		eliminarStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(plantaEncontrada != null) 
						plantaEncontrada.getStock().remove(table.getSelectedRow());
				}
		});
		eliminarStock.setBounds(350, 400, 100, 25);
		add(eliminarStock);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(38, 154, 553, 216);
		add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(modelo);
		
		
	}
}
