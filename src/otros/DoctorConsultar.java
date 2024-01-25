package otros;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Dimension;

import javax.print.DocFlavor.URL;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.JTextField;
import java.awt.Label;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPopupMenu;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JMenuBar;
import javax.swing.KeyStroke;
import java.awt.event.InputEvent;

public class DoctorConsultar extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static final Font fuenteLabel = new Font("Montserrat", Font.PLAIN, 20);
	private static final Font fuenteGrande = new Font("Montserrat", Font.PLAIN, 50);
	int yPosition = 30;
	int separacionVertical = 70;

	private JTextField textField_email;
	private JTextField textField_Salario;
	private JTextField textField_especialidad;
	private JTextField textField_id;
	private JOptionPane joptionPane;
	public JLabel labelDoctor = new JLabel("DR.REQUENA");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					DoctorConsultar frame = new DoctorConsultar();
					frame.setVisible(true);
					frame.setResizable(false);
					/*
					 * frame.setIconImage(Toolkit.getDefaultToolkit().getImage(
					 * pantallaInicial.class.getResource("/logo.png"))); frame.setVisible(true);
					 */
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	/**
	 * Create the frame.
	 */

	public DoctorConsultar() {

		// Dimensiones
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 748);
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("Menú");
		menuBar.add(mnNewMenu);

		JMenu mnNewMenu_1 = new JMenu("Clases");
		mnNewMenu.add(mnNewMenu_1);

		JMenuItem mntmNewMenuItem = new JMenuItem("Paciente");
		mntmNewMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0));
		mnNewMenu_1.add(mntmNewMenuItem);

		mntmNewMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Crear una instancia de la ventana del paciente y mostrarla
				PacienteCRUD ventanaPaciente = new PacienteCRUD();
				ventanaPaciente.setVisible(true);

			}
		});
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Cita");
		mntmNewMenuItem_2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0));
		mnNewMenu_1.add(mntmNewMenuItem_2);
		mnNewMenu.add(mnNewMenu_1);

		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Crear una instancia de la ventana del paciente y mostrarla
				VentanaCitaCRUD ventanaCitaCRUD = new VentanaCitaCRUD();
				ventanaCitaCRUD.setVisible(true);

			}
		});

		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Material");
		mnNewMenu_1.add(mntmNewMenuItem_3);

		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Factura");
		mnNewMenu_1.add(mntmNewMenuItem_4);

		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Pedido");
		mnNewMenu_1.add(mntmNewMenuItem_5);

		JMenuItem mntmNewMenuItem_6 = new JMenuItem("Stock");
		mnNewMenu_1.add(mntmNewMenuItem_6);

		JMenuItem mntmNewMenuItem_7 = new JMenuItem("Tratamiento");
		mnNewMenu_1.add(mntmNewMenuItem_7);

		JMenuItem mntmNewMenuItem_8 = new JMenuItem("Especialistas");
		mnNewMenu_1.add(mntmNewMenuItem_8);

		JMenuItem mntmNewMenuItem_9 = new JMenuItem("Usuarios");
		mnNewMenu_1.add(mntmNewMenuItem_9);

		JMenu mnNewMenu_2 = new JMenu("Opciones");
		mnNewMenu.add(mnNewMenu_2);

		JMenuItem mntmNewMenuItem_10 = new JMenuItem("Guardar");
		mntmNewMenuItem_10.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0));
		mnNewMenu_2.add(mntmNewMenuItem_10);

		JMenuItem mntmNewMenuItem_11 = new JMenuItem("Borrar");
		mntmNewMenuItem_11.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE, InputEvent.SHIFT_DOWN_MASK));
		mnNewMenu_2.add(mntmNewMenuItem_11);

		JMenuItem mntmNewMenuItem_12 = new JMenuItem("Volver");
		mntmNewMenuItem_12.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_DOWN_MASK));
		mnNewMenu_2.add(mntmNewMenuItem_12);

		// Estilos del JPanel
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.WHITE);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(Color.decode("#FFFFFF"));

// Código de Ventana Principal...

		// Logo Fondo azul
		JLabel logoBlanco = new JLabel("");
		logoBlanco.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(logoBlanco);

		ImageIcon imagen = new ImageIcon(getClass().getResource("/logoAzul.png"));
		int ancho = imagen.getIconWidth();
		int alto = imagen.getIconHeight();
		logoBlanco.setBounds(-11, 0, 123, 107);

		Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT));
		logoBlanco.setIcon(icono);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 101, 100, 590);
		contentPane.add(scrollPane);

		// PANEL CON LOS BOTONES DEL MENU

		JPanel buttonPanel = new JPanel();
		scrollPane.setViewportView(buttonPanel);
		buttonPanel.setBackground(Color.decode("#FFFFFF"));

		JPanel panel = new JPanel(); // PANEL DONDE ESTAN LOS LABELS

		// BOTONES

		// Botón 1
		java.net.URL imgUrl = getClass().getResource("/pacientesIcono.png");
		Icon icon = new ImageIcon(imgUrl);
		buttonPanel.setLayout(null);
		JButton button1 = new JButton(icon);
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PacienteCRUD pacienteCRUD = new PacienteCRUD();
				pacienteCRUD.setVisible(true);
			}
		});
		button1.setBounds(0, 18, 98, 40);
		button1.setPreferredSize(new Dimension(icon.getIconWidth(), icon.getIconHeight()));
		button1.setBackground(Color.WHITE);
		buttonPanel.add(button1);
		button1.setContentAreaFilled(false);

		// Botón 2
		java.net.URL imgUrl2 = getClass().getResource("/doctoresIcono.png");
		Icon icon2 = new ImageIcon(imgUrl2);
		JButton button2 = new JButton(icon2);
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DoctorConsultar doctorConsultar = new DoctorConsultar();
				doctorConsultar.setVisible(true);
			}
		});
		button2.setBounds(0, 77, 98, 40);
		button2.setPreferredSize(new Dimension(icon2.getIconWidth(), icon2.getIconHeight()));
		button2.setBackground(Color.WHITE);
		buttonPanel.add(button2);
		button2.setContentAreaFilled(false);

		// Botón 3
		java.net.URL imgUrl3 = getClass().getResource("/consultasIcono.png");
		Icon icon3 = new ImageIcon(imgUrl3);
		JButton button3 = new JButton(icon3);
		button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaCitaCRUD ventanaCitaCRUD = new VentanaCitaCRUD();
				ventanaCitaCRUD.setVisible(true);
			}
		});
		button3.setBounds(0, 135, 98, 40);
		button3.setPreferredSize(new Dimension(icon3.getIconWidth(), icon3.getIconHeight()));
		button3.setBackground(Color.WHITE);
		buttonPanel.add(button3);
		button3.setContentAreaFilled(false);

		// Botón 4
		java.net.URL imgUrl4 = getClass().getResource("/materialIcono.png");
		Icon icon4 = new ImageIcon(imgUrl4);
		JButton button4 = new JButton(icon4);
		button4.setBounds(0, 191, 98, 40);
		button4.setPreferredSize(new Dimension(icon4.getIconWidth(), icon4.getIconHeight()));
		button4.setBackground(Color.WHITE);
		buttonPanel.add(button4);
		button4.setContentAreaFilled(false);

		// Botón 5
		java.net.URL imgUrl5 = getClass().getResource("/facturacionIcono.png");
		Icon icon5 = new ImageIcon(imgUrl5);
		JButton button5 = new JButton(icon5);
		button5.setBounds(0, 249, 98, 40);
		button5.setPreferredSize(new Dimension(icon5.getIconWidth(), icon5.getIconHeight()));
		button5.setBackground(Color.WHITE);
		buttonPanel.add(button5);
		button5.setContentAreaFilled(false);

		// Botón 6
		java.net.URL imgUrl6 = getClass().getResource("/pedidosIcono.png");
		Icon icon6 = new ImageIcon(imgUrl6);
		JButton button6 = new JButton(icon6);
		button6.setBounds(-3, 308, 98, 40);
		button6.setPreferredSize(new Dimension(icon6.getIconWidth(), icon6.getIconHeight()));
		button6.setBackground(Color.WHITE);
		buttonPanel.add(button6);
		button6.setContentAreaFilled(false);

		// Botón 7
		java.net.URL imgUrl7 = getClass().getResource("/proveedoresIcono.png");
		Icon icon7 = new ImageIcon(imgUrl7);
		JButton button7 = new JButton(icon7);
		button7.setBounds(2, 365, 98, 40);
		button7.setPreferredSize(new Dimension(icon7.getIconWidth(), icon7.getIconHeight()));
		button7.setBackground(Color.WHITE);
		buttonPanel.add(button7);
		button7.setContentAreaFilled(false);

		// Botón 8
		java.net.URL imgUrl8 = getClass().getResource("/tratamientosIcono.png");
		Icon icon8 = new ImageIcon(imgUrl8);
		JButton button8 = new JButton(icon8);
		button8.setBounds(3, 426, 98, 40);
		button8.setPreferredSize(new Dimension(icon8.getIconWidth(), icon8.getIconHeight()));
		button8.setBackground(Color.WHITE);
		buttonPanel.add(button8);
		button8.setContentAreaFilled(false);

		// Botón 9
		java.net.URL imgUrl9 = getClass().getResource("/especialistasIcono.png");
		Icon icon9 = new ImageIcon(imgUrl9);
		JButton button9 = new JButton(icon9);
		button9.setBounds(0, 487, 98, 40);
		button9.setPreferredSize(new Dimension(icon9.getIconWidth(), icon9.getIconHeight()));
		button9.setBackground(Color.WHITE);
		buttonPanel.add(button9);
		button9.setContentAreaFilled(false);

		// Botón 10
		java.net.URL imgUrl10 = getClass().getResource("/usuariosIcono.png");
		Icon icon10 = new ImageIcon(imgUrl10);
		JButton button10 = new JButton(icon10);
		button10.setContentAreaFilled(false);
		button10.setBounds(0, 540, 98, 40);
		button10.setPreferredSize(new Dimension(icon10.getIconWidth(), icon10.getIconHeight()));
		button10.setBackground(Color.WHITE);
		buttonPanel.add(button10);

		// LABELS DE LOS BOTONES MENÚ

		JLabel lblNewLabel = new JLabel("PACIENTE");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(26, 0, 62, 14);
		buttonPanel.add(lblNewLabel);

		JLabel lblDoctor = new JLabel("DOCTOR");
		lblDoctor.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDoctor.setBounds(26, 59, 62, 20);
		buttonPanel.add(lblDoctor);

		JLabel lblCita = new JLabel("CITA");
		lblCita.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCita.setBounds(34, 121, 27, 14);
		buttonPanel.add(lblCita);

		JLabel lblFactura = new JLabel("FACTURA");
		lblFactura.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFactura.setBounds(24, 235, 54, 14);
		buttonPanel.add(lblFactura);

		JLabel lblSrock = new JLabel("STOCK");
		lblSrock.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSrock.setBounds(28, 350, 47, 14);
		buttonPanel.add(lblSrock);

		JLabel lblTratamiento = new JLabel("TRATAMIENTO");
		lblTratamiento.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTratamiento.setBounds(10, 472, 88, 14);
		buttonPanel.add(lblTratamiento);

		JLabel lblPedido = new JLabel("PEDIDO");
		lblPedido.setBounds(28, 292, 47, 14);
		buttonPanel.add(lblPedido);
		lblPedido.setFont(new Font("Tahoma", Font.BOLD, 11));

		JLabel lblUsuarios = new JLabel("USUARIOS");
		lblUsuarios.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUsuarios.setBounds(23, 528, 88, 14);
		buttonPanel.add(lblUsuarios);

		JLabel lblMaterial = new JLabel("MATERIAL");
		lblMaterial.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblMaterial.setBounds(23, 176, 88, 14);
		buttonPanel.add(lblMaterial);

		JLabel lblEspecialidad = new JLabel("ESPECIALIDAD");
		lblEspecialidad.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEspecialidad.setBounds(10, 409, 88, 14);
		buttonPanel.add(lblEspecialidad);
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
		editarPanel.setBounds(100, 0, 1164, 670);
		contentPane.add(editarPanel);

		// Panel donde están los labels

		editarPanel.setViewportView(panel);
		panel.setBackground(new Color(255, 255, 255));

		// Cargar la imagen desde la carpeta de recursos
		String rutaImagen = "/doctorIcono.png"; // Ajusta la ruta según la ubicación de tu recurso
		java.net.URL urlImagen = getClass().getResource(rutaImagen);
		ImageIcon imagen1 = new ImageIcon(urlImagen);
		panel.setLayout(null);

		// Añadir la imagen al panel
		JLabel labelImagen = new JLabel(imagen1);
		labelImagen.setBounds(10, 115, 512, 512);
		panel.add(labelImagen);

		// COLOR DEL PANEL(FONDO)

		// ID
		JLabel label_ID = new JLabel("ID:");
		label_ID.setBounds(630, 511, 36, 55);
		label_ID.setBackground(new Color(0, 128, 192));
		panel.add(label_ID);

		// Establecemos el tamaño y su fuente
		label_ID.setFont(fuenteLabel);
		label_ID.setBackground(Color.decode("#008cce"));

		// L NOMBRE
		JLabel label_Nombre = new JLabel("Nombre:");
		label_Nombre.setBounds(591, 13, 85, 65);
		panel.add(label_Nombre);

		label_Nombre.setFont(fuenteLabel);
		label_Nombre.setBackground(Color.decode("#008cce"));

		// APELLIDOS

		JLabel label_Apellidos = new JLabel("Apellidos:");
		label_Apellidos.setBounds(583, 89, 85, 65);
		panel.add(label_Apellidos);

		label_Apellidos.setFont(fuenteLabel);

		// TELEFONO

		JLabel label_Telefono = new JLabel("Teléfono:");
		label_Telefono.setBounds(591, 223, 82, 65);
		panel.add(label_Telefono);

		label_Telefono.setFont(fuenteLabel);

		// DIRECCION

		JLabel label_Direccion = new JLabel("Dirección:");
		label_Direccion.setBounds(583, 159, 93, 65);
		panel.add(label_Direccion);

		label_Direccion.setFont(fuenteLabel);

		// ID ESPECIALIDAD

		JLabel label_IDEspecialidad = new JLabel("ID Especialidad:");
		label_IDEspecialidad.setBounds(526, 435, 150, 65);
		panel.add(label_IDEspecialidad);

		label_IDEspecialidad.setFont(fuenteLabel);

		// SALARIO

		JLabel label_Salario = new JLabel("Salario:");
		label_Salario.setBounds(602, 359, 74, 65);
		panel.add(label_Salario);

		label_Salario.setFont(fuenteLabel);

		// EMAIL

		JLabel label_Email = new JLabel("Email:");
		label_Email.setBounds(606, 299, 62, 65);
		panel.add(label_Email);

		label_Email.setFont(fuenteLabel);

		String rutaImagen2 = "/guardarIcono.png"; // Ajusta la ruta según la ubicación de tu recurso
		java.net.URL urlImagen2 = getClass().getResource(rutaImagen2);

		// TextFields separados equitativamente

		JTextField textField_nombre = new JTextField();
		textField_nombre.setBounds(673, 30, 379, 38);
		panel.add(textField_nombre);

		JTextField textField_apellidos = new JTextField();
		textField_apellidos.setBounds(673, 100, 379, 38);
		panel.add(textField_apellidos);

		JTextField textField_direccion = new JTextField();
		textField_direccion.setBounds(673, 170, 379, 38);
		panel.add(textField_direccion);

		JTextField textField_tlf = new JTextField();
		textField_tlf.setBounds(673, 240, 379, 38);
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

		textField_email = new JTextField();
		textField_email.setBounds(673, 310, 379, 38);
		panel.add(textField_email);

		textField_Salario = new JTextField();
		textField_Salario.setBounds(673, 380, 379, 38);

		// ERRORES

		textField_Salario.addKeyListener(new KeyAdapter() {
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
		panel.add(textField_Salario);

		textField_especialidad = new JTextField();
		textField_especialidad.setBounds(673, 450, 379, 38);
		textField_especialidad.addKeyListener(new KeyAdapter() {
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
		panel.add(textField_especialidad);

		textField_id = new JTextField();
		textField_id.setBounds(673, 520, 379, 38);
		textField_id.addKeyListener(new KeyAdapter() {
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
		panel.add(textField_id);

		labelDoctor.setBounds(113, 45, 468, 47);
		panel.add(labelDoctor);
		labelDoctor.setFont(fuenteGrande);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(591, 593, 592, 75);
		panel_1.setForeground(Color.BLACK);
		panel_1.setBorder(null);
		panel_1.setBackground(new Color(70, 130, 180));
		panel.add(panel_1);
		panel_1.setLayout(null);
		panel_1.setBackground(Color.decode("#FFFFFF"));

		// BOTÓN GUARDAR
		JButton btnGuardar = new JButton(icon11);
		btnGuardar.setBounds(28, 11, 76, 59);
		panel_1.add(btnGuardar);
		btnGuardar.setPreferredSize(new Dimension(icon11.getIconWidth(), icon11.getIconHeight()));
		btnGuardar.setContentAreaFilled(false);
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Obtener los valores de los campos
				String nombre = textField_nombre.getText();
				String apellidos = textField_apellidos.getText();
				String telefono = textField_direccion.getText();
				String direccion = textField_tlf.getText();
				String salario = textField_email.getText();
				String idEspecialidad = textField_Salario.getText();
				String email = textField_especialidad.getText();
				String id = textField_id.getText();

				// Verificar que todos los campos estén llenos
				if (nombre.isEmpty() || apellidos.isEmpty() || telefono.isEmpty() || direccion.isEmpty()
						|| salario.isEmpty() || idEspecialidad.isEmpty() || email.isEmpty() || id.isEmpty()) {
					// Mostrar un mensaje de error si algún campo está vacío
					JOptionPane.showMessageDialog(null, "Rellena todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
				} else if (!email.contains("@")) {
					// Mostrar un mensaje de error si el correo electrónico no contiene '@'
					JOptionPane.showMessageDialog(null, "Ingrese un correo electrónico válido", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		// Establecer el botón como predeterminado para la tecla "Enter"
		getRootPane().setDefaultButton(btnGuardar);

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

		// Resto del código...

		// BOTÓN VOLVER
		JButton btnVolver = new JButton(icon13);
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				VentanaDoctorInterna ventanaDoctorInterna = new VentanaDoctorInterna();
				ventanaDoctorInterna.setVisible(true);

			}
		});
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

	}
}
