/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicasaracura.controllers;

import clinicasaracura.dao.ClienteDAO;
import clinicasaracura.models.Agenda;
import clinicasaracura.models.Cliente;
import clinicasaracura.models.Pessoa;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
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

    public void criarCliente(String nome, String cpf, String telefone) {
        Pessoa novaPessoa = new Pessoa(nome, cpf, telefone);
        Agenda novaAgenda = new Agenda();
        novaPessoa.setAgenda(novaAgenda);
        Cliente novoCliente = new Cliente(novaPessoa);
        try {
            this.clienteDAO.salvar(novoCliente);
        } catch (SQLException ex) {
            System.out.println("ClientesController: Falha ao salvar cliente.");
            System.out.println(ex);
        }
    }

    public List getClientes() {
        try {
            return this.clienteDAO.findClientes();
        } catch (SQLException ex) {
            System.out.println("ClientesController: Falha ao recuperar clientes.");
            System.out.println(ex);
            return new ArrayList<>();
        }
    }
}
