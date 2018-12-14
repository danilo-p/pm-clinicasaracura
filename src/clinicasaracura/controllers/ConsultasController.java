/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicasaracura.controllers;

import clinicasaracura.dao.ConsultaDAO;
import clinicasaracura.models.Agenda;
import clinicasaracura.models.Consulta;
import clinicasaracura.models.Cliente;
import clinicasaracura.models.Medico;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author Bernardo Senna
 */
public class ConsultasController {
    
    private final ConsultaDAO consultaDAO;

    public ConsultasController() {
        this.consultaDAO = new ConsultaDAO();
    }
    
    public void criarConsulta(String dataHoraConsulta, Medico medico, Cliente cliente) throws ParseException {
        
        Consulta novaConsulta = new Consulta();
        //Tentei fazer a conversão abaixo mas não rola, cai no ParseException
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");    
        Date data = (Date) format.parse(dataHoraConsulta);
        
        novaConsulta.setData(data);
        novaConsulta.setMedico(medico);
        novaConsulta.setCliente(cliente);

        try {
            this.consultaDAO.salvar(novaConsulta);
        } catch (SQLException ex) {
            System.out.println("ConsultasController: Falha ao salvar consulta.");
            System.out.println(ex);
        }
    }
    
}
