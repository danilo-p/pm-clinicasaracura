/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicasaracura.dao;

import clinicasaracura.models.Especialidade;
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
}
