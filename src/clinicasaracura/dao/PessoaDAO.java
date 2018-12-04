/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicasaracura.dao;

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
public class PessoaDAO extends GenericDAO {
    public void salvar(Pessoa pessoa) throws SQLException {
        String insert = "INSERT INTO pessoas(nome, cpf, telefone) VALUES(?,?,?)";
        save(insert, pessoa.getNome(), pessoa.getCpf(), pessoa.getTelefone());
    }

    public void alterar(Pessoa pessoa) throws SQLException {
        String update = "UPDATE pessoas "
                + "SET nome = ?, cpf = ?, telefone = ? "
                + "WHERE id = ?";
        update(update, pessoa.getId(), pessoa.getNome(), pessoa.getCpf(), pessoa.getTelefone());
    }

    public void excluir(int id) throws SQLException {
        String delete = "DELETE FROM pessoas WHERE id = ?";
        delete(delete, id);
    }

    public List findPessoas() throws SQLException {
        List pessoas = new ArrayList();

        String select = "SELECT * FROM pessoas";

        PreparedStatement stmt = getConnection().prepareStatement(select);

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Pessoa pessoa = new Pessoa();
            pessoa.setId(rs.getInt("id"));
            pessoa.setNome(rs.getString("nome"));
            pessoa.setCpf(rs.getString("cpf"));
            pessoa.setTelefone(rs.getString("telefone"));
            pessoas.add(pessoa);
        }

        rs.close();
        stmt.close();
        getConnection().close();

        return pessoas;
    }

    public Pessoa findByName(String nome) throws SQLException {
        String select = "SELECT * FROM pessoas WHERE nome = ?";
        Pessoa pessoa = null;
        PreparedStatement stmt = getConnection().prepareStatement(select);

        stmt.setString(1, nome);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            pessoa = new Pessoa();
            pessoa.setId(rs.getInt("id"));
            pessoa.setNome(rs.getString("nome"));
            pessoa.setCpf(rs.getString("cpf"));
            pessoa.setTelefone(rs.getString("telefone"));
        }

        rs.close();
        stmt.close();
        getConnection().close();

        return pessoa;
    }
}
