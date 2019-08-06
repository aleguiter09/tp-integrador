package isi.died.tp.pantallas;


import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import isi.died.tp.dominio.*;


public class RegistroInsumo extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final int ancho = 650, alto = 571;
	
	private JTextField descripcion;
	private JTextField costo;
	private JTextField peso;
	private JTextField densidad;
	private JTextField nombre;
	
	private int idGeneral = 0;
	
	public RegistroInsumo(Principal principal, JPanel aux) {
		setBounds(350, 0, ancho, alto);
		setBackground(new Color(139, 69, 19));
		setLayout(null);

		JLabel lblRegistroInsumo = new JLabel("REGISTRO INSUMO");
		lblRegistroInsumo.setForeground(new Color(255, 255, 255));
		lblRegistroInsumo.setFont(new Font("Dialog", Font.BOLD, 20));
		lblRegistroInsumo.setBounds(225, 25, 200, 25);
		add(lblRegistroInsumo);
		
		JLabel lblDescripcion = new JLabel("Descripción:");
		lblDescripcion.setFont(new Font("Dialog", Font.BOLD, 12));
		lblDescripcion.setForeground(new Color(255, 255, 255));
		lblDescripcion.setBounds(50, 100, 75, 25);
		add(lblDescripcion);
		
		JLabel lblUnidadDeMedida = new JLabel("Unidad de medida:");
		lblUnidadDeMedida.setForeground(Color.WHITE);
		lblUnidadDeMedida.setFont(new Font("Dialog", Font.BOLD, 12));
		lblUnidadDeMedida.setBounds(50, 225, 120, 25);
		add(lblUnidadDeMedida);
		
		JLabel lblCosto = new JLabel("Costo por unidad:");
		lblCosto.setForeground(Color.WHITE);
		lblCosto.setFont(new Font("Dialog", Font.BOLD, 12));
		lblCosto.setBounds(50, 275, 120, 25);
		add(lblCosto);
		
		JLabel lblPeso = new JLabel("Peso:");
		lblPeso.setForeground(Color.WHITE);
		lblPeso.setFont(new Font("Dialog", Font.BOLD, 12));
		lblPeso.setBounds(50, 325, 120, 25);
		add(lblPeso);
		
		JLabel lblDensidad = new JLabel("Densidad:");
		lblDensidad.setForeground(Color.WHITE);
		lblDensidad.setFont(new Font("Dialog", Font.BOLD, 12));
		lblDensidad.setBounds(50, 375, 120, 25);
		add(lblDensidad);
		
		JLabel lblNombre = new JLabel("Nombre: ");
		lblNombre.setForeground(Color.WHITE);
		lblNombre.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNombre.setBounds(50, 75, 120, 25);
		add(lblNombre);
		
		JComboBox<Medida> comboBox = new JComboBox<Medida>();
		comboBox.setModel(new DefaultComboBoxModel<Medida>(Medida.values()));
		comboBox.setBounds(300, 225, 175, 25);
		add(comboBox);
		
		descripcion = new JTextField();
		descripcion.setBounds(50, 128, 425, 80);
		add(descripcion);
		descripcion.setColumns(10);
		
		
		costo = new JTextField();
		costo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char validar=e.getKeyChar();
				if(Character.isLetter(validar)) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		costo.setBounds(300, 275, 100, 25);
		add(costo);
		costo.setColumns(10);
		
		peso = new JTextField();
		peso.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char validar=e.getKeyChar();
				if(Character.isLetter(validar)) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		peso.setBounds(300, 325, 100, 25);
		add(peso);
		peso.setColumns(10);
		
		densidad = new JTextField();
		densidad.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char validar=e.getKeyChar();
				if(Character.isLetter(validar)) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		densidad.setText("0.0");
		densidad.setBounds(300, 375, 100, 25);
		add(densidad);
		densidad.setColumns(10);
		
		nombre = new JTextField();
		nombre.setColumns(10);
		nombre.setBounds(300, 75, 100, 25);
		add(nombre);
		
		Checkbox check = new Checkbox("Insumo refrigerado");
		check.setForeground(Color.WHITE);
		check.setFont(new Font("Dialog", Font.BOLD, 12));
		check.setBounds(50, 425, 200, 25);
		add(check);
		
		Button aceptar = new Button("Aceptar");
		aceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(descripcion.getText().isEmpty()||costo.getText().isEmpty()||nombre.getText().isEmpty()||peso.getText().isEmpty())
					JOptionPane.showMessageDialog(null, "Debe completar todos los campos","¡ERROR!", JOptionPane.WARNING_MESSAGE);
				else {
						Medida med = (Medida) comboBox.getSelectedItem();
						if(densidad.getText().equals("0.0") && med.equals(Medida.LITRO)) 
							JOptionPane.showMessageDialog(null, "El insumo es Líquido, ingrese una Densidad","¡ERROR!", JOptionPane.WARNING_MESSAGE);
						else {
								String nom = nombre.getText();	
								String desc = descripcion.getText();
								float cos = Float.valueOf(costo.getText().trim()).floatValue();
								float pes = Float.valueOf(peso.getText().trim()).floatValue();
								float dens = Float.valueOf(densidad.getText().trim()).floatValue();
								boolean ref = check.getState();
								
							if(dens == 0.0) {
									Insumo insumo = new Insumo(idGeneral, nom, desc, med, ref,cos, pes);
									idGeneral++;
									principal.arbol.agregar(insumo);
									JOptionPane.showMessageDialog(null, "¡Insumo registrado con éxito! \nID Insumo: "+insumo.getId());
								}
								else {
									InsumoLiquido insumo =	new InsumoLiquido(idGeneral, nom, desc, med,ref,cos, pes, dens);
									idGeneral++;
									principal.arbol.agregar(insumo);
									JOptionPane.showMessageDialog(null, "¡Insumo registrado con éxito! \nID Insumo: "+insumo.getId());
									}
							descripcion.setText(null);
							costo.setText(null);
							peso.setText(null);
							check.setState(false);;
						} 
					}	
			}
		});
		aceptar.setBounds(450, 500, 70, 25);
		add(aceptar);
		
		Button cancelar = new Button("Cancelar");
		cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int resp = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea cancelar?","ALERTA!",JOptionPane.YES_NO_OPTION);
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
 