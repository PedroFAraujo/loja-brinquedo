/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexao;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author eross
 */
public class ConexaoFac {
    private final String driver = "com.mysql.jdbc.Driver";
    
    // URL de acesso ao banco de dados.
    private final String url = "jdbc:mysql://localhost:3306/contatodb";
    // Nome de usuário para acesso ao banco de dados.
    private final String usuario = "root";
    // Senha de usuário para acesso ao banco de dados.
    private final String senha = "";

    public Connection conecta() {
        try {
            Class.forName(driver);
            return DriverManager.getConnection(url, usuario, senha);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deslogar(Connection con) {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deslogar(Connection con, PreparedStatement ps) {
        ConexaoFac.this.deslogar(con);
        try {
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deslogar(Connection con, PreparedStatement ps, ResultSet rs) {
        ConexaoFac.this.deslogar(con, ps);
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}