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
import java.awt.FlowLayout;
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
import java.awt.FlowLayout;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;

/**
 * Página de cadastro de cliente
 *
 * @author Bernardo Senna
 */
public class CadastroMedicoView extends JPanel {
    
    private final EspecialidadeDAO especialidadeDAO;
    
    private String[] intervalo;
    
    private JCheckBox seg,ter,qua,qui,sex;
    private String[] dias = new String[3];
    
    public CadastroMedicoView() {
        
        this.especialidadeDAO = new EspecialidadeDAO();
        this.intervalo = new String[]{"00:15","00:20","00:30"};
        
        this.setBorder(new EmptyBorder(10, 10, 10, 10));
        this.setLayout(new BorderLayout(5, 5));

        JLabel titulo = new JLabel("Novo médico");
        titulo.setAlignmentX(CENTER_ALIGNMENT);
        this.add(titulo, BorderLayout.NORTH);

        JPanel fieldsPanel = new JPanel();
        fieldsPanel.setLayout(new BoxLayout(fieldsPanel, BoxLayout.Y_AXIS));

        JPanel nomeFieldPanel = new JPanel();
        JLabel nomeLabel = new JLabel("Nome:");
        nomeFieldPanel.add(nomeLabel);
        JTextField nomeField = new JTextField(1);
        nomeField.setColumns(20);
        nomeFieldPanel.add(nomeField);
        fieldsPanel.add(nomeFieldPanel);

        JPanel cpfFieldPanel = new JPanel();
        JLabel cpfLabel = new JLabel("CPF:");
        cpfFieldPanel.add(cpfLabel);
        JTextField cpfField = new JTextField(1);
        cpfField.setColumns(20);
        cpfFieldPanel.add(cpfField);
        fieldsPanel.add(cpfFieldPanel);

        JPanel telefoneFieldPanel = new JPanel();
        JLabel telefoneLabel = new JLabel("Telefone:");
        telefoneFieldPanel.add(telefoneLabel);
        JTextField telefoneField = new JTextField(1);
        telefoneField.setColumns(20);
        telefoneFieldPanel.add(telefoneField);
        fieldsPanel.add(telefoneFieldPanel);
        
        JPanel horaInicioFieldPanel = new JPanel();
        JLabel horaInicioLabel = new JLabel("Hora início:");
        horaInicioFieldPanel.add(horaInicioLabel);
        JTextField horaInicioField = new JTextField(1);
        horaInicioField.setColumns(5);
        horaInicioFieldPanel.add(horaInicioField);
        fieldsPanel.add(horaInicioFieldPanel);
        
        JPanel horaFimFieldPanel = new JPanel();
        JLabel horaFimLabel = new JLabel("Hora fim:");
        horaFimFieldPanel.add(horaFimLabel);
        JTextField horaFimField = new JTextField(1);
        horaFimField.setColumns(5);
        horaFimFieldPanel.add(horaFimField);
        fieldsPanel.add(horaFimFieldPanel);
        
        JPanel intervaloFieldPanel = new JPanel();
        JLabel intervaloLabel = new JLabel("Tempo intervalo:");
        intervaloFieldPanel.add(intervaloLabel);
        JComboBox<String> intervaloComboBox = new JComboBox<String>();
        intervaloFieldPanel.add(intervaloComboBox);
        fieldsPanel.add(intervaloFieldPanel);
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
        fieldsPanel.add(cargaHorariaFieldPanel);
        
        JPanel especialidadeFieldPanel = new JPanel();
        JLabel especialidadeLabel = new JLabel("Especialidade:");
        especialidadeFieldPanel.add(especialidadeLabel);
        JComboBox<String> especialidadeComboBox = new JComboBox<String>();
        especialidadeFieldPanel.add(especialidadeComboBox);
        JTextField especialidadeField = new JTextField(1);
        especialidadeField.setColumns(20);
        especialidadeField.setEnabled(false);
        especialidadeField.setBackground(Color.LIGHT_GRAY);
        especialidadeFieldPanel.add(especialidadeField);
        fieldsPanel.add(especialidadeFieldPanel);
        
        EspecialidadesController especialidadesController = new EspecialidadesController();
        List especialidades = especialidadesController.getEspecialidades();
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

        this.add(fieldsPanel, BorderLayout.CENTER);

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
            
            int cont = 0;
            while (cont <= 2){
                if (seg.isSelected()){
                    dias[cont] = "Segunda-feira";
                    cont++;
                }
                if (ter.isSelected()){
                    dias[cont] = "Terça-feira";
                    cont++;
                }
                if (qua.isSelected()){
                    dias[cont] = "Quarta-feira";
                    cont++;
                }
                if (qui.isSelected()){
                    dias[cont] = "Quinta-feira";
                    cont++;
                }
                if (sex.isSelected()){
                    dias[cont] = "Sexta-feira";
                    cont++;
                }
            }
            
            String cargaHoraria = String.join(",", dias);
            
            medicosController.criarMedico(nomeField.getText(), cpfField.getText(), telefoneField.getText(), especialidade, horaInicioField.getText(), horaFimField.getText(), textoIntervaloComboBox, cargaHoraria);
            Router.getInstance().goToView(new MedicosView());
        });
        rodapePanel.add(cadastrarButton);

        this.add(rodapePanel, BorderLayout.SOUTH);
    }
}
