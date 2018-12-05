/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicasaracura.dao;

import clinicasaracura.models.Agenda;
import clinicasaracura.models.Cliente;
import clinicasaracura.models.Pessoa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object para o modelo Cliente.
 *
 * @see https://pt.wikipedia.org/wiki/Objeto_de_acesso_a_dados
 *
 * @author danilo
 */
public class ClienteDAO extends GenericDAO {
    
    private final PessoaDAO pessoaDAO;
    private final AgendaDAO agendaDAO;
    
    public ClienteDAO() {
        this.pessoaDAO = new PessoaDAO();
        this.agendaDAO = new AgendaDAO();
    }

    public void salvar(Cliente cliente) throws SQLException {
        this.pessoaDAO.salvar(cliente);
    }

    public List findClientes() throws SQLException {
        List pessoas = new ArrayList();

        String select = "SELECT * FROM pessoas WHERE tipo = 0";

        Connection connection = getConnection();
        PreparedStatement stmt = connection.prepareStatement(select);
        
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Cliente cliente = new Cliente();

            cliente.setId(rs.getInt("id"));
            cliente.setNome(rs.getString("nome"));
            cliente.setCpf(rs.getString("cpf"));
            cliente.setTelefone(rs.getString("telefone"));
            cliente.setTipo(rs.getInt("tipo"));

            int agendaId = rs.getInt("agenda_id");
            Agenda agenda = this.agendaDAO.findById(agendaId);
            cliente.setAgenda(agenda);

            pessoas.add(cliente);
        }

        rs.close();
        stmt.close();
        connection.close();

        return pessoas;
    }
}
