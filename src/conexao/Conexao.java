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
public class Conexao {
    final private String driver  = "com.mysql.jdbc.Driver";
    final private String url = "jdbc:mysql://127.0.0.1/pequenos-exploradores";
    final private String usuario = "root";
    final private String senha = "";
    
    private Connection conexao;
    public Statement statement;
    public ResultSet resultset;
    
    public boolean conecta(){
        boolean resultado = true;
        
        try{
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, usuario, senha);
        }catch(ClassNotFoundException | SQLException exception){
            resultado = false;
            JOptionPane.showMessageDialog(null, "Ocorreu um erro na conexão com o banco. Erro: " + exception);
        }
        
        return resultado;
    }
    
    public boolean desconecta(){
        boolean resultado = true;
        
        try{
            conexao.close();
        }catch(SQLException exception){
            resultado = false;
            JOptionPane.showMessageDialog(null, "Erro ao desconectar. Erro: " + exception);
        }
        
        return resultado;
    }
    
    public void executaSQL(String sql){
        try{
            statement = conexao.createStatement(resultset.TYPE_SCROLL_SENSITIVE, resultset.CONCUR_READ_ONLY);
            resultset = statement.executeQuery(sql);
        }catch(SQLException exception){
            JOptionPane.showMessageDialog(null, "Erro ao executar instrução SQL. Erro: " + exception);
        }
    }
}