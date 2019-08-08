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

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;

public class VerCamiones extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private static final int  ancho=900, alto=571;
	
	private JTable table;
	private Object[][] valores;
	private String[] columnas;
	private DefaultTableModel modelo;
	private JTextField valor;
	
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
				principal.camiones.remove(table.getSelectedRow());
			}
		});
		eliminar.setBounds(312, 505, 70, 22);
		add(eliminar);
		
		Button modificar = new Button("Modificar");
		modificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedColumn() == 1) {
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
				else {
					System.out.println("Aca va el error");
				}
			}
		});
		modificar.setBounds(212, 505, 70, 22);
		add(modificar);
	}
}
