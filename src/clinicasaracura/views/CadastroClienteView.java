/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicasaracura.views;

import clinicasaracura.controllers.ClientesController;
import java.awt.BorderLayout;
import java.awt.Color;
import static java.awt.Component.CENTER_ALIGNMENT;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 * Página de cadastro de cliente
 *
 * @author danilo
 */
public class CadastroClienteView extends JPanel {
    JTextField nomeField;
    JTextField cpfField;
    JTextField telefoneField;
    JLabel cpfError;
    JLabel telefoneError;
    JLabel nomeError;

    public CadastroClienteView() {
        this.setViewLayout();
        this.renderTitle();
        this.renderFields();
        this.renderFooter();
    }
    
    private void setViewLayout() {
        this.setBorder(new EmptyBorder(15, 15, 15, 15));
        this.setLayout(new BorderLayout(15, 15));
    }
    
    private void renderTitle() {
        JLabel titulo = new JLabel("Novo cliente");
        titulo.setAlignmentX(CENTER_ALIGNMENT);
        this.add(titulo, BorderLayout.NORTH);
    }
    
    private void renderFields() {
        JPanel fieldsPanel = new JPanel();
        fieldsPanel.setLayout(new BoxLayout(fieldsPanel, BoxLayout.Y_AXIS));

        JPanel nomeFieldPanel = new JPanel();
        JLabel nomeLabel = new JLabel("Nome completo:");
        nomeFieldPanel.add(nomeLabel);
        this.nomeField = new JTextField(1);
        this.nomeField.setColumns(20);
        nomeFieldPanel.add(this.nomeField);
        this.nomeError = new JLabel("Insira o nome completo do paciente");
        this.nomeError.setForeground(Color.red);
        this.nomeError.setVisible(false);
        nomeFieldPanel.add(this.nomeError);
        fieldsPanel.add(nomeFieldPanel);

        JPanel cpfFieldPanel = new JPanel();
        JLabel cpfLabel = new JLabel("CPF:");
        cpfFieldPanel.add(cpfLabel);
        this.cpfField = new JTextField(1);
        this.cpfField.setColumns(20);
        cpfFieldPanel.add(this.cpfField);
        this.cpfError = new JLabel("Insira um CPF válido no formato XXX.XXX.XXX-XX");
        this.cpfError.setForeground(Color.red);
        this.cpfError.setVisible(false);
        cpfFieldPanel.add(this.cpfError);
        fieldsPanel.add(cpfFieldPanel);

        JPanel telefoneFieldPanel = new JPanel();
        JLabel telefoneLabel = new JLabel("Telefone:");
        telefoneFieldPanel.add(telefoneLabel);
        this.telefoneField = new JTextField(1);
        this.telefoneField.setColumns(20);
        telefoneFieldPanel.add(this.telefoneField);
        this.telefoneError = new JLabel("Insira o telefone no formato (XX) XXXXX-XXXX");
        this.telefoneError.setForeground(Color.red);
        this.telefoneError.setVisible(false);
        telefoneFieldPanel.add(this.telefoneError);
        fieldsPanel.add(telefoneFieldPanel);

        this.add(fieldsPanel, BorderLayout.CENTER);
    }
    
    private void renderFooter() {
        JPanel rodapePanel = new JPanel();
        rodapePanel.setLayout(new GridLayout(1, 2, 0, 0));

        JButton voltarButton = new JButton("Voltar");
        voltarButton.addActionListener((ActionEvent e) -> {
            Router.getInstance().goToView(new ClientesView());
        });
        rodapePanel.add(voltarButton);

        ClientesController clientesController = new ClientesController();
        JButton cadastrarButton = new JButton("Novo");
        cadastrarButton.addActionListener((ActionEvent e) -> {
            if (this.validateFields()) {
                clientesController.criarCliente(
                    this.nomeField.getText(),
                    this.cpfField.getText(),
                    this.telefoneField.getText()
                );
                Router.getInstance().goToView(new ClientesView());
            }
        });
        rodapePanel.add(cadastrarButton);

        this.add(rodapePanel, BorderLayout.SOUTH);
    }
    
    private boolean validateFields() {
        return this.validateNomeField() & this.validateCpfField() & this.validateTelefoneField();
    }

    private boolean validateNomeField() {
        String nome = this.nomeField.getText();

        if (nome.length() > 0) { 
            this.nomeError.setVisible(false);
            return true;
        }

        this.nomeError.setVisible(true);
        return false;
    }
    
    private boolean validateCpfField() {
        String cpf = this.cpfField.getText();

        if (cpf.matches("[\\d]{3}[\\.][\\d]{3}[\\.][\\d]{3}[\\-][\\d]{2}")) { 
            this.cpfError.setVisible(false);
            return true;
        }

        this.cpfError.setVisible(true);
        return false;
    }
    
    private boolean validateTelefoneField() {
        String telefone = this.telefoneField.getText();

        if (telefone.matches("[\\(][\\d]{2}[\\)][\\s][\\d]{5}[\\-][\\d]{4}")) { 
            this.telefoneError.setVisible(false);
            return true;
        }

        this.telefoneError.setVisible(true);
        return false;
    }
}
