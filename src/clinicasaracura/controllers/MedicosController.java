/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicasaracura.controllers;

import clinicasaracura.dao.MedicoDAO;
import clinicasaracura.models.Agenda;
import clinicasaracura.models.Medico;
import clinicasaracura.models.Especialidade;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Bernardo Senna
 */
public class MedicosController {

    private final MedicoDAO medicoDAO;

    public MedicosController() {
        this.medicoDAO = new MedicoDAO();
    }

    public void criarMedico(String nome, String cpf, String telefone, String nomeEspecialidade) {
        Medico novoMedico = new Medico();

        novoMedico.setNome(nome);
        novoMedico.setCpf(cpf);
        novoMedico.setTelefone(telefone);
        
        Especialidade novaEspecialidade = new Especialidade();
        novoMedico.setEspecialidade(novaEspecialidade);

        Agenda novaAgenda = new Agenda();
        novoMedico.setAgenda(novaAgenda);

        try {
            this.medicoDAO.salvar(novoMedico);
        } catch (SQLException ex) {
            System.out.println("MedicosController: Falha ao salvar medico.");
            System.out.println(ex);
        }
    }

    public List getMedicos() {
        try {
            return this.medicoDAO.findMedicos();
        } catch (SQLException ex) {
            System.out.println("MedicosController: Falha ao recuperar medicos.");
            System.out.println(ex);
            return new ArrayList<>();
        }
    }
}
