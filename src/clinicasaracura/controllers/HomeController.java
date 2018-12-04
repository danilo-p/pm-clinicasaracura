/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicasaracura.controllers;

import clinicasaracura.dao.PessoaDAO;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author danilo
 */
public class HomeController {
    private final PessoaDAO pessoaDAO;

    public HomeController() {
        this.pessoaDAO = new PessoaDAO();
    }

    public List getPessoas() {
        try {
            return this.pessoaDAO.findPessoas();
        } catch (SQLException ex) {
            return null;
        }
    }
}
