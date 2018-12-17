/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicasaracura.controllers;

import clinicasaracura.dao.ClienteDAO;
import clinicasaracura.models.Agenda;
import clinicasaracura.models.Cliente;
import java.sql.SQLException;
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
        Cliente novoCliente = new Cliente();

        novoCliente.setNome(nome);
        novoCliente.setCpf(cpf);
        novoCliente.setTelefone(telefone);

        Agenda novaAgenda = new Agenda();
        novoCliente.setAgenda(novaAgenda);

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
    
    public Cliente getClienteByName(String nome) {
        try {
            return this.clienteDAO.findByName(nome);
        } catch (SQLException ex) {
            System.out.println("ClientesController: Falha ao recuperar cliente.");
            System.out.println(ex);
            return new Cliente();
        }
    }
}
