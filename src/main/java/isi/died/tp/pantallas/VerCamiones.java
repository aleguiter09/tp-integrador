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

public class VerCamiones extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private static final int  ancho=650, alto=571;
	
	private JTable table;
	private Object[][] valores;
	private String[] columnas;
	private DefaultTableModel modelo;
	
	public VerCamiones(Principal principal, JPanel aux) {
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
	
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 76, 604, 373);
		add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(modelo);
		
		Button button = new Button("Cerrar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int resp = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea cancelar?","ALERTA!",JOptionPane.YES_NO_OPTION);
				if(resp != 1) {
					setVisible(false);
					aux.setVisible(true);
				}
			}
		});
		button.setBounds(526, 505, 70, 22);
		add(button);
		
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
		
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.getColumnModel().getColumn(3).setPreferredWidth(100);
		
	}
}
