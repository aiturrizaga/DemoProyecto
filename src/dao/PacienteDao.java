package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.PacienteModelo;

public class PacienteDao extends Conexion {

    // Metodo para agregar paciente
    public boolean agregarPaciente(PacienteModelo paciente) throws SQLException {
        try {
            this.conexion();
            String sql = "INSERT INTO PACIENTE VALUES (null, ?, ?, ?, STR_TO_DATE(?, '%d/%m/%Y'), ?, ?, 'A')";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, paciente.getNombre());
            ps.setString(2, paciente.getApellido());
            ps.setString(3, paciente.getDireccion());
            ps.setString(4, paciente.getFechaNacimiento());
            ps.setString(5, paciente.getDni());
            ps.setString(6, paciente.getCelular());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Ocurrió un error al agregar paciente: " + e.getMessage());
            return false;
        } finally {
            this.desconectar();
        }
    }

    // Metodo para actualizar paciente
    public boolean actualizarPaciente(PacienteModelo paciente) throws SQLException {
        try {
            this.conexion();
            String sql = "UPDATE PACIENTE SET NOMBRE=?, APELLIDO=?, DIRECCION=?, FECHA_NAC = STR_TO_DATE(?, '%d/%m/%Y'), DNI=?, CELULAR=? WHERE ID=?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, paciente.getNombre());
            ps.setString(2, paciente.getApellido());
            ps.setString(3, paciente.getDireccion());
            ps.setString(4, paciente.getFechaNacimiento());
            ps.setString(5, paciente.getDni());
            ps.setString(6, paciente.getCelular());
            ps.setInt(7, paciente.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Ocurrió un error al agregar paciente: " + e.getMessage());
            return false;
        } finally {
            this.desconectar();
        }
    }
    
    public boolean inactivarPaciente(Integer id) throws SQLException {
        try {
            this.conexion();
            String sql = "UPDATE PACIENTE SET ESTADO = 'I' WHERE ID = ?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Ocurrió un error al inactivar el paciente: " + e.getMessage());
            return false;
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
            String sql = "SELECT ID, NOMBRE, APELLIDO, DIRECCION, DATE_FORMAT(FECHA_NAC, '%d/%m/%Y') as FECHA_NAC, DNI, CELULAR FROM PACIENTE WHERE ESTADO = 'A'";
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
