/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicasaracura.dao;

import clinicasaracura.models.Cliente;
import clinicasaracura.models.Pessoa;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author danilo
 */
public class ClienteDAO extends GenericDAO {
    
    PessoaDAO pessoaDAO;
    
    public ClienteDAO() {
        this.pessoaDAO = new PessoaDAO();
    }

    public void salvar(Cliente cliente) throws SQLException {
        this.pessoaDAO.salvar(cliente.getPessoa());
        String insert = "INSERT INTO clientes(pessoa_id) VALUES(?)";
        save(insert, cliente.getPessoa().getId());
    }

    public void alterar(Cliente cliente) throws SQLException {
        this.pessoaDAO.alterar(cliente.getPessoa());
        String update = "UPDATE clientes "
                + "SET pessoa_id = ?"
                + "WHERE id = ?";
        update(update, cliente.getId(), cliente.getPessoa().getId());
    }

    public List findClientes() throws SQLException {
        List clientes = new ArrayList();

        String select = "SELECT * FROM clientes";

        PreparedStatement stmt = getConnection().prepareStatement(select);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Cliente cliente = new Cliente();
            cliente.setId(rs.getInt("id"));
            int pessoaId = rs.getInt("pessoa_id");
            Pessoa pessoa = this.pessoaDAO.findById(pessoaId);
            cliente.setPessoa(pessoa);
            clientes.add(cliente);
        }

        rs.close();
        stmt.close();
        getConnection().close();

        return clientes;
    }
}
