/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades.Cliente;
import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import conexao.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
/**
 *
 * @author eross
 */
public class ClienteFunctions {

    private ConexaoFac con;
    
    public ClienteFunctions() {
        con = new ConexaoFac();
    }
    
    public void add(Cliente cliente)
            throws SQLException {

        Connection conexao = con.conecta();
        java.sql.PreparedStatement ps = null;

        try {
            ps = conexao.prepareStatement("INSERT INTO `cliente`(`cpfCliente`, `nomeCliente`, `dataNascimentoCliente`) VALUES ('?','?','?')");
            ps.setString(1, cliente.getCpf());
            ps.setString(2, cliente.getNome());
            ps.setString(3, cliente.getDataNasc());

            ps.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Não foi possível adicionar cliente");
        } finally {
            con.deslogar(conexao, ps);
        }
    }
    
    public Cliente getCliente(int id)
            throws SQLException {

        Connection conexao = con.conecta();
        java.sql.PreparedStatement ps = null;
        ResultSet resultSet = null;

        Cliente cliente = null;

        try {
            ps = conexao.prepareStatement("SELECT * FROM contato WHERE id = ?");
            ps.setInt(1, id);
            resultSet = ps.executeQuery();

            while (resultSet.next()) {
                cliente = new Cliente(resultSet.getInt("id"), resultSet.getString("cpf") , resultSet.getString("nome") , resultSet.getString("dataNasc"));
            }

            if (cliente == null) {
                JOptionPane.showMessageDialog(null, "Cliente não existe");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Não foi possível buscar o cliente");
        } finally {
            con.deslogar(conexao, ps, resultSet);
        }

        return cliente;
    }

    
    public List<Cliente> getClientesPorNome(String nome)
            throws SQLException {

        Connection conexao = con.conecta();
        java.sql.PreparedStatement ps = null;
        ResultSet resultSet = null;

        List<Cliente> clientes = new ArrayList<>();

        try {
            ps = conexao.prepareStatement("SELECT * FROM cliente WHERE nome LIKE ?");
            ps.setString(1, "%" + nome + "%");
            resultSet = ps.executeQuery();

            while (resultSet.next()) {
                Cliente cliente = new Cliente(resultSet.getInt("id"), resultSet.getString("cpf") , resultSet.getString("nome") , resultSet.getString("dataNasc"));
                clientes.add(cliente);
            }

            if (clientes.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Não foram encontrados clientes com esse nome");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null , "Não foi possível buscar os contatos.");
        } finally {
            con.deslogar(conexao, ps, resultSet);
        }

        return clientes;
    }

    public List<Cliente> getClientesPorEmail(String email)
            throws SQLException {

        Connection conexao = con.conecta();
        java.sql.PreparedStatement ps = null;
        ResultSet resultSet = null;

        List<Cliente> contatos = new ArrayList<>();

        try {
            ps = conexao.prepareStatement("SELECT * FROM cliente WHERE email LIKE ?");
            ps.setString(1, "%" + email + "%");
            resultSet = ps.executeQuery();

            while (resultSet.next()) {
                Cliente cliente = new Cliente(resultSet.getInt("id"), resultSet.getString("cpf") , resultSet.getString("nome") , resultSet.getString("dataNasc"));
                contatos.add(cliente);
            }

            if (contatos.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Não existem clientes com esse email ou parecido.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Não foi possível fazer a consulta de clientes por email.");
        } finally {
            con.deslogar(conexao, ps, resultSet);
        }

        return contatos;
    }

    public List<Cliente> getClientes()
            throws SQLException {

        Connection conexao = con.conecta();
        java.sql.PreparedStatement ps = null;
        ResultSet resultSet = null;

        List<Cliente> clientes = new ArrayList<>();

        try {
            ps = conexao.prepareStatement("SELECT * FROM contato");
            resultSet = ps.executeQuery();
            
            while (resultSet.next()) {
                Cliente cliente = new Cliente(resultSet.getInt("id"), resultSet.getString("cpf") , resultSet.getString("nome") , resultSet.getString("dataNasc"));
                clientes.add(cliente);
            }

            if (clientes.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nenhum Cliente cadastrado.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Não foi possível realizar a busca de todos os clientes.");
        } finally {
            con.deslogar(conexao, ps, resultSet);
        }

        return clientes;
    }
    
    public void update(Cliente cliente)
            throws SQLException {

        Connection conexao = con.conecta();
        java.sql.PreparedStatement ps = null;

        try {
            ps = conexao.prepareStatement("UPDATE `cliente` SET `cpfCliente`='?',`nomeCliente`='?',`dataNascimentoCliente`='?' WHERE idCliente = ?");
            ps.setString(1, cliente.getCpf());
            ps.setString(2, cliente.getNome());
            ps.setString(3, cliente.getDataNasc());
            ps.setInt(4, cliente.getId());

            if (ps.executeUpdate() == 0) {
                JOptionPane.showMessageDialog(null , "Não foi encontrado cliente com esse ID");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Não foi possível realizar o update no cliente.");
        } finally {
            con.deslogar(conexao, ps);
        }
    }
    
    public void delete(int id)
            throws SQLException {

        Connection conexao = con.conecta();
        java.sql.PreparedStatement ps = null;

        try {
            ps = conexao.prepareStatement("DELETE FROM cliente WHERE idCliente = ?");
            ps.setInt(1, id);

            if (ps.executeUpdate() == 0) {
                JOptionPane.showMessageDialog(null, "Cliente inexistente");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao tentar remover cliente.");
        } finally {
            con.deslogar(conexao, ps);
        }
    }

}
