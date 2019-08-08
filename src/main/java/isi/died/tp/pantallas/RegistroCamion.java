package isi.died.tp.pantallas;

import java.awt.*;
import javax.swing.*;

import isi.died.tp.dominio.Camion;

import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

public class RegistroCamion extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final int ancho = 900, alto = 571;
	
	private JTextField marca;
	private JTextField modelo;
	private JTextField capacidad;
	private JTextField costoKm;
	private JTextField patente;
	private JTextField año;
	
	private int idGeneral = 0;
	
	public RegistroCamion(Principal principal) {
		setBounds(350, 0, ancho, alto);
		setBackground(new Color(139, 69, 19));
		setLayout(null);
		
		JLabel lblMarca = new JLabel("Marca:");
		lblMarca.setFont(new Font("Dialog", Font.BOLD, 12));
		lblMarca.setForeground(new Color(255, 255, 255));
		lblMarca.setBounds(50, 100, 120, 25);
		add(lblMarca);
		
		marca = new JTextField();
		marca.setBounds(200, 100, 100, 25);
		add(marca);
		marca.setColumns(10);
		
		JLabel lblModelo = new JLabel("Modelo: ");
		lblModelo.setFont(new Font("Dialog", Font.BOLD, 12));
		lblModelo.setForeground(new Color(255, 255, 255));
		lblModelo.setBounds(50, 150, 120, 25);
		add(lblModelo);
		
		modelo = new JTextField();
		modelo.setBounds(200, 150, 100, 25);
		add(modelo);
		modelo.setColumns(10);
		
		JLabel lblPatente = new JLabel("Patente: ");
		lblPatente.setFont(new Font("Dialog", Font.BOLD, 12));
		lblPatente.setForeground(new Color(255, 255, 255));
		lblPatente.setBounds(50, 200, 120, 25);
		add(lblPatente);
		
		patente = new JTextField();
		patente.setBounds(200, 200, 100, 25);
		add(patente);
		patente.setColumns(10);
		
		JLabel lblAo = new JLabel("Año:");
		lblAo.setFont(new Font("Dialog", Font.BOLD, 12));
		lblAo.setForeground(new Color(255, 255, 255));
		lblAo.setBounds(50, 250, 120, 25);
		add(lblAo);
		
		
		año = new JTextField();
		año.setBounds(200, 250, 100, 25);
		add(año);
		año.setColumns(10);
		año.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char validar=e.getKeyChar();
				if(Character.isLetter(validar)) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		
		JLabel lblCostoPorKm = new JLabel("Costo por KM:");
		lblCostoPorKm.setFont(new Font("Dialog", Font.BOLD, 12));
		lblCostoPorKm.setForeground(new Color(255, 255, 255));
		lblCostoPorKm.setBounds(50, 300, 120, 25);
		add(lblCostoPorKm);
		
		costoKm = new JTextField();
		costoKm.setBounds(200, 300, 100, 25);
		add(costoKm);
		costoKm.setColumns(10);
		costoKm.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char validar=e.getKeyChar();
				if(Character.isLetter(validar)) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		
		JLabel lblCapacidad = new JLabel("Capacidad:");
		lblCapacidad.setFont(new Font("Dialog", Font.BOLD, 12));
		lblCapacidad.setForeground(new Color(255, 255, 255));
		lblCapacidad.setBounds(50, 350, 120, 25);
		add(lblCapacidad);
		
		capacidad = new JTextField();
		capacidad.setBounds(200, 350, 100, 25);
		add(capacidad);
		capacidad.setColumns(10);
		capacidad.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char validar=e.getKeyChar();
				if(Character.isLetter(validar)) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		
		JLabel lblRegistroCamion = new JLabel("REGISTRO CAMIÓN");
		lblRegistroCamion.setForeground(new Color(255, 255, 255));
		lblRegistroCamion.setFont(new Font("Dialog", Font.BOLD, 20));
		lblRegistroCamion.setBounds(225, 25, 200, 25);
		add(lblRegistroCamion);
		

		Checkbox aptoLiq = new Checkbox("¿Apto para transportar líquidos?");
		aptoLiq.setFont(new Font("Dialog", Font.BOLD, 12));
		aptoLiq.setForeground(new Color(255, 255, 255));
		aptoLiq.setBackground(new Color(139, 69, 19));
		aptoLiq.setBounds(50, 400, 250, 25);
		add(aptoLiq);
		
		Button aceptar = new Button("Aceptar");
		aceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(marca.getText().isEmpty()||modelo.getText().isEmpty()||patente.getText().isEmpty()||año.getText().isEmpty()||costoKm.getText().isEmpty()||capacidad.getText().isEmpty())
					JOptionPane.showMessageDialog(null, "Debe completar todos los campos","¡ERROR!", JOptionPane.WARNING_MESSAGE);
				else {
					//Camion(Integer id, Integer capacidad, Integer anio, boolean aptoLiquidos, String marca, String modelo,
							//String dominio, Float costoPorKM) 
					
					String mar = marca.getText();
					String mod = modelo.getText();
					String pat = patente.getText();
					float cap = Float.valueOf(capacidad.getText().trim()).floatValue();
					int anio = Integer.valueOf(año.getText().trim()).intValue();
					float cost = Float.valueOf(costoKm.getText().trim()).floatValue();
					boolean ref = aptoLiq.getState();
					Camion c = new Camion(idGeneral,cap,anio,ref,mar,mod,pat,cost);
					principal.camiones.add(c);
					JOptionPane.showMessageDialog(null, "¡Insumo registrado con éxito!\nID Camión: "+idGeneral);
					idGeneral++;
					marca.setText(null);
					modelo.setText(null);
					patente.setText(null);
					costoKm.setText(null);
					capacidad.setText(null);
					año.setText(null);
					aptoLiq.setState(false);
					
					
				}
			}
		});
		aceptar.setBounds(450, 500, 70, 25);
		add(aceptar);
		
		Button cancelar = new Button("Cancelar");
		cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int resp = JOptionPane.showConfirmDialog(null, "¿Esta seguro que desea cancelar?","ALERTA!",JOptionPane.YES_NO_OPTION);
				if(resp != 1) {
					setVisible(false);
				}
			}
		});
		cancelar.setBounds(535, 500, 70, 25);
		add(cancelar);
		
		
	}
}