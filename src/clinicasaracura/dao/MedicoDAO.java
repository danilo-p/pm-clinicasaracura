/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicasaracura.dao;

import clinicasaracura.models.Agenda;
import clinicasaracura.models.Especialidade;
import clinicasaracura.models.Medico;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Bernardo Senna
 */
public class MedicoDAO extends GenericDAO{
    
    private final PessoaDAO pessoaDAO;
    private final AgendaDAO agendaDAO;
    private final EspecialidadeDAO especialidadeDAO;
    
    public MedicoDAO() {
        this.pessoaDAO = new PessoaDAO();
        this.agendaDAO = new AgendaDAO();
        this.especialidadeDAO = new EspecialidadeDAO();
    }

    public void salvarMedico(Medico medico) throws SQLException {
        Agenda agenda = medico.getAgenda();
        this.agendaDAO.salvar(agenda);
        String insert = "INSERT INTO pessoas(nome, cpf, telefone, tipo, especialidade_id, agenda_id) VALUES(?,?,?,?,?,?)";
        int id = save(insert, medico.getNome(), medico.getCpf(), medico.getTelefone(), medico.getTipo(), medico.getEspecialidade().getId(), agenda.getId());
        if (id > 0) {
            medico.setId(id);
        }
    }

    public List findMedicos() throws SQLException {
        List pessoas = new ArrayList();

        String select = "SELECT * FROM pessoas WHERE tipo = 1";

        Connection connection = getConnection();
        
        PreparedStatement stmt = connection.prepareStatement(select);
                
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Medico medico = new Medico();

            medico.setId(rs.getInt("id"));
            medico.setNome(rs.getString("nome"));
            medico.setCpf(rs.getString("cpf"));
            medico.setTelefone(rs.getString("telefone"));
            medico.setTipo(rs.getInt("tipo"));
            
            int especialidadeId = rs.getInt("especialidade_id");
            Especialidade especialidade = this.especialidadeDAO.findById(especialidadeId);
            medico.setEspecialidade(especialidade);

            int agendaId = rs.getInt("agenda_id");
            Agenda agenda = this.agendaDAO.findById(agendaId);
            medico.setAgenda(agenda);

            pessoas.add(medico);
        }

        rs.close();
        stmt.close();
        connection.close();

        return pessoas;
    }
    
	public List findMedicosByEspecialidadeId(int id) throws SQLException {
        List pessoas = new ArrayList();

        String select = "SELECT * FROM pessoas WHERE tipo = 1 AND especialidade_id = ?";

        Connection connection = getConnection();
        
        PreparedStatement stmt = connection.prepareStatement(select);
        
	stmt.setInt(1, id);        
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Medico medico = new Medico();

            medico.setId(rs.getInt("id"));
            medico.setNome(rs.getString("nome"));
            medico.setCpf(rs.getString("cpf"));
            medico.setTelefone(rs.getString("telefone"));
            medico.setTipo(rs.getInt("tipo"));
            
            int especialidadeId = rs.getInt("especialidade_id");
            Especialidade especialidade = this.especialidadeDAO.findById(especialidadeId);
            medico.setEspecialidade(especialidade);

            int agendaId = rs.getInt("agenda_id");
            Agenda agenda = this.agendaDAO.findById(agendaId);
            medico.setAgenda(agenda);

            pessoas.add(medico);
        }

        rs.close();
        stmt.close();
        connection.close();

        return pessoas;
    }
    
    public Medico findById(int id) throws SQLException {
        String select = "SELECT * FROM pessoas WHERE id = ? AND tipo = 1";
        Medico medico = null;
        Connection connection = getConnection();
        PreparedStatement stmt = connection.prepareStatement(select);

        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            medico = new Medico();

            medico.setId(rs.getInt("id"));
            medico.setId(rs.getInt("id"));
            medico.setNome(rs.getString("nome"));
            medico.setCpf(rs.getString("cpf"));
            medico.setTelefone(rs.getString("telefone"));
            medico.setTipo(rs.getInt("tipo"));
            
            int especialidadeId = rs.getInt("especialidade_id");
            Especialidade especialidade = this.especialidadeDAO.findById(especialidadeId);
            medico.setEspecialidade(especialidade);

            int agendaId = rs.getInt("agenda_id");
            Agenda agenda = this.agendaDAO.findById(agendaId);
            medico.setAgenda(agenda);
        }

        rs.close();
        stmt.close();
        connection.close();

        return medico;
    }
    
}
