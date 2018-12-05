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
import static java.awt.Component.CENTER_ALIGNMENT;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author danilo
 */
public class NovoClienteView extends JPanel {

    public NovoClienteView() {
        this.setBorder(new EmptyBorder(15, 15, 15, 15));
        this.setLayout(new BorderLayout(15, 15));

        JLabel titulo = new JLabel("Novo cliente");
        titulo.setAlignmentX(CENTER_ALIGNMENT);
        this.add(titulo, BorderLayout.NORTH);

        JPanel fieldsPanel = new JPanel();

        JLabel nomeLabel = new JLabel("Nome:");
        fieldsPanel.add(nomeLabel);
        JTextField nomeField = new JTextField(1);
        fieldsPanel.add(nomeField);
        
        JLabel cpfLabel = new JLabel("CPF:");
        fieldsPanel.add(cpfLabel);
        JTextField cpfField = new JTextField(1);
        fieldsPanel.add(cpfField);
        
        JLabel telefoneLabel = new JLabel("Telefone:");
        fieldsPanel.add(telefoneLabel);
        JTextField telefoneField = new JTextField(1);
        fieldsPanel.add(telefoneField);

        this.add(fieldsPanel, BorderLayout.CENTER);

        JPanel rodapePanel = new JPanel();
        rodapePanel.setLayout(new GridLayout(1, 2, 0, 0));

        JButton voltarButton = new JButton("Voltar");
        voltarButton.addActionListener((ActionEvent e) -> {
            Router.getInstance().goToView(new HomeView());
        });
        rodapePanel.add(voltarButton);

        ClientesController clientesController = new ClientesController();
        JButton cadastrarButton = new JButton("Novo");
        cadastrarButton.addActionListener((ActionEvent e) -> {
            clientesController.criarCliente(nomeField.getText(), cpfField.getText(), telefoneField.getText());
            Router.getInstance().goToView(new ClientesView());
        });
        rodapePanel.add(cadastrarButton);

        this.add(rodapePanel, BorderLayout.SOUTH);
    }
}
