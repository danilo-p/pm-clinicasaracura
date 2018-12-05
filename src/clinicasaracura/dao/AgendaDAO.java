/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicasaracura.dao;

import clinicasaracura.models.Agenda;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Data Access Object para o modelo Agenda.
 *
 * @see https://pt.wikipedia.org/wiki/Objeto_de_acesso_a_dados
 *
 * @author danilo
 */
public class AgendaDAO extends GenericDAO {

    public void salvar(Agenda agenda) throws SQLException {
        String insert = "INSERT INTO agendas(carga_horaria, hora_inicio, hora_fim, tempo_intervalo) VALUES(?, ?, ?, ?)";
        int id = save(insert, agenda.getCargaHoraria(), agenda.getHoraInicio(),
            agenda.getHoraFim(), agenda.getTempoIntervalo());
        if (id > 0) {
            agenda.setId(id);
        }
    }
    
    public void alterar(Agenda agenda) throws SQLException {
        String update = "UPDATE agendas SET carga_horaria = ?, hora_inicio = ?, hora_fim = ?, tempo_intervalo = ? WHERE id = ?";
        update(update, agenda.getCargaHoraria(), agenda.getHoraInicio(),
            agenda.getHoraFim(), agenda.getTempoIntervalo());
    }

    public Agenda findById(int id) throws SQLException {
        String select = "SELECT * FROM agendas WHERE id = ?";
        Agenda agenda = null;
        Connection connection = getConnection();
        PreparedStatement stmt = connection.prepareStatement(select);

        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            agenda = new Agenda();
            agenda.setId(rs.getInt("id"));
            agenda.setCargaHoraria(rs.getTime("carga_horaria"));
            agenda.setHoraInicio(rs.getTime("hora_inicio"));
            agenda.setHoraFim(rs.getTime("hora_fim"));
            agenda.setTempoIntervalo(rs.getTime("tempo_intervalo"));
        }

        rs.close();
        stmt.close();
        connection.close();

        return agenda;
    }
}
