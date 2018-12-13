/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicasaracura.dao;

import clinicasaracura.models.Agenda;
import clinicasaracura.models.Pessoa;
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
        //this.agendaDAO.salvar(agenda);
        String insert = "INSERT INTO pessoas(nome, cpf, telefone, tipo, agenda_id) VALUES(?,?,?,?,?)";
        int id = save(insert, pessoa.getNome(), pessoa.getCpf(), pessoa.getTelefone(), pessoa.getTipo(), agenda.getId());
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
}
