/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicasaracura.views;

import clinicasaracura.controllers.MedicosController;
import clinicasaracura.controllers.EspecialidadesController;
import clinicasaracura.models.Medico;
import clinicasaracura.models.Especialidade;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;


/**
 *
 * @author Bernardo Senna
 */
public class CadastroConsultaView extends JPanel{
    
    public CadastroConsultaView(){
        
        this.setBorder(new EmptyBorder(15, 15, 15, 15));
        this.setLayout(new BorderLayout(15, 15));

        JPanel tituloPanel = new JPanel();
        tituloPanel.setLayout(new GridLayout(1, 2, 0, 0));

        JLabel titulo = new JLabel("Cadastro de consultas");
        titulo.setAlignmentX(CENTER_ALIGNMENT);
        tituloPanel.add(titulo);

        this.add(tituloPanel, BorderLayout.NORTH);
        
        JPanel fieldsPanel = new JPanel();
        fieldsPanel.setLayout(new BoxLayout(fieldsPanel, BoxLayout.Y_AXIS));
		
	JPanel especialidadeFieldPanel = new JPanel();
        JLabel especialidadeLabel = new JLabel("Especialidade:");
        especialidadeFieldPanel.add(especialidadeLabel);
        JComboBox<String> especialidadeComboBox = new JComboBox<String>();
        especialidadeFieldPanel.add(especialidadeComboBox);
        fieldsPanel.add(especialidadeFieldPanel);
        
        EspecialidadesController especialidadesController = new EspecialidadesController();
        List especialidades = especialidadesController.getEspecialidades();
        for (int i = 0; i < especialidades.size(); i++) {
            Especialidade especialidade = (Especialidade) especialidades.get(i);
            especialidadeComboBox.addItem(especialidade.getNome()); 
        }
        
        this.add(fieldsPanel, BorderLayout.CENTER);
        
        //List medicos = null;
        
	especialidadeComboBox.addActionListener((ActionEvent e) -> {
            
            Especialidade idMedico = null;
			
            String textoComboBox = (String) especialidadeComboBox.getSelectedItem();
			
            MedicosController medicosController = new MedicosController();

            idMedico = (Especialidade) especialidadesController.getEspecialidadesByName(textoComboBox);
            
            List medicos = medicosController.getMedicosById(idMedico.getId());
            
            String[] titulos = {"ID", "Nome", "CPF", "Telefone", "Especialidade"};
        Object[][] linhas = new Object[medicos.size()][5];
        for (int i = 0; i < medicos.size(); i++) {
                Medico medico = (Medico) medicos.get(i);
                linhas[i][0] = medico.getId();
                linhas[i][1] = medico.getNome();
                linhas[i][2] = medico.getCpf();
                linhas[i][3] = medico.getTelefone();
                linhas[i][4] = medico.getEspecialidade().getNome();
        }
        
        JTable consultaTable = new JTable(linhas, titulos);
        consultaTable.setDefaultEditor(Object.class, null);
        consultaTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                JTable table =(JTable) mouseEvent.getSource();
                Point point = mouseEvent.getPoint();
                int row = table.rowAtPoint(point);
                    if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
                        Medico medicoSelecionado = (Medico) medicos.get(table.getSelectedRow());
                        System.out.println(medicoSelecionado.getNome());
                        Router.getInstance().goToView(new AgendaMedicoView(medicoSelecionado));
                    }
            }
        });
        
        
        JScrollPane scrollPane = new JScrollPane(consultaTable);
        consultaTable.setFillsViewportHeight(true);
        this.add(scrollPane, BorderLayout.CENTER);
        
        });
        
        

        JButton voltarButton = new JButton("Voltar");
        voltarButton.addActionListener((ActionEvent e) -> {
            Router.getInstance().goToView(new HomeView());
        });
        this.add(voltarButton, BorderLayout.SOUTH);
    }
}
