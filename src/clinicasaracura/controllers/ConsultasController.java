/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicasaracura.controllers;

import clinicasaracura.dao.ConsultaDAO;
import clinicasaracura.models.Consulta;
import clinicasaracura.models.Cliente;
import clinicasaracura.models.Medico;
import java.sql.SQLException;
import java.sql.Timestamp;
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
    
    public Consulta criarConsulta(String dataHoraConsulta, Medico medico, Cliente cliente) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Timestamp data = new Timestamp(sdf.parse(dataHoraConsulta).getTime());
            Consulta novaConsulta = new Consulta(data, medico, cliente);            
            this.consultaDAO.salvar(novaConsulta);
            return novaConsulta;
        } catch (ParseException ex) {
            System.out.println("ConsultasController: Falha ao converter data.");
            System.out.println(ex);
        } catch (SQLException ex) {
            System.out.println("ConsultasController: Falha ao salvar consulta.");
            System.out.println(ex);
        }

        return null;
    }
    
}
