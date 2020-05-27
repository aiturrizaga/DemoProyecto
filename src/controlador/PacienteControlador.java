package controlador;

import dao.PacienteDao;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import modelo.PacienteModelo;

public class PacienteControlador {

    PacienteDao dao = new PacienteDao();
    ArrayList<PacienteModelo> listaPacientes = new ArrayList<>();

    public boolean agregarPaciente(String nombre, String apellido, String direc, String fechaNac, String dni, String celular) {
        try {
            dao.agregarPaciente(new PacienteModelo(null, nombre, apellido, direc, fechaNac, dni, celular));
            return true;
        } catch (SQLException e) {
            System.err.println("Error en el controlador de agregar paciente");
            return false;
        }
    }

    public boolean actualizarPaciente(Integer id, String nombre, String apellido, String direc, String fechaNac, String dni, String celular) {
        try {
            dao.actualizarPaciente(new PacienteModelo(id, nombre, apellido, direc, fechaNac, dni, celular));
            return true;
        } catch (SQLException e) {
            System.err.println("Error en el controlador de agregar paciente");
            return false;
        }
    }

    public DefaultTableModel listarPacientes() {
        String[] columnas = {"ID", "Nombre", "Apellido", "Dirección", "Fecha Nacimiento", "Dni", "Celular"};
        DefaultTableModel modelo = new DefaultTableModel(null, columnas);
        Object[] registros = new Object[7];
        try {
            listaPacientes = dao.listarPacientes();
            for (int i = 0; i < listaPacientes.size(); i++) {
                registros[0] = listaPacientes.get(i).getId();
                registros[1] = listaPacientes.get(i).getNombre();
                registros[2] = listaPacientes.get(i).getApellido();
                registros[3] = listaPacientes.get(i).getDireccion();
                registros[4] = listaPacientes.get(i).getFechaNacimiento();
                registros[5] = listaPacientes.get(i).getDni();
                registros[6] = listaPacientes.get(i).getCelular();
                modelo.addRow(registros);
            }
            return modelo;
        } catch (SQLException e) {
            System.err.println("Error al listar pacientes");
            return null;
        }
    }

}
