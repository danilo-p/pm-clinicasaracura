/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicasaracura.views;

import clinicasaracura.controllers.MedicosController;
import clinicasaracura.controllers.EspecialidadesController;
import clinicasaracura.models.Medico;
import clinicasaracura.models.Pessoa;
import clinicasaracura.models.Especialidade;
import clinicasaracura.dao.EspecialidadeDAO;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import static java.awt.Component.CENTER_ALIGNMENT;
import java.awt.FlowLayout;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.sql.Time;
import javax.swing.JCheckBox;
import javax.swing.JFrame;


/**
 *
 * @author Bernardo Senna
 */
 public class CadastroConsultaView extends JPanel{
        
        int especialidadeId;
        
	public CadastroConsultaView(){
	
        this.setBorder(new EmptyBorder(15, 15, 15, 15));
        this.setLayout(new BorderLayout(15, 15));
        
	//cria o combobox com as especialidades a serem escolhidas.
	JPanel especialidadeFieldPanel = new JPanel();        
        JLabel especialidadeLabel = new JLabel("Filtrar médicos por especialidade:");
        especialidadeFieldPanel.add(especialidadeLabel);
        JComboBox<String> especialidadeComboBox = new JComboBox<String>();
        especialidadeFieldPanel.add(especialidadeComboBox);
		
	//preenche o combobox com as especialidades existentes no banco de dados.
	EspecialidadesController especialidadesController = new EspecialidadesController();
        List especialidades = especialidadesController.getEspecialidades();
        for (int i = 0; i < especialidades.size(); i++) {
            Especialidade especialidade = (Especialidade) especialidades.get(i);
            especialidadeComboBox.addItem(especialidade.getNome()); 
        }	
	
        
        especialidadeComboBox.addActionListener((ActionEvent e) -> {
            
            String textoComboBox = (String) especialidadeComboBox.getSelectedItem();
			
            Especialidade especialidade = especialidadesController.getEspecialidadesByName(textoComboBox);
            
            especialidadeId = especialidade.getId();
            
        });
        
        //somente pra buscar a especialidade que ja vem "selecionada" no ComboBox
        String textoComboBox = (String) especialidadeComboBox.getSelectedItem();	
        Especialidade especialidade = especialidadesController.getEspecialidadesByName(textoComboBox);
        especialidadeId = especialidade.getId();
        
        JPanel botoesFieldPanel = new JPanel();
        JButton voltarButton = new JButton("Voltar");
        voltarButton.addActionListener((ActionEvent e) -> {
            Router.getInstance().goToView(new HomeView());
        });
        botoesFieldPanel.add(voltarButton);
        
        JButton BuscarButton = new JButton("Buscar");
        BuscarButton.addActionListener((ActionEvent e) -> {
            Router.getInstance().goToView(new ConsultaEspecialidadesView(especialidadeId));
        });
        botoesFieldPanel.add(BuscarButton);
        
        especialidadeFieldPanel.add(botoesFieldPanel);
        this.add(especialidadeFieldPanel);
        
    }
        
 }