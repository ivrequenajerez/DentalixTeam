package mainPack;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Dimension;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import java.awt.Font;
import javax.swing.JOptionPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import java.awt.Toolkit;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.KeyStroke;
import java.awt.event.InputEvent;

public class Cita extends JPanel {

	// Variables
	private static final long serialVersionUID = 1L;
	private static final Font fuenteLabel = new Font("Montserrat", Font.PLAIN, 20);
	int yPosition = -40;
	int separacionVertical = 95;
	int contadorClics;
	ImageIcon icono = new ImageIcon("images/nombre_de_tu_imagen.png");
	JLabel labelCita;
	
	/**
	 * Create the panel.
	 */
	public Cita() {

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

		JPanel panel = new JPanel(); // PANEL DONDE ESTAN LOS LABELS

		editarPanel.setViewportView(panel);
		panel.setBackground(Color.decode("#008cce"));

		// Cargar la imagen desde la carpeta de recursos
		String rutaImagen = "/cita.png"; // Ajusta la ruta según la ubicación de tu recurso
		java.net.URL urlImagen = getClass().getResource(rutaImagen);
		ImageIcon imagen1 = new ImageIcon(urlImagen);
		panel.setLayout(null);
		panel.setLayout(null);

		// Añadir la imagen al panel
		JLabel labelImagen = new JLabel(imagen1);

		labelImagen.setBounds(-226, -110, 1012, 865);
		panel.add(labelImagen);

		// L NOMBRE
		JLabel label_IDCita = new JLabel("ID Cita:");
		label_IDCita.setBounds(605, 40, 78, 65);
		panel.add(label_IDCita);

		label_IDCita.setFont(fuenteLabel);
		label_IDCita.setBackground(Color.decode("#008cce"));

		// APELLIDOS

		JLabel label_IDDoctor = new JLabel("ID Doctor:");
		label_IDDoctor.setBounds(580, 230, 97, 65);
		panel.add(label_IDDoctor);

		label_IDDoctor.setFont(fuenteLabel);

		// TELEFONO

		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setBounds(612, 418, 65, 65);
		panel.add(lblFecha);

		lblFecha.setFont(fuenteLabel);

		// DIRECCION

		JLabel label_Motivo = new JLabel("Motivo:");
		label_Motivo.setBounds(609, 323, 74, 65);
		panel.add(label_Motivo);

		label_Motivo.setFont(fuenteLabel);

		// EMAIL

		JLabel lblHora = new JLabel("Hora:");
		lblHora.setBounds(623, 513, 60, 65);
		panel.add(lblHora);

		lblHora.setFont(fuenteLabel);

		String rutaImagen2 = "/guardarIcono.png"; // Ajusta la ruta según la ubicación de tu recurso
		java.net.URL urlImagen2 = getClass().getResource(rutaImagen2);
		JTextField textField_IDCita = new JTextField();
		textField_IDCita.addKeyListener(new KeyAdapter() {
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
		textField_IDCita.setBounds(673, 55, 379, 38);
		panel.add(textField_IDCita);

		JTextField textField_IDPaciente = new JTextField();
		textField_IDPaciente.addKeyListener(new KeyAdapter() {
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
		textField_IDPaciente.setBounds(673, 150, 379, 38);
		panel.add(textField_IDPaciente);

		JTextField textField_IDDoctor = new JTextField();
		textField_IDDoctor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (c == KeyEvent.VK_BACK_SPACE) {
					return;
				}

				if (!Character.isDigit(c) && c != '-') {
					e.consume();
					JOptionPane.showMessageDialog(null, "Solo se pueden introducir números", "Advertencia",
							JOptionPane.WARNING_MESSAGE);
				}

			}
		});
		textField_IDDoctor.setBounds(673, 245, 379, 38);
		panel.add(textField_IDDoctor);

		JTextField textField_Motivo = new JTextField();
		textField_Motivo.setBounds(673, 340, 379, 38);
		panel.add(textField_Motivo);

		JTextField textField_Fecha = new JTextField();
		textField_Fecha.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {

				char c = e.getKeyChar();
				if (c == KeyEvent.VK_BACK_SPACE) {
					return;
				}
				if (!Character.isDigit(c) && c != '-') {
					e.consume();
					JOptionPane.showMessageDialog(null, "El formato de la fecha es AÑO-MES-DÍA", "Advertencia",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		textField_Fecha.setBounds(673, 435, 379, 38);
		panel.add(textField_Fecha);

		JTextField textField_Hora = new JTextField();
		textField_Hora.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (c == KeyEvent.VK_BACK_SPACE) {
					return;
				}
				if (!Character.isDigit(c) && c != ':') {
					e.consume();
					JOptionPane.showMessageDialog(null, "Solo se pueden introducir números y ':' ", "Advertencia",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		textField_Hora.setBounds(673, 530, 379, 38);
		panel.add(textField_Hora);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(591, 593, 592, 75);
		panel_1.setBackground(new Color(70, 130, 180));
		panel.add(panel_1);
		panel_1.setLayout(null);
		panel_1.setBackground(Color.decode("#008cce"));

		// BOTON GUARDAR
		// BOTON GUARDAR
		// BOTON GUARDAR
		// BOTON GUARDAR
		JButton btnGuardar = new JButton(icon11);
		btnGuardar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try {
		            String idCita = textField_IDCita.getText();
		            String idPaciente = textField_IDPaciente.getText();
		            String idDoctor = textField_IDDoctor.getText();
		            String motivo = textField_Motivo.getText();
		            String fecha = textField_Fecha.getText();
		            String hora = textField_Hora.getText();

		            // Verificar que todos los campos estén llenos
		            if (idCita.isEmpty() || idPaciente.isEmpty() || idDoctor.isEmpty() || motivo.isEmpty()
		                    || fecha.isEmpty() || hora.isEmpty()) {
		                // Mostrar un mensaje de error si algún campo está vacío
		                JOptionPane.showMessageDialog(null, "Rellena todos los campos", "Error",
		                        JOptionPane.ERROR_MESSAGE);
		                return; // Salir del método para evitar el segundo mensaje de error
		            }

		            // Crear una instancia de ConectorBBDD (asegúrate de que sea accesible desde esta
					
					
					
					
					// Realizar la lógica de guardar aquí

				} catch (Exception ex) {
					// Capturar cualquier excepción no manejada
					JOptionPane.showMessageDialog(null, "Error al procesar la información", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		// Agrega el botón al panel_1
		btnGuardar.setBounds(28, 11, 76, 59);
		panel_1.add(btnGuardar);
		btnGuardar.setPreferredSize(new Dimension(icon11.getIconWidth(), icon11.getIconHeight()));
		btnGuardar.setContentAreaFilled(false);

		// Agrega el ActionListener para la tecla "Enter" en el JFrame
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
					// Simular el clic en el botón al presionar "Enter"
					btnGuardar.doClick();
				}
			}
		});

		// Agrega el botón al panel_1
		btnGuardar.setBounds(28, 11, 76, 59);
		panel_1.add(btnGuardar);
		btnGuardar.setPreferredSize(new Dimension(icon11.getIconWidth(), icon11.getIconHeight()));
		btnGuardar.setContentAreaFilled(false);

		// Agrega el ActionListener para la tecla "Enter" en el JFrame
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
					// Simular el clic en el botón al presionar "Enter"
					btnGuardar.doClick();
				}
			}
		});

		// BOTON VOLVER
		JButton btnVolver = new JButton(icon13);
		btnVolver.setBounds(391, 0, 164, 80);
		panel_1.add(btnVolver);
		btnVolver.setPreferredSize(new Dimension(96, 96));
		btnVolver.setContentAreaFilled(false);

		// BOTÓN ELIMINAR
		JButton btnEliminar = new JButton(icon12);
		btnEliminar.setBounds(192, 0, 164, 80);
		panel_1.add(btnEliminar);
		btnEliminar.setPreferredSize(new Dimension(96, 96));
		btnEliminar.setContentAreaFilled(false);

		JLabel label_IDPaciente = new JLabel("ID Paciente:");
		label_IDPaciente.setBounds(562, 135, 115, 65);
		label_IDPaciente.setFont(new Font("Dialog", Font.PLAIN, 20));
		panel.add(label_IDPaciente);

		java.net.URL imgUrlColor = getClass().getResource("/cambiarColor.png");
		Icon iconColor = new ImageIcon(imgUrlColor);
		JButton btnCambiaColor = new JButton(iconColor);
		btnCambiaColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Incrementar el contador de clics
				contadorClics++;

				// Determinar el color según el número de clics
				Color color;
				switch (contadorClics % 3) {
				case 1:
					color = Color.GREEN;
					break;
				case 2:
					color = Color.RED;
					break;
				case 0:
					color = Color.decode("#008cce");
					;
					break;
				default:
					color = Color.WHITE; // Color por defecto
					break;
				}

				// Cambiar el color del panel
				panel.setBackground(color);
				panel.setBackground(color);
				// buttonPanel.setBackground(color);
				panel_1.setBackground(color);

			}
		});

		btnCambiaColor.setContentAreaFilled(false);

		btnCambiaColor.setBounds(1063, 11, 89, 38);
		panel.add(btnCambiaColor);

	}
	
	public JLabel getLabelCita() {
		
		return labelCita;
	}

}
