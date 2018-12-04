/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicasaracura.controllers;

import clinicasaracura.dao.ClienteDAO;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author danilo
 */
public class ClientesController {
    private final ClienteDAO clienteDAO;

    public ClientesController() {
        this.clienteDAO = new ClienteDAO();
    }

    public List getClientes() {
        try {
            return this.clienteDAO.findClientes();
        } catch (SQLException ex) {
            return null;
        }
    }
}
