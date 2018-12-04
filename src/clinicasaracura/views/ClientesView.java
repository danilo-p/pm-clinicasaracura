/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicasaracura.views;

import clinicasaracura.controllers.ClientesController;
import clinicasaracura.models.Cliente;
import clinicasaracura.models.Pessoa;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author danilo
 */
public class ClientesView extends JPanel {

    public ClientesView() {
        this.setBorder(new EmptyBorder(15, 15, 15, 15));
        this.setLayout(new BorderLayout(15, 15));

        JLabel titulo = new JLabel("Clientes");
        titulo.setAlignmentX(CENTER_ALIGNMENT);
        this.add(titulo, BorderLayout.NORTH);
        
        String[] titulos = {"ID", "Nome", "CPF", "Telefone"};
        ClientesController clientesController = new ClientesController();
        List clientes = clientesController.getClientes();
        Object[][] linhas = new Object[clientes.size()][4];
        for (int i = 0; i < clientes.size(); i++) {
            Cliente cliente = (Cliente) clientes.get(i);
            Pessoa pessoa = cliente.getPessoa();
            linhas[i][0] = cliente.getId();
            linhas[i][1] = pessoa.getNome();
            linhas[i][2] = pessoa.getCpf();
            linhas[i][3] = pessoa.getTelefone();
        }
        
        JTable clientesTable;
        clientesTable = new JTable(linhas, titulos);
        JScrollPane scrollPane = new JScrollPane(clientesTable);
        clientesTable.setFillsViewportHeight(true);
        this.add(scrollPane, BorderLayout.CENTER);

        JButton voltarButton = new JButton("Voltar");
        voltarButton.addActionListener((ActionEvent e) -> {
            Router.getInstance().goToView(new HomeView());
        });
        this.add(voltarButton, BorderLayout.SOUTH);
    }
}
