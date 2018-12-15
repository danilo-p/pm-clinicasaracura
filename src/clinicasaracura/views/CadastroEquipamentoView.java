/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicasaracura.views;

import clinicasaracura.controllers.EquipamentosController;
import clinicasaracura.controllers.EspecialidadesController;
import clinicasaracura.dao.EspecialidadeDAO;
import clinicasaracura.models.Especialidade;

import java.awt.BorderLayout;
import java.awt.Color;
import static java.awt.Component.CENTER_ALIGNMENT;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Página de cadastro de equipamento
 *
 * @author Edu
 */
public class CadastroEquipamentoView extends JPanel {
    EspecialidadesController especialidadesController = new EspecialidadesController();
    private final EspecialidadeDAO especialidadeDAO = new EspecialidadeDAO();

    private JComboBox<String> especialidadeComboBox;
    private JTextField nomeField;
    private JLabel especialidadeError, nomeError;

    public CadastroEquipamentoView() {
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
        JLabel titulo = new JLabel("Novo equipamento");
        titulo.setAlignmentX(CENTER_ALIGNMENT);
        this.add(titulo, BorderLayout.NORTH);
    }

    private void renderFields() {
        JPanel fieldsPanel = new JPanel();
        fieldsPanel.setLayout(new BoxLayout(fieldsPanel, BoxLayout.Y_AXIS));

        JPanel nomeFieldPanel = new JPanel();
        JLabel nomeLabel = new JLabel("Nome do equipamento:");
        nomeFieldPanel.add(nomeLabel);
        this.nomeField = new JTextField(1);
        this.nomeField.setColumns(20);
        nomeFieldPanel.add(this.nomeField);
        this.nomeError = new JLabel("Insira o nome do equipamento.");
        this.nomeError.setForeground(Color.red);
        this.nomeError.setVisible(false);
        nomeFieldPanel.add(this.nomeError);
        fieldsPanel.add(nomeFieldPanel);

        JPanel especialidadeFieldPanel = new JPanel();
        JLabel especialidadeLabel = new JLabel("Especialidade:");
        especialidadeFieldPanel.add(especialidadeLabel);
        especialidadeComboBox = new JComboBox<String>();
        especialidadeFieldPanel.add(especialidadeComboBox);
        especialidadeError = new JLabel("Selecione a especialidade que utiliza este equipamento.");
        especialidadeError.setForeground(Color.red);
        especialidadeError.setVisible(false);
        especialidadeFieldPanel.add(especialidadeError);
        fieldsPanel.add(especialidadeFieldPanel);

        List especialidades = especialidadesController.getEspecialidades();
        especialidadeComboBox.addItem("");
        for (int i = 0; i < especialidades.size(); i++) {
            Especialidade especialidade = (Especialidade) especialidades.get(i);
            especialidadeComboBox.addItem(especialidade.getNome());
        }

        this.add(fieldsPanel, BorderLayout.CENTER);
    }

    private void renderFooter() {
        JPanel rodapePanel = new JPanel();
        rodapePanel.setLayout(new GridLayout(1, 2, 0, 0));

        JButton voltarButton = new JButton("Voltar");
        voltarButton.addActionListener((ActionEvent e) -> {
            Router.getInstance().goToView(new HomeView());
        });
        rodapePanel.add(voltarButton);

        EquipamentosController equipamentosController = new EquipamentosController();
        JButton cadastrarButton = new JButton("Novo");
        cadastrarButton.addActionListener((ActionEvent e) -> {
            try {
                Especialidade especialidade = this.especialidadeDAO.findByName((String) especialidadeComboBox.getSelectedItem());
                if (this.validateFields()) {
                equipamentosController.criarEquipamento(
                    this.nomeField.getText(),
                    especialidade
                    );
                Router.getInstance().goToView(new HomeView());
                }
            } catch (SQLException ex) {
                Logger.getLogger(CadastroMedicoView.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        rodapePanel.add(cadastrarButton);

        this.add(rodapePanel, BorderLayout.SOUTH);
    }

    private boolean validateFields() {
        return this.validateNomeField() & this.validateEspecialidadeComboBox();
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

    private boolean validateEspecialidadeComboBox() {
        String especialidade = (String) this.especialidadeComboBox.getSelectedItem();

        if(especialidade.length() == 0) {
            this.especialidadeError.setText("Selecione a especialidade do médico");
            this.especialidadeError.setVisible(true);
            return false;
        }

        this.especialidadeError.setVisible(false);
        return true;
    }
}
