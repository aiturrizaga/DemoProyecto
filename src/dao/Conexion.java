package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private Connection cn;

    // Metodo para conectarse a la base de datos
    public void conexion() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "");
            System.out.println("Se conecto a la base de datos hospital");
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error al conectar a la base de datos hospital: " + e.getMessage());
        }
    }
    
    // Metodo para desconectarse de la base de datos
    public void desconectar() throws SQLException {
        if (cn != null) {
            if (cn.isClosed() == false) {
                cn.close();
            }
        }
    }

    public Connection getCn() {
        return cn;
    }

    public void setCn(Connection cn) {
        this.cn = cn;
    }
    
    public static void main(String[] args) {
        Conexion cnx = new Conexion();
        cnx.conexion();
    }
    
}
