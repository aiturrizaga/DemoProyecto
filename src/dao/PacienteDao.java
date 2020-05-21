package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.PacienteModelo;

public class PacienteDao extends Conexion {

    // Metodo para agregar paciente
    public void agregarPaciente(PacienteModelo paciente) throws SQLException {
        try {
            this.conexion();
            String sql = "INSERT INTO PACIENTE VALUES (null, ?, ?, ?, STR_TO_DATE(?, '%d/%m/%Y'), ?, ?)";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, paciente.getNombre());
            ps.setString(2, paciente.getApellido());
            ps.setString(3, paciente.getDireccion());
            ps.setString(4, paciente.getFechaNacimiento());
            ps.setString(5, paciente.getDni());
            ps.setString(6, paciente.getCelular());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Ocurrió un error al agregar paciente: " + e.getMessage());
        } finally {
            this.desconectar();
        }
    }

    // Metodo para listar pacientes
    public ArrayList<PacienteModelo> listarPacientes() throws SQLException {
        this.conexion();
        ResultSet rs;
        ArrayList<PacienteModelo> lista;
        try {
            String sql = "SELECT ID, NOMBRE, APELLIDO, DIRECCION, FECHA_NAC, DNI, CELULAR FROM PACIENTE";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            rs = ps.executeQuery();
            lista = new ArrayList<>();
            while (rs.next()) {
                lista.add(
                        new PacienteModelo(
                                rs.getInt("ID"),
                                rs.getString("NOMBRE"),
                                rs.getString("APELLIDO"),
                                rs.getString("DIRECCION"),
                                rs.getString("FECHA_NAC"),
                                rs.getString("DNI"),
                                rs.getString("CELULAR")
                        )
                );
            }
            return lista;
        } catch (SQLException e) {
            System.err.println("Ocurrió un error al listar pacientes: " + e.getMessage());
            return null;
        } finally {
            this.desconectar();
        }
    }
}
