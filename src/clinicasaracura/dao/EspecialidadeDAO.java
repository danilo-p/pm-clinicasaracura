/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicasaracura.dao;

import clinicasaracura.models.Especialidade;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author vien
 */
public class EspecialidadeDAO extends GenericDAO{
    
    public void salvar(Especialidade especialidade) throws SQLException {
        String insert = "INSERT INTO especialidades(nome) VALUES(?)";
        int id = save(insert, especialidade.getNome());
        if (id > 0) {
            especialidade.setId(id);
        }
    }

    public void alterar(Especialidade especialidade) throws SQLException {
        String update = "UPDATE especialidades SET nome = ? WHERE id = ?";
        update(update, especialidade.getId(), especialidade.getNome());
    }
    
    public Especialidade findById(int id) throws SQLException {
        String select = "SELECT * FROM especialidades WHERE id = ?";
        Especialidade especialidade = null;
        Connection connection = getConnection();
        PreparedStatement stmt = connection.prepareStatement(select);

        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            especialidade = new Especialidade();
            especialidade.setId(rs.getInt("id"));
            especialidade.setNome(rs.getString("nome"));
        }

        rs.close();
        stmt.close();
        connection.close();

        return especialidade;
    }

}
