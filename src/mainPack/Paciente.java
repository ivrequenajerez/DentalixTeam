package mainPack;

import javax.swing.border.EmptyBorder;

import database.ConectorBBDD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Paciente extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final Font fuenteLabel = new Font("Montserrat", Font.PLAIN, 20);
	private static final Font fuenteGrande = new Font("Montserrat", Font.PLAIN, 50);
	int yPosition = 40;
	int separacionVertical = 70;
	private JTextField textField_UltimaConsulta;
	private JTextField textField_Id;
	JButton btnGuardar;
	JLabel labelPaciente = new JLabel("NOMBRE PACIENTE");
	ConectorBBDD conectorBBDDD;

	/**
	 * Create the panel.
	 */
	public Paciente() {

		// Código existente...
		setBounds(0, -1, 1179, 691);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setBackground(Color.WHITE);
		setLayout(null);
		setBackground(Color.decode("#008cce"));
		setLayout(null); // Layout nulo para posicionar manualmente los componentes

		// BOTON GUARDAR
		java.net.URL imgUrl11 = getClass().getResource("/save.png");
		Icon icon11 = new ImageIcon(imgUrl11);

		// BOTON ELIMINAR
		java.net.URL imgUrl12 = getClass().getResource("/eliminar.png");
		Icon icon12 = new ImageIcon(imgUrl12);

		// BOTON VOLVER
		java.net.URL imgUrl13 = getClass().getResource("/volverIcono.png");
		Icon icon13 = new ImageIcon(imgUrl13);

		// Panel Principal

		JScrollPane editarPanel = new JScrollPane();
		editarPanel.setBounds(0, -1, 1179, 691);
		this.add(editarPanel);

		// Panel donde están los labels
		JPanel panel = new JPanel(); // PANEL DONDE ESTAN LOS LABELS
		editarPanel.setViewportView(panel);
		panel.setBackground(Color.decode("#FFFFFF"));

		// Cargar la imagen desde la carpeta de recursos
		String rutaImagen = "/paciente.png"; // Ajusta la ruta según la ubicación de tu recurso
		java.net.URL urlImagen = getClass().getResource(rutaImagen);
		ImageIcon imagen1 = new ImageIcon(urlImagen);
		panel.setLayout(null);

		// Añadir la imagen al panel
		JLabel labelImagen = new JLabel(imagen1);
		labelImagen.setBounds(0, 124, 544, 512);
		panel.add(labelImagen);

		// L NOMBRE
		JLabel label_Nombre = new JLabel("Nombre:");
		label_Nombre.setBounds(591, 92, 85, 65);
		panel.add(label_Nombre);

		label_Nombre.setFont(fuenteLabel);
		label_Nombre.setBackground(Color.decode("#008cce"));

		// APELLIDOS

		JLabel label_Apellidos = new JLabel("Apellidos:");
		label_Apellidos.setBounds(591, 168, 85, 65);
		panel.add(label_Apellidos);

		label_Apellidos.setFont(fuenteLabel);

		// TELEFONO

		JLabel label_Telefono = new JLabel("Teléfono:");
		label_Telefono.setBounds(591, 297, 82, 65);
		panel.add(label_Telefono);

		label_Telefono.setFont(fuenteLabel);

		// DIRECCION

		JLabel label_Direccion = new JLabel("Dirección:");
		label_Direccion.setBounds(583, 229, 93, 65);
		panel.add(label_Direccion);

		label_Direccion.setFont(fuenteLabel);

		// SALARIO

		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(636, 449, 40, 65);
		panel.add(lblId);

		lblId.setFont(fuenteLabel);

		// EMAIL

		JLabel lblUltimaConsulta = new JLabel("Ultima Consulta:");
		lblUltimaConsulta.setBounds(532, 373, 168, 65);
		panel.add(lblUltimaConsulta);

		lblUltimaConsulta.setFont(fuenteLabel);

		String rutaImagen2 = "/guardarIcono.png"; // Ajusta la ruta según la ubicación de tu recurso
		java.net.URL urlImagen2 = getClass().getResource(rutaImagen2);

		// TextFields separados equitativamente

		JTextField textField_nombre = new JTextField();
		textField_nombre.setBounds(673, yPosition + separacionVertical * 1, 379, 38);
		panel.add(textField_nombre);

		JTextField textField_apellidos = new JTextField();
		textField_apellidos.setBounds(673, yPosition + separacionVertical * 2, 379, 38);
		panel.add(textField_apellidos);

		JTextField textField_direccion = new JTextField();
		textField_direccion.setBounds(673, yPosition + separacionVertical * 3, 379, 38);
		panel.add(textField_direccion);

		JTextField textField_tlf = new JTextField();
		textField_tlf.setBounds(673, yPosition + separacionVertical * 4, 379, 38);
		textField_tlf.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (c == KeyEvent.VK_BACK_SPACE) {
					return;
				}
				if (!Character.isDigit(c)) {
					e.consume();
					JOptionPane.showMessageDialog(null, "Solo se pueden introducir números", "Advertencia",
							JOptionPane.WARNING_MESSAGE);
				}

			}
		});

		panel.add(textField_tlf);

		textField_UltimaConsulta = new JTextField();
		textField_UltimaConsulta.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (c == KeyEvent.VK_BACK_SPACE) {
					return;
				}

				if (!Character.isDigit(c) && c != ':') {
					e.consume();
					JOptionPane.showMessageDialog(null, "Solo se pueden introducir números y ':'", "Advertencia",
							JOptionPane.WARNING_MESSAGE);

				}

			}
		});
		textField_UltimaConsulta.setBounds(673, yPosition + separacionVertical * 5, 379, 38);
		panel.add(textField_UltimaConsulta);

		textField_Id = new JTextField();
		textField_Id.setBounds(673, yPosition + separacionVertical * 6, 379, 38);
		panel.add(textField_Id);

		// ERRORES

		textField_Id.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (c == KeyEvent.VK_BACK_SPACE) {
					return;
				}
				if (!Character.isDigit(c)) {
					e.consume();
					JOptionPane.showMessageDialog(null, "Solo se pueden introducir números", "Advertencia",
							JOptionPane.WARNING_MESSAGE);

				}

			}
		});

		labelPaciente.setBounds(53, 43, 533, 47);
		panel.add(labelPaciente);
		labelPaciente.setFont(fuenteGrande);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(591, 593, 592, 75);
		panel_1.setForeground(Color.BLACK);
		panel_1.setBorder(null);
		panel_1.setBackground(new Color(70, 130, 180));
		panel.add(panel_1);
		panel_1.setLayout(null);
		panel_1.setBackground(Color.decode("#FFFFFF"));

		// BOTÓN GUARDAR
		btnGuardar = new JButton(icon11);
		btnGuardar.setBounds(10, 11, 78, 54);
		panel_1.add(btnGuardar);
		btnGuardar.setPreferredSize(new Dimension(icon11.getIconWidth(), icon11.getIconHeight()));
		btnGuardar.setContentAreaFilled(false);
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Obtener los valores de los campos
				String nombre = textField_nombre.getText();
				String apellidos = textField_apellidos.getText();
				String direccion = textField_direccion.getText();
				String telefono = textField_tlf.getText();
				String ultimaConsulta = textField_UltimaConsulta.getText();
				String id = textField_Id.getText();

				if (nombre.isEmpty() || apellidos.isEmpty() || direccion.isEmpty() || telefono.isEmpty()
						|| ultimaConsulta.isEmpty() || id.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Rellena todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
				} else {
					// Crear una instancia de ConectorBBDD (asegúrate de que sea accesible desde
					// esta clase)
					ConectorBBDD conectorBBDD = new ConectorBBDD();

					// Llamar al método para insertar paciente en la base de datos
					((ConectorBBDD) conectorBBDD).insertarPaciente(nombre, apellidos, direccion, telefono,
							ultimaConsulta, id);

					// Luego, si es necesario, puedes actualizar la tabla o realizar otras acciones
					// después de la inserción.
				}
			}
		});
		
		// BOTÓN VOLVER
		JButton btnVolver = new JButton(icon13);
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaPrincipal ventanaPrincipal = new VentanaPrincipal();
				ventanaPrincipal.setVisible(true);

			}
		});
		btnVolver.setBounds(391, 0, 164, 80);
		panel_1.add(btnVolver);
		btnVolver.setPreferredSize(new Dimension(96, 96));
		btnVolver.setContentAreaFilled(false);

		// BOTÓN ELIMINAR
		JButton btnEliminar = new JButton(icon12);
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				textField_nombre.setText("");
				textField_apellidos.setText("");
				textField_direccion.setText("");
				textField_tlf.setText("");
				textField_UltimaConsulta.setText("");
				textField_Id.setText("");

			}
		});
		btnEliminar.setBounds(192, 0, 164, 80);
		panel_1.add(btnEliminar);
		btnEliminar.setPreferredSize(new Dimension(96, 96));
		btnEliminar.setContentAreaFilled(false);

	}

	public void setDefaultButton() {
		if (getRootPane() != null) {
			getRootPane().setDefaultButton(btnGuardar);
		}
	}

	public JLabel getLabelPaciente() {
		return labelPaciente;
	}
}
