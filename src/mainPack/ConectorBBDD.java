package mainPack;

// Imports BBDD
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import java.sql.PreparedStatement;

/**
 * Autores: David Andrade Pablo Rodriguez Ian Requena 2023
 */

public class ConectorBBDD {

	// Variables
	String url = "jdbc:mysql://localhost:3306/dentilax";
	String usuario = "root";
	String contrasenia = "1234";
	private Connection conexion; // Conexión
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	boolean credencialesValidas = false;

	// Métodos/Funciones conectarConBBDD
	public boolean conectarConBBDD() {
		try {
			conexion = DriverManager.getConnection(url, usuario, contrasenia);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Error al conectar con la base de datos");
			return false;
		}
	}

	public ConectorBBDD() {
		conectarConBBDD(); // Llama al método para establecer la conexión al crear una instancia
	}

	protected void cerrarConexion() {
		try {
			if (conexion != null) {
				conexion.close();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void insertarPaciente(String nombre, String apellidos, String direccion, String telefono, String ultimaConsulta, String id) {
	    try {
	        if (conectarConBBDD()) {
	            String consulta = "INSERT INTO paciente (nombre, apellidos, dirección, teléfono, ultimaConsulta) VALUES (?, ?, ?, ?, ?)";
	            PreparedStatement statement = conexion.prepareStatement(consulta);
	            statement.setString(1, nombre);
	            statement.setString(2, apellidos);
	            statement.setString(3, direccion);
	            statement.setString(4, telefono);
	            statement.setString(5, ultimaConsulta);

	            // Si id es un campo autoincremental, no es necesario incluirlo en la consulta

	            int filasAfectadas = statement.executeUpdate();

	            if (filasAfectadas > 0) {
	                JOptionPane.showMessageDialog(null, "Paciente insertado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
	            } else {
	                JOptionPane.showMessageDialog(null, "Error al insertar paciente", "Error", JOptionPane.ERROR_MESSAGE);
	            }

	            cerrarConexion();
	        }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Error SQL al insertar paciente", "Error", JOptionPane.ERROR_MESSAGE);
	    }
	}
	
	
	

	public void realizarBusqueda(String criterio, DefaultTableModel modeloTabla) {
		PreparedStatement statementPaciente = null;
		PreparedStatement statementDoctor = null;
		ResultSet resultadoPaciente = null;
		ResultSet resultadoDoctor = null;

		try {
			// Verificar si la conexión está cerrada y abrir si es necesario
			if (conexion == null || conexion.isClosed()) {
				conectarConBBDD();
			}

			// CONSULTA SQL para la tabla 'dentilax.paciente'
			String consultaPaciente = "SELECT nombre, apellidos, idPaciente, ultimaConsulta FROM dentilax.paciente WHERE nombre LIKE ? OR idPaciente LIKE ?";
			statementPaciente = conexion.prepareStatement(consultaPaciente);
			statementPaciente.setString(1, "%" + criterio + "%");
			statementPaciente.setString(2, "%" + criterio + "%");

			resultadoPaciente = statementPaciente.executeQuery();

			// CONSULTA SQL para la tabla 'dentilax.doctor'
			String consultaDoctor = "SELECT nombre, apellidos, idDoctor, email FROM dentilax.doctor WHERE nombre LIKE ? OR idDoctor LIKE ?";
			statementDoctor = conexion.prepareStatement(consultaDoctor);
			statementDoctor.setString(1, "%" + criterio + "%");
			statementDoctor.setString(2, "%" + criterio + "%");

			resultadoDoctor = statementDoctor.executeQuery();

			// Limpiar la tabla antes de agregar nuevos resultados
			modeloTabla.setRowCount(0);

			// Procesar y mostrar los resultados para 'dentilax.paciente'
			while (resultadoPaciente.next()) {
				String nombre = resultadoPaciente.getString("nombre");
				String apellidos = resultadoPaciente.getString("apellidos");
				int idPaciente = resultadoPaciente.getInt("idPaciente");
				String ultimaConsulta = resultadoPaciente.getString("ultimaConsulta");

				// Agregar la fila a la tabla
				modeloTabla.addRow(new Object[] { nombre, apellidos, idPaciente, ultimaConsulta });

//				System.out.println("Nombre Paciente: " + nombre + ", Apellidos: " + apellidos + ", ID Paciente: "
//						+ idPaciente + ", Última Consulta: " + ultimaConsulta);
			}

			// Procesar y mostrar los resultados para 'dentilax.doctor'
			while (resultadoDoctor.next()) {
				String nombreDoctor = resultadoDoctor.getString("nombre");
				String apellidosDoctor = resultadoDoctor.getString("apellidos");
				int idDoctor = resultadoDoctor.getInt("idDoctor");
				String emailDoctor = resultadoDoctor.getString("email");

				// Agregar la fila a la tabla
				modeloTabla.addRow(new Object[] { nombreDoctor, apellidosDoctor, idDoctor, emailDoctor });

//				System.out.println("Nombre Doctor: " + nombreDoctor + ", Apellidos: " + apellidosDoctor
//						+ ", ID Doctor: " + idDoctor + ", Email: " + emailDoctor);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error SQL al realizar la búsqueda", "Error",
					JOptionPane.ERROR_MESSAGE);
		} finally {
			cerrarRecursos(resultadoPaciente, statementPaciente);
			cerrarRecursos(resultadoDoctor, statementDoctor);
		}
	}

	// Método para cerrar los recursos (ResultSet y PreparedStatement)
	private void cerrarRecursos(ResultSet rs, PreparedStatement stmt) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private boolean cargandoDatos = false; // Variable que indica si se están cargando datos

	public boolean isCargandoDatos() {
		return cargandoDatos;
	}

	public void setCargandoDatos(boolean cargandoDatos) {
		this.cargandoDatos = cargandoDatos;
	}

	public boolean cargarDatosPacientes(DefaultTableModel modeloTabla) {
		try {

			// Indicar que se están cargando datos
			setCargandoDatos(true);

			Vector<String> columnas = new Vector<>();
			columnas.add("Nombre");
			columnas.add("Apellidos");
			columnas.add("Documento");
			columnas.add("Última Consulta");

			modeloTabla.setColumnIdentifiers(columnas);

			// Verifica si la conexión es null antes de utilizarla
//			System.out.println("Conexión a la base de datos: " + (this.conexion != null ? "exitosa" : "fallida"));

			if (this.conexion != null) {
				// CONSULTA SQL
				String consulta = "SELECT nombre, apellidos, idPaciente, ultimaConsulta FROM dentilax.paciente";
//				System.out.println("Consulta SQL: " + consulta);

				Statement statement = conexion.createStatement();
				ResultSet resultado = statement.executeQuery(consulta);

				while (modeloTabla.getRowCount() > 0) {
					modeloTabla.removeRow(0);
				}

				while (resultado.next()) {
					Object[] fila = { resultado.getString("nombre"), resultado.getString("apellidos"),
							resultado.getInt("idPaciente"), resultado.getString("ultimaConsulta") };
					modeloTabla.addRow(fila);
				}

//				System.out.println("Filas en la tabla de pacientes: " + modeloTabla.getRowCount());
			} else {
//				System.out.println("La conexión es null. Asegúrate de haber establecido la conexión.");
			}

			// cerrarConexion(); // Puedes habilitar esto si necesitas cerrar la conexión en
			// este punto
			if (modeloTabla.getRowCount() > 0) {
				return true; // Devuelve true solo si se cargaron datos
			} else {
				return false; // Devuelve false si no se cargaron datos
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error SQL al cargar los datos de pacientes", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error al cargar los datos de pacientes", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		} finally {
			// Indicar que se ha terminado de cargar datos, ya sea con éxito o con error
			setCargandoDatos(false);
		}
	}
	public void insertarDoctor(String nombre, String apellidos, String telefono, String direccion, int idEspecialidad, int salario, String email) {
	    try {
	        if (conectarConBBDD()) {
	            String consulta = "INSERT INTO doctor (nombre, apellidos, teléfono, dirección, idEspecialidad_FK, salario, email) VALUES (?, ?, ?, ?, ?, ?, ?)";
	            PreparedStatement statement = conexion.prepareStatement(consulta);
	            statement.setString(1, nombre);
	            statement.setString(2, apellidos);
	            statement.setString(3, telefono);
	            statement.setString(4, direccion);
	            statement.setInt(5, idEspecialidad);
	            statement.setInt(6, salario);
	            statement.setString(7, email);

	            int filasAfectadas = statement.executeUpdate();

	            if (filasAfectadas > 0) {
	                JOptionPane.showMessageDialog(null, "Doctor insertado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
	            } else {
	                JOptionPane.showMessageDialog(null, "Error al insertar doctor", "Error", JOptionPane.ERROR_MESSAGE);
	            }

	            cerrarConexion();
	        }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Error SQL al insertar doctor", "Error", JOptionPane.ERROR_MESSAGE);
	    }
	}



	public boolean cargarDatosDoctores(DefaultTableModel modeloTabla) {
		try {

			// Indicar que se están cargando datos
			setCargandoDatos(true);

			// Verifica si la conexión es null antes de utilizarla
			if (this.conexion != null) {
				Vector<String> columnas = new Vector<>();
				columnas.add("Nombre");
				columnas.add("Apellidos");
				columnas.add("ID");
				columnas.add("Email");

				modeloTabla.setColumnIdentifiers(columnas);

				// CONSULTA SQL
				String consulta = "SELECT nombre, apellidos, idDoctor, email FROM dentilax.doctor";
				Statement statement = conexion.createStatement();
				ResultSet resultado = statement.executeQuery(consulta);

				while (modeloTabla.getRowCount() > 0) {
					modeloTabla.removeRow(0);
				}

				while (resultado.next()) {
					Object[] fila = { resultado.getString("nombre"), resultado.getString("apellidos"),
							resultado.getInt("idDoctor"), resultado.getString("email") };
					modeloTabla.addRow(fila);
				}
			} else {
//				System.out.println("La conexión es null. Asegúrate de haber establecido la conexión.");
			}
			if (modeloTabla.getRowCount() > 0) {
				return true; // Devuelve true solo si se cargaron datos
			} else {
				return false; // Devuelve false si no se cargaron datos
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error SQL al cargar los datos de doctores", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error al cargar los datos de doctores", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		} finally {
			// Indicar que se ha terminado de cargar datos, ya sea con éxito o con error
			setCargandoDatos(false);
		}
	}

	public boolean cargarDatosCitas(DefaultTableModel modeloTabla) {
		try {
			// Indicar que se están cargando datos
			setCargandoDatos(true);

			Vector<String> columnas = new Vector<>();
			columnas.add("ID Paciente");
			columnas.add("Motivo");
			columnas.add("Fecha");
			columnas.add("Hora");

			modeloTabla.setColumnIdentifiers(columnas);

			// Verifica si la conexión es null antes de utilizarla
			if (this.conexion != null) {
				// CONSULTA SQL
				String consulta = "SELECT idCita, fecha, hora, motivo, idPaciente_FK, idDoctor_FK FROM dentilax.cita";
				Statement statement = conexion.createStatement();
				ResultSet resultado = statement.executeQuery(consulta);

				while (modeloTabla.getRowCount() > 0) {
					modeloTabla.removeRow(0);
				}

				while (resultado.next()) {
					Object[] fila = { resultado.getInt("idPaciente_FK"), resultado.getString("motivo"),
							resultado.getDate("fecha"), resultado.getString("hora") };
					modeloTabla.addRow(fila);
				}
			} else {
//				System.out.println("La conexión es null. Asegúrate de haber establecido la conexión.");
			}

			if (modeloTabla.getRowCount() > 0) {
				return true; // Devuelve true solo si se cargaron datos
			} else {
				return false; // Devuelve false si no se cargaron datos
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error SQL al cargar los datos de citas", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error al cargar los datos de citas", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		} finally {
			// Indicar que se ha terminado de cargar datos, ya sea con éxito o con error
			setCargandoDatos(false);
		}
	}

	private String obtenerEspecialidad(int idEspecialidad) {
		try {
			String consultaEspecialidad = "SELECT nombre FROM dentilax.especialidad WHERE idEspecialidad = ?";
			PreparedStatement statementEspecialidad = conexion.prepareStatement(consultaEspecialidad);
			statementEspecialidad.setInt(1, idEspecialidad);
			ResultSet resultadoEspecialidad = statementEspecialidad.executeQuery();

			if (resultadoEspecialidad.next()) {
				return resultadoEspecialidad.getString("nombre");
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return "";
	}

	public boolean verificarCredencialesEnBaseDeDatos(String usuario, String contrasenia) {

		try {
			/*
			 * String url = "jdbc:mysql://localhost:3306/sys?useSSL=false"; String dbUser =
			 * "root"; String dbPassword = "1234"; conexion =
			 * DriverManager.getConnection(url, dbUser, dbPassword);
			 */

			conectarConBBDD();

			// Sentencia SQL para comprobar usuario y contraseña
			String selectSQL = "SELECT * FROM dentilax.usuario WHERE Nombre = ? AND contraseña = ?";
			preparedStatement = (PreparedStatement) conexion.prepareStatement(selectSQL);
			preparedStatement.setString(1, usuario);
			preparedStatement.setString(2, contrasenia);
			resultSet = preparedStatement.executeQuery();

			// Condicional para controlar si es admin o es doctor
			if (resultSet.next()) {
				String rol = resultSet.getString("rol");

				if ("administrador".equals(rol)) {
					credencialesValidas = true;
					new VentanaPrincipal().setVisible(true);
					new VentanaEspectador().setVisible(false);
				} else if ("doctor".equals(rol)) {
					credencialesValidas = true;
					new VentanaEspectador().setVisible(true);
					new VentanaPrincipal().setVisible(false);
				} else {
					JOptionPane.showMessageDialog(null, "Rol no válido", "Error de Rol", JOptionPane.ERROR_MESSAGE);
				}

			} else {
				JOptionPane.showMessageDialog(null, "Credenciales incorrectas", "Error de Inicio de Sesión",
						JOptionPane.ERROR_MESSAGE);
			}

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (conexion != null) {
					conexion.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return credencialesValidas;
	}

	public void insertarPaciente1(String nombre, String apellidos, String direccion, String telefono, String ultimaConsulta, String id) {
	    try {
	        if (conectarConBBDD()) {
	            String consulta = "INSERT INTO paciente (nombre, apellidos, dirección, teléfono, ultimaConsulta) VALUES (?, ?, ?, ?, ?)";
	            PreparedStatement statement = conexion.prepareStatement(consulta);
	            statement.setString(1, nombre);
	            statement.setString(2, apellidos);
	            statement.setString(3, direccion);
	            statement.setString(4, telefono);
	            statement.setString(5, ultimaConsulta);

	            // Si id es un campo autoincremental, no es necesario incluirlo en la consulta

	            int filasAfectadas = statement.executeUpdate();

	            if (filasAfectadas > 0) {
	                JOptionPane.showMessageDialog(null, "Paciente insertado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
	            } else {
	                JOptionPane.showMessageDialog(null, "Error al insertar paciente", "Error", JOptionPane.ERROR_MESSAGE);
	            }

	            cerrarConexion();
	        }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Error SQL al insertar paciente", "Error", JOptionPane.ERROR_MESSAGE);
	    }
	}
}
