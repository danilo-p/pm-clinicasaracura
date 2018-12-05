/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicasaracura.dao;

import clinicasaracura.models.Cliente;
import clinicasaracura.models.Pessoa;
import java.sql.Connection;
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
    AgendaDAO agendaDAO;
    
    public ClienteDAO() {
        this.pessoaDAO = new PessoaDAO();
        this.agendaDAO = new AgendaDAO();
    }

    public void salvar(Cliente cliente) throws SQLException {
        Pessoa pessoa = cliente.getPessoa();
        this.pessoaDAO.salvar(pessoa);
        String insert = "INSERT INTO clientes(pessoa_id) VALUES(?)";
        int id = save(insert, pessoa.getId());
        if (id > 0) {
            cliente.setId(id);
        }
    }

    public void alterar(Cliente cliente) throws SQLException {
        Pessoa pessoa = cliente.getPessoa();
        this.agendaDAO.alterar(pessoa.getAgenda());
        this.pessoaDAO.alterar(pessoa);
        String update = "UPDATE clientes SET pessoa_id = ? WHERE id = ?";
        update(update, cliente.getId(), cliente.getPessoa().getId());
    }

    public List findClientes() throws SQLException {
        List clientes = new ArrayList();

        String select = "SELECT * FROM clientes";

        Connection connection = getConnection();
        PreparedStatement stmt = connection.prepareStatement(select);
        ResultSet rs = stmt.executeQuery();
        
        System.out.println("adkfjalkdsfjaldf 1");

        while (rs.next()) {
            System.out.println("adkfjalkdsfjaldf 2");
            Cliente cliente = new Cliente();
            cliente.setId(rs.getInt("id"));
            int pessoaId = rs.getInt("pessoa_id");
            System.out.println("adkfjalkdsfjaldf 3");
            Pessoa pessoa = this.pessoaDAO.findById(pessoaId);
            cliente.setPessoa(pessoa);
            clientes.add(cliente);
            System.out.println("adkfjalkdsfjaldf 4");
        }
        
        System.out.println("adkfjalkdsfjaldf 5");

        rs.close();
        stmt.close();
        connection.close();
        
        System.out.println("adkfjalkdsfjaldf 6");

        return clientes;
    }
}
