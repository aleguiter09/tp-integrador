package isi.died.tp.pantallas;

import isi.died.tp.estructuras.*;
import isi.died.tp.dominio.*;


import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegistroPlanta extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final int ancho = 650, alto = 571;
	private int id_general=0;
	
	private JTextField nombre;
	
	public RegistroPlanta(Principal principal, JPanel aux) {
		setBounds(350, 0, ancho, alto);
		setBackground(new Color(139, 69, 19));
		setLayout(null);
		
		JLabel lblRegistroPlanta = new JLabel("REGISTRO PLANTA");
		lblRegistroPlanta.setFont(new Font("Dialog", Font.BOLD, 20));
		lblRegistroPlanta.setForeground(new Color(255, 255, 255));
		lblRegistroPlanta.setBounds(225, 25, 200, 25);
		add(lblRegistroPlanta);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setForeground(new Color(255, 255, 255));
		lblNombre.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNombre.setBounds(50, 100, 120, 25);
		add(lblNombre);
		
		nombre = new JTextField();
		nombre.setBounds(200, 100, 150, 25);
		add(nombre);
		nombre.setColumns(10);
		
		Button aceptar = new Button("Aceptar");
		aceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!nombre.getText().isEmpty()) {
					Planta p = new Planta(id_general,nombre.getText());
					principal.listaPlantas.add(p);
					principal.grafo.addNodo(p); 
					JOptionPane.showMessageDialog(null, "¡Planta registrada con éxito!\nID Planta: "+p.getId());
					id_general++;
					nombre.setText(null);
				} else {
					JOptionPane.showMessageDialog(null, "Debe completar el campo nombre","¡ERROR!", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		aceptar.setBounds(450, 500, 70, 25);
		add(aceptar);
		
		Button cancelar = new Button("Cancelar");
		cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int resp = JOptionPane.showConfirmDialog(null, "Â¿Esta seguro que desea cancelar?","ALERTA!",JOptionPane.YES_NO_OPTION);
				if(resp != 1) {
					setVisible(false);
					aux.setVisible(true);
				}
			}
		});
		cancelar.setBounds(535, 500, 70, 25);
		add(cancelar);
	}
}
