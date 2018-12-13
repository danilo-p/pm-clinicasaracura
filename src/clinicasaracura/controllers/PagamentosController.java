/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicasaracura.controllers;

import clinicasaracura.dao.PagamentoDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Bernardo Senna
 */
public class PagamentosController {
    
    private final PagamentoDAO pagamentoDAO;

    public PagamentosController() {
        this.pagamentoDAO = new PagamentoDAO();
    }
    
    
    
}
