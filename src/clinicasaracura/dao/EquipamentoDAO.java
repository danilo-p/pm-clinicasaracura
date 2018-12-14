/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicasaracura.dao;

import clinicasaracura.models.Especialidade;
import clinicasaracura.models.Equipamento;
import clinicasaracura.models.Agenda;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Vien
 */
public class EquipamentoDAO extends GenericDAO{
    private EspecialidadeDAO especialidadeDAO;
    private AgendaDAO agendaDAO;
    
    EquipamentoDAO()
    {
        this.especialidadeDAO = new EspecialidadeDAO();
        this.agendaDAO = new AgendaDAO();
    }
    
    public void salvarEquipamento(Equipamento equip) throws SQLException{
        Agenda agenda = equip.getAgenda();
        this.agendaDAO.salvar(agenda);
        String insert = "INSERT INTO equipamentos(nome, especialidade_id, agenda_id) VALUES(?,?,?)";
        int id = save(insert, equip.getNome(), equip.getEspecialidade().getId(), agenda.getId());
        if (id > 0){
            equip.setId(id);
        }
    }
    
    public List findEquipamentos() throws SQLException {
        List equipamentos = new ArrayList();

        String select = "SELECT * FROM equipamentos";

        Connection connection = getConnection();
        
        PreparedStatement stmt = connection.prepareStatement(select);
                
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Equipamento equip = new Equipamento();

            equip.setId(rs.getInt("id"));
            equip.setNome(rs.getString("nome"));
            
            int especialidadeId = rs.getInt("especialidade_id");
            Especialidade especialidade = this.especialidadeDAO.findById(especialidadeId);
            equip.setEspecialidade(especialidade);

            int agendaId = rs.getInt("agenda_id");
            Agenda agenda = this.agendaDAO.findById(agendaId);
            equip.setAgenda(agenda);

            equipamentos.add(equip);
        }

        rs.close();
        stmt.close();
        connection.close();

        return equipamentos;
    }
    
    public Equipamento findById(int id) throws SQLException {
        String select = "SELECT * FROM equipamentos WHERE id = ?";
        Equipamento equip = null;
        Connection connection = getConnection();
        PreparedStatement stmt = connection.prepareStatement(select);

        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            equip = new Equipamento();

            equip.setId(rs.getInt("id"));
            equip.setNome(rs.getString("nome"));
            
            int especialidadeId = rs.getInt("especialidade_id");
            Especialidade especialidade = this.especialidadeDAO.findById(especialidadeId);
            equip.setEspecialidade(especialidade);

            int agendaId = rs.getInt("agenda_id");
            Agenda agenda = this.agendaDAO.findById(agendaId);
            equip.setAgenda(agenda);
        }

        rs.close();
        stmt.close();
        connection.close();

        return equip;
    }

    public List findByEspecialidade(Especialidade especialidade) throws SQLException {
        List equipamentos = new ArrayList();
        
        String select = "SELECT * FROM equipamentos WHERE especialidade_id = ?";
        Connection connection = getConnection();
        PreparedStatement stmt = connection.prepareStatement(select);

        stmt.setInt(1, especialidade.getId());
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Equipamento equip = new Equipamento();

            equip.setId(rs.getInt("id"));
            equip.setNome(rs.getString("nome"));
            
            int especialidadeId = rs.getInt("especialidade_id");
            Especialidade espc = this.especialidadeDAO.findById(especialidadeId);
            equip.setEspecialidade(espc);

            int agendaId = rs.getInt("agenda_id");
            Agenda agenda = this.agendaDAO.findById(agendaId);
            equip.setAgenda(agenda);
            
            equipamentos.add(equip);
        }

        rs.close();
        stmt.close();
        connection.close();

        return equipamentos;
    }    
}
