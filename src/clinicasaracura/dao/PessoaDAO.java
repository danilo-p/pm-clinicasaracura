/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicasaracura.dao;

import clinicasaracura.models.Agenda;
import clinicasaracura.models.Pessoa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Data Access Object para o modelo Pessoa.
 *
 * @see https://pt.wikipedia.org/wiki/Objeto_de_acesso_a_dados
 *
 * @author danilo
 */
public class PessoaDAO extends GenericDAO {

    private AgendaDAO agendaDAO;

    public PessoaDAO() {
        this.agendaDAO = new AgendaDAO();
    }

    public void salvar(Pessoa pessoa) throws SQLException {
        Agenda agenda = pessoa.getAgenda();
        this.agendaDAO.salvar(agenda);
        String insert = "INSERT INTO pessoas(nome, cpf, telefone, agenda_id) VALUES(?,?,?,?)";
        int id = save(insert, pessoa.getNome(), pessoa.getCpf(), pessoa.getTelefone(), agenda.getId());
        if (id > 0) {
            pessoa.setId(id);
        }
    }

    public void alterar(Pessoa pessoa) throws SQLException {
        String update = "UPDATE pessoas "
                + "SET nome = ?, cpf = ?, telefone = ? "
                + "WHERE id = ?";
        update(update, pessoa.getId(), pessoa.getNome(), pessoa.getCpf(), pessoa.getTelefone());
    }

    public Pessoa findById(int id) throws SQLException {
        String select = "SELECT * FROM pessoas WHERE id = ?";
        Connection connection = getConnection();
        PreparedStatement stmt = connection.prepareStatement(select);

        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        Pessoa pessoa = null;
        while (rs.next()) {
            pessoa = new Pessoa();
            pessoa.setId(rs.getInt("id"));
            pessoa.setNome(rs.getString("nome"));
            pessoa.setCpf(rs.getString("cpf"));
            pessoa.setTelefone(rs.getString("telefone"));
            int agendaId = rs.getInt("agenda_id");
            Agenda agenda = this.agendaDAO.findById(agendaId);
            pessoa.setAgenda(agenda);
        }

        rs.close();
        stmt.close();
        connection.close();

        return pessoa;
    }
}
