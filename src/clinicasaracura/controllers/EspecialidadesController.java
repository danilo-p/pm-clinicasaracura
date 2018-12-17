/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicasaracura.controllers;

import clinicasaracura.dao.EspecialidadeDAO;
import clinicasaracura.models.Especialidade;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Bernardo Senna
 */
public class EspecialidadesController {
    
    private final EspecialidadeDAO especialidadeDAO;

    public EspecialidadesController() {
        this.especialidadeDAO = new EspecialidadeDAO();
    }

    public void criarEspecialidade(String nome) {
        Especialidade novaEspecialidade = new Especialidade();

        novaEspecialidade.setNome(nome);

        try {
            this.especialidadeDAO.salvar(novaEspecialidade);
        } catch (SQLException ex) {
            System.out.println("EspecialidadesController: Falha ao salvar especialidades.");
            System.out.println(ex);
        }
    }

    public List getEspecialidades() {
        try {
            return this.especialidadeDAO.findEspecialidades();
        } catch (SQLException ex) {
            System.out.println("EspecialidadesController: Falha ao recuperar especialidades.");
            System.out.println(ex);
            return new ArrayList<>();
        }
    }
	
	public Especialidade getEspecialidadesByName(String nome) {
        try {
            return this.especialidadeDAO.findByName(nome);
        } catch (SQLException ex) {
            System.out.println("EspecialidadesController: Falha ao recuperar especialidades.");
            System.out.println(ex);
            return new Especialidade();
        }
    }
    
}
