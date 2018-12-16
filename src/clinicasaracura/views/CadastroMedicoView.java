/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicasaracura.views;

import clinicasaracura.controllers.MedicosController;
import clinicasaracura.controllers.EspecialidadesController;
import clinicasaracura.dao.EspecialidadeDAO;
import clinicasaracura.models.Especialidade;
import java.awt.BorderLayout;
import java.awt.Color;
import static java.awt.Component.CENTER_ALIGNMENT;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

/**
 * Página de cadastro de medico
 *
 * @author Bernardo Senna
 */
public class CadastroMedicoView extends JPanel {
    EspecialidadesController especialidadesController = new EspecialidadesController();
    private final EspecialidadeDAO especialidadeDAO = new EspecialidadeDAO();
    private final String[] intervalo = {"15","20","30"};
    private final String[] dias = new String[3];
    private JTextField nomeField, cpfField, telefoneField, horaInicioField, especialidadeField;
    private JCheckBox seg, ter, qua, qui, sex;
    private JComboBox<String> especialidadeComboBox, intervaloComboBox;
    private JLabel nomeError, cpfError, telefoneError, horaInicioError,
        especialidadeError, cargaHorariaError, intervaloError;
    
    public CadastroMedicoView() {
        this.setViewLayout();
        this.renderTitle();
        this.renderFields();
        this.renderFooter();        
    }
    
    private void setViewLayout() {
        this.setBorder(new EmptyBorder(10, 10, 10, 10));
        this.setLayout(new BorderLayout(5, 5));
    }
    
    private void renderTitle() {
        JLabel titulo = new JLabel("Novo médico");
        titulo.setAlignmentX(CENTER_ALIGNMENT);
        this.add(titulo, BorderLayout.NORTH);
    }
    
    private void renderFields() {
        JPanel fieldsPanel = new JPanel();
        fieldsPanel.setLayout(new BoxLayout(fieldsPanel, BoxLayout.Y_AXIS));
        fieldsPanel.setPreferredSize(new Dimension(500, 600));
        JScrollPane scrollPane = new JScrollPane(
            fieldsPanel,
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        );
        scrollPane.setPreferredSize(new Dimension(500, 500));

        JPanel nomeFieldPanel = new JPanel();
        JLabel nomeLabel = new JLabel("Nome completo:");
        nomeFieldPanel.add(nomeLabel);
        nomeField = new JTextField(1);
        nomeField.setColumns(20);
        nomeFieldPanel.add(nomeField);
        nomeError = new JLabel("Insira o nome completo do paciente");
        nomeError.setForeground(Color.red);
        nomeError.setVisible(false);
        nomeFieldPanel.add(nomeError);
        fieldsPanel.add(nomeFieldPanel);

        JPanel cpfFieldPanel = new JPanel();
        JLabel cpfLabel = new JLabel("CPF:");
        cpfFieldPanel.add(cpfLabel);
        cpfField = new JTextField(1);
        cpfField.setColumns(20);
        cpfFieldPanel.add(cpfField);
        cpfError = new JLabel("Insira um CPF válido no formato XXX.XXX.XXX-XX");
        cpfError.setForeground(Color.red);
        cpfError.setVisible(false);
        cpfFieldPanel.add(cpfError);
        fieldsPanel.add(cpfFieldPanel);

        JPanel telefoneFieldPanel = new JPanel();
        JLabel telefoneLabel = new JLabel("Telefone:");
        telefoneFieldPanel.add(telefoneLabel);
        telefoneField = new JTextField(1);
        telefoneField.setColumns(20);
        telefoneFieldPanel.add(telefoneField);
        telefoneError = new JLabel("Insira o telefone no formato (XX) XXXXX-XXXX");
        telefoneError.setForeground(Color.red);
        telefoneError.setVisible(false);
        telefoneFieldPanel.add(telefoneError);
        fieldsPanel.add(telefoneFieldPanel);
        
        JPanel horaInicioFieldPanel = new JPanel();
        JLabel horaInicioLabel = new JLabel("Hora início:");
        horaInicioFieldPanel.add(horaInicioLabel);
        horaInicioField = new JTextField(1);
        horaInicioField.setColumns(20);
        horaInicioFieldPanel.add(horaInicioField);
        horaInicioError = new JLabel("Insira a hora de início no formato HH:MM:SS");
        horaInicioError.setForeground(Color.red);
        horaInicioError.setVisible(false);
        horaInicioFieldPanel.add(horaInicioError);
        fieldsPanel.add(horaInicioFieldPanel);
        
        JPanel intervaloFieldPanel = new JPanel();
        JLabel intervaloLabel = new JLabel("Tempo intervalo:");
        intervaloFieldPanel.add(intervaloLabel);
        intervaloComboBox = new JComboBox<String>();
        intervaloFieldPanel.add(intervaloComboBox);
        intervaloError = new JLabel("Selecione uma opção de tempo de intervalo entre 15 e 30 minutos");
        intervaloError.setForeground(Color.red);
        intervaloError.setVisible(false);
        intervaloFieldPanel.add(intervaloError);
        fieldsPanel.add(intervaloFieldPanel);
        intervaloComboBox.addItem("");
        for (int i = 0; i < intervalo.length; i++) {
            String number = intervalo[i];
            intervaloComboBox.addItem(number); 
        }
        intervaloComboBox.addActionListener((ActionEvent e) -> {
            String textoIntervaloComboBox = (String) intervaloComboBox.getSelectedItem();
        });
        
        JPanel cargaHorariaFieldPanel = new JPanel();
        JLabel cargaHorariaLabel = new JLabel("Dias da semana:");
        cargaHorariaFieldPanel.add(cargaHorariaLabel);
        seg = new JCheckBox("S");
        cargaHorariaFieldPanel.add(seg);
        ter = new JCheckBox("T");
        cargaHorariaFieldPanel.add(ter);
        qua = new JCheckBox("Q");
        cargaHorariaFieldPanel.add(qua);
        qui = new JCheckBox("Q");
        cargaHorariaFieldPanel.add(qui);
        sex = new JCheckBox("S");
        cargaHorariaFieldPanel.add(sex);
        cargaHorariaError = new JLabel("Selecione entre 1 e 3 dias de trabalho para o médico");
        cargaHorariaError.setForeground(Color.red);
        cargaHorariaError.setVisible(false);
        cargaHorariaFieldPanel.add(cargaHorariaError);
        fieldsPanel.add(cargaHorariaFieldPanel);
        
        JPanel especialidadeFieldPanel = new JPanel();
        JLabel especialidadeLabel = new JLabel("Especialidade:");
        especialidadeFieldPanel.add(especialidadeLabel);
        especialidadeComboBox = new JComboBox<String>();
        especialidadeFieldPanel.add(especialidadeComboBox);
        especialidadeField = new JTextField(1);
        especialidadeField.setColumns(21);
        especialidadeField.setEnabled(false);
        especialidadeField.setBackground(Color.LIGHT_GRAY);
        especialidadeFieldPanel.add(especialidadeField);
        especialidadeError = new JLabel("Selecione a especialidade do médico");
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
        especialidadeComboBox.addItem("Outra");
        
        especialidadeComboBox.addActionListener((ActionEvent e) -> {
            
            String textoComboBox = (String) especialidadeComboBox.getSelectedItem();

            if( "Outra".equals(textoComboBox)){        
                especialidadeField.setEnabled(true);
                especialidadeField.setBackground(Color.WHITE);
            }
            else{  
                especialidadeField.setText("");
                especialidadeField.setEnabled(false);
                especialidadeField.setBackground(Color.LIGHT_GRAY);
            }
        });

        this.add(scrollPane, BorderLayout.CENTER);
    }
    
    private void renderFooter() {
        JPanel rodapePanel = new JPanel();
        rodapePanel.setLayout(new GridLayout(1, 2, 0, 0));

        JButton voltarButton = new JButton("Voltar");
        voltarButton.addActionListener((ActionEvent e) -> {
            Router.getInstance().goToView(new MedicosView());
        });
        rodapePanel.add(voltarButton);

        MedicosController medicosController = new MedicosController();
        JButton cadastrarButton = new JButton("Novo");
        cadastrarButton.addActionListener((ActionEvent e) -> {
            
            if (!this.validateFields()) {
                return;
            }
            
            Especialidade especialidade = null;
        
            String textoComboBox = (String) especialidadeComboBox.getSelectedItem();
            String textoIntervaloComboBox = (String) intervaloComboBox.getSelectedItem();
            
            if( ("Outra".equals(textoComboBox)) && !"".equals(especialidadeField.getText())){
                
                especialidadesController.criarEspecialidade(especialidadeField.getText());

                try {
                    especialidade = this.especialidadeDAO.findByName(especialidadeField.getText());
                } catch (SQLException ex) {
                    Logger.getLogger(CadastroMedicoView.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else{

                try {
                    especialidade = this.especialidadeDAO.findByName((String) especialidadeComboBox.getSelectedItem());
                } catch (SQLException ex) {
                    Logger.getLogger(CadastroMedicoView.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
            
            String cargaHoraria = "";
             if (seg.isSelected()){
                cargaHoraria += "1,";
            }
            if (ter.isSelected()){
                cargaHoraria += "2,";
            }
            if (qua.isSelected()){
                cargaHoraria += "3,";
            }
            if (qui.isSelected()){
                cargaHoraria += "4,";
            }
            if (sex.isSelected()){
                cargaHoraria += "5,";
            }
            cargaHoraria = cargaHoraria.substring(0, cargaHoraria.length() - 1);
            
            int intervalo = Integer.parseInt(textoIntervaloComboBox);
            
            medicosController.criarMedico(nomeField.getText(), cpfField.getText(), telefoneField.getText(), especialidade, horaInicioField.getText(), intervalo, cargaHoraria);
            Router.getInstance().goToView(new MedicosView());
        });
        rodapePanel.add(cadastrarButton);

        this.add(rodapePanel, BorderLayout.SOUTH);
    }
    
    private boolean validateFields() {
        return (
            this.validateNomeField() &
            this.validateCpfField() &
            this.validateTelefoneField() &
            this.validateHoraInicioField() &
            this.validateEspecialidadeComboBox() &
            this.validateCargaHorariaField() &
            this.validateIntervaloField()
        );
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
        
    private boolean validateHoraInicioField() {
        String horaInicio = this.horaInicioField.getText();

        if (horaInicio.matches("[\\d]{2}\\:[\\d]{2}\\:[\\d]{2}")) { 
            this.horaInicioError.setVisible(false);
            return true;
        }

        this.horaInicioError.setVisible(true);
        return false;
    }
        
    private boolean validateEspecialidadeComboBox() {
        System.out.println("askdhfjaldskfj 1");
        String especialidade = (String) this.especialidadeComboBox.getSelectedItem();
        String novaEspecialidade = especialidadeField.getText();
        
        System.out.println("askdhfjaldskfj 2 " + especialidade + " " + novaEspecialidade);

        if(especialidade.length() == 0) {
            this.especialidadeError.setText("Selecione a especialidade do médico");
            this.especialidadeError.setVisible(true);
            System.out.println("askdhfjaldskfj 3");
            return false;
        }
        

        if(("Outra".equals(especialidade)) && novaEspecialidade.length() == 0) {
            this.especialidadeError.setText("Insira a nova especialidade ou selecione outra opção");
            this.especialidadeError.setVisible(true);
            System.out.println("askdhfjaldskfj 4");
            return false;
        }
        
        System.out.println("askdhfjaldskfj 5");

        this.especialidadeError.setVisible(false);
        return true;
    }
        
    private boolean validateCargaHorariaField() {
        int cont = 0;
        if (seg.isSelected()) cont++;
        if (ter.isSelected()) cont++;
        if (qua.isSelected()) cont++;
        if (qui.isSelected()) cont++;
        if (sex.isSelected()) cont++;
        
        if (cont == 0 || cont > 3) {
            this.cargaHorariaError.setVisible(true);
            return false;
        }
        
        this.cargaHorariaError.setVisible(false);
        return true;
    }
        
    private boolean validateIntervaloField() {
        String intervalo = (String) intervaloComboBox.getSelectedItem();

        if (intervalo.length() > 0) { 
            this.intervaloError.setVisible(false);
            return true;
        }

        this.intervaloError.setVisible(true);
        return false;
    }   
}
