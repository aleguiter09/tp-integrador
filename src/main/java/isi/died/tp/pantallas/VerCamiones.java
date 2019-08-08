package isi.died.tp.pantallas;

import java.awt.Button;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import isi.died.tp.dominio.*;
import isi.died.tp.dominio.Planta;
import isi.died.tp.estructuras.Vertice;

import javax.swing.JTextField;

public class VerCamiones extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private static final int  ancho=1000, alto=571;
	
	private JTable table;
	private Object[][] valores, valoresTablaInsumos;
	private String[] columnas, columnasTablaInsumos;
	private DefaultTableModel modelo, modeloTablaInsumos;
	private JTextField valor;
	private JTable table_1;
	
	public VerCamiones(Principal principal) {
		setBounds(350, 0, ancho, alto);
		setBackground(new Color(139, 69, 19));
		setLayout(null);
		
		JLabel lblVerCamiones = new JLabel("VER CAMIONES");
		lblVerCamiones.setForeground(new Color(255, 255, 255));
		lblVerCamiones.setFont(new Font("Dialog", Font.BOLD, 20));
		lblVerCamiones.setBounds(225, 25, 200, 25);
		add(lblVerCamiones);
		
		valores = new Object[principal.camiones.size()][8];
		columnas = new String[] {"ID", "Marca", "Modelo", "Patente", "A\u00F1o", "Costo por KM", "Capacidad", "Apto L\u00EDquidos"};
		
		for(int i=0; i<principal.camiones.size(); i++) {
			valores[i][0] = principal.camiones.get(i).getId();
			valores[i][1] = principal.camiones.get(i).getMarca();
			valores[i][2] = principal.camiones.get(i).getModelo();
			valores[i][3] = principal.camiones.get(i).getPatente();
			valores[i][4] = principal.camiones.get(i).getAnio();
			valores[i][5] = principal.camiones.get(i).getCostoPorKM();
			valores[i][6] = principal.camiones.get(i).getCapacidad();
			valores[i][7] = principal.camiones.get(i).isAptoLiquidos();
		}
		modelo = new DefaultTableModel(valores, columnas) {
			private static final long serialVersionUID = 1L;
			public boolean[] columnEditables = new boolean[] {
					false, false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			};
		};
		
		modelo.setDataVector(valores,columnas);
		
		JLabel lblValorAModificar = new JLabel("Valor a modificar:");
		lblValorAModificar.setForeground(Color.WHITE);
		lblValorAModificar.setFont(new Font("Dialog", Font.BOLD, 12));
		lblValorAModificar.setBounds(30, 465, 115, 25);
		add(lblValorAModificar);
	
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 76, 604, 364);
		add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(modelo);
		
		Button cerrar = new Button("Cerrar");
		cerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int resp = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea cancelar?","ALERTA!",JOptionPane.YES_NO_OPTION);
				if(resp != 1) {
					setVisible(false);
				}
			}
		});
		
		valor = new JTextField();
		valor.setBounds(196, 465, 86, 25);
		add(valor);
		valor.setColumns(10);
		cerrar.setBounds(526, 505, 70, 22);
		add(cerrar);
		
		Button refresh = new Button("Refrescar");
		refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				valores = new Object[principal.camiones.size()][8]; 
				for(int i=0; i<principal.camiones.size(); i++) {
					valores[i][0] = principal.camiones.get(i).getId();
					valores[i][1] = principal.camiones.get(i).getMarca();
					valores[i][2] = principal.camiones.get(i).getModelo();
					valores[i][3] = principal.camiones.get(i).getPatente();
					valores[i][4] = principal.camiones.get(i).getAnio();
					valores[i][5] = principal.camiones.get(i).getCostoPorKM();
					valores[i][6] = principal.camiones.get(i).getCapacidad();
					valores[i][7] = principal.camiones.get(i).isAptoLiquidos();
				}
				modelo.setDataVector(valores, columnas);
			}
		});
		refresh.setBounds(413, 505, 70, 22);
		add(refresh);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(modelo);
		
		Button eliminar = new Button("Eliminar");
		eliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow() == -1) JOptionPane.showMessageDialog(null, "Por favor, seleccione un camión a eliminar.","¡ERROR!", JOptionPane.WARNING_MESSAGE);
				else {if(principal.camiones.remove(table.getSelectedRow()) != null) JOptionPane.showMessageDialog(null, "¡Camión eliminado con éxito!\nPor favor, refresque la tabla.");
				else JOptionPane.showMessageDialog(null, "No se pudo eliminar el Camión \nPor favor, reintente la operación.","¡ERROR!", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		eliminar.setBounds(312, 505, 70, 22);
		add(eliminar);
		
		Button modificar = new Button("Modificar");
		modificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(valor.getText().isEmpty()) JOptionPane.showMessageDialog(null, "Ingrese un valor a modificar","¡ERROR!", JOptionPane.WARNING_MESSAGE);
				else {if(table.getSelectedColumn() == 1) {
					principal.camiones.get(table.getSelectedRow()).setMarca(valor.getText());
				}
				else if(table.getSelectedColumn() == 2) {
					principal.camiones.get(table.getSelectedRow()).setModelo(valor.getText());
				}
				else if(table.getSelectedColumn() == 3) {
					principal.camiones.get(table.getSelectedRow()).setDominio(valor.getText());
				}
				else if(table.getSelectedColumn() == 4) {
					int aux = Integer.valueOf(valor.getText().trim()).intValue();
					principal.camiones.get(table.getSelectedRow()).setAnio(aux);		
				}
				else if(table.getSelectedColumn() == 5) {
					float cos = Float.valueOf(valor.getText().trim()).floatValue();
					principal.camiones.get(table.getSelectedRow()).setCostoPorKM(cos);
				}
				else if(table.getSelectedColumn() == 6) {
					float cap = Float.valueOf(valor.getText().trim()).floatValue();
					principal.camiones.get(table.getSelectedRow()).setCapacidad(cap);
				}
				else if(table.getSelectedColumn() == 7) {
					principal.camiones.get(table.getSelectedRow()).setAptoLiquidos(!principal.camiones.get(table.getSelectedRow()).isAptoLiquidos());
				}
				else { if(table.getSelectedColumn() == -1) JOptionPane.showMessageDialog(null, "Por favor, seleccione un campo a modificar.","¡ERROR!", JOptionPane.WARNING_MESSAGE);
				}
			}
		}
		});
		modificar.setBounds(212, 505, 70, 22);
		add(modificar);
		
		JLabel lblInsumosFaltantes = new JLabel("INSUMOS FALTANTES");
		lblInsumosFaltantes.setFont(new Font("Dialog", Font.BOLD, 12));
		lblInsumosFaltantes.setForeground(Color.WHITE);
		lblInsumosFaltantes.setBounds(759, 96, 134, 14);
		add(lblInsumosFaltantes);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(664, 121, 307, 274);
		add(scrollPane_1);
	
		ArrayList<Stock> InsumosFaltantes = new ArrayList<>();
		for(int i=0; i<principal.grafo.vertices().size(); i++) {
			InsumosFaltantes.addAll(insumosFaltantesPlanta(principal.grafo.vertices().get(i).getValor()));
		}
		
		valoresTablaInsumos = new Object[InsumosFaltantes.size()-2][4];
		columnasTablaInsumos = new String[] {"ID Planta","ID Insumo","Nombre", "Cantidad Faltante"};

		modeloTablaInsumos = new DefaultTableModel(valores, columnas) {
			private static final long serialVersionUID = 1L;
			public boolean[] columnEditables = new boolean[] {
					false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			};
		};
		
		ArrayList<Vertice<Planta>> vert = new ArrayList<Vertice<Planta>>();
		for(Vertice<Planta> p: principal.grafo.vertices())
			if(p.getValor().getId() != -1 && p.getValor().getId() !=-2) vert.add(p);
		
		
		for(int i=0; i<vert.size(); i++) {
			InsumosFaltantes.clear();
			InsumosFaltantes.addAll(insumosFaltantesPlanta(vert.get(i).getValor()));
			//if(principal.grafo.vertices().get(i).getValor().getId() !=-1  ME ESCUCHAN)
			valoresTablaInsumos[i][0] = vert.get(i).getValor().getId();
			for(int j=0; j<InsumosFaltantes.size(); j++) {
				valoresTablaInsumos[i+j][1] = InsumosFaltantes.get(j).getInsumo().getId();
				valoresTablaInsumos[i+j][2] = InsumosFaltantes.get(j).getInsumo().getNombre();
				valoresTablaInsumos[i+j][3] = InsumosFaltantes.get(j).getPuntoPedido() - InsumosFaltantes.get(j).getCantidad();
			}
		}
		
		for(int i=0; i<vert.size(); i++) 			
			InsumosFaltantes.addAll(insumosFaltantesPlanta(vert.get(i).getValor()));

		modeloTablaInsumos.setDataVector(valoresTablaInsumos, columnasTablaInsumos);
			 
		Button button = new Button("Generar Solución");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(table.getSelectedRow() == -1) JOptionPane.showMessageDialog(null, "Seleccione un camión de la lista","¡ERROR!", JOptionPane.WARNING_MESSAGE); 
				else {
					Camion elegido = principal.camiones.get(table.getSelectedRow());
				}
			}
		});
		button.setBounds(865, 414, 106, 22);
		add(button);
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		table_1.setModel(modeloTablaInsumos);
	}
	
	public ArrayList<Stock> insumosFaltantesPlanta(Planta p){
		ArrayList<Stock> stock = new ArrayList<>();	
		if(p.getId() != -1 && p.getId() != -2) {
			for(Stock s: p.getStock()) {
					if(s.getCantidad() < s.getPuntoPedido()) stock.add(s);
			}
		}
		return stock;
	}
}


