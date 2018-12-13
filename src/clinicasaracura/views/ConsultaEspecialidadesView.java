/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicasaracura.views;

import clinicasaracura.controllers.MedicosController;
import clinicasaracura.models.Medico;
import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Bernardo Senna
 */
public class ConsultaEspecialidadesView extends JPanel{
    
    public ConsultaEspecialidadesView(int id){
        
        this.setBorder(new EmptyBorder(15, 15, 15, 15));
        this.setLayout(new BorderLayout(15, 15));
        
        MedicosController medicosController = new MedicosController();
        List medicos = medicosController.getMedicosByEspecialidadeId(id);

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
        
      
        JTable medicosTable = new JTable(linhas, titulos);
        medicosTable.setDefaultEditor(Object.class, null);
        medicosTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                JTable table =(JTable) mouseEvent.getSource();
                Point point = mouseEvent.getPoint();
                int row = table.rowAtPoint(point);
                if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
                    Medico medicoSelecionado = (Medico) medicos.get(table.getSelectedRow());
                    Router.getInstance().goToView(new AgendaMedicoViewConsulta(medicoSelecionado));
                }
            }
        });
        
        JScrollPane scrollPane = new JScrollPane(medicosTable);
        medicosTable.setFillsViewportHeight(true);
        this.add(scrollPane, BorderLayout.CENTER);
        
        JButton voltarButton = new JButton("Voltar");
        voltarButton.addActionListener((ActionEvent e) -> {
            Router.getInstance().goToView(new CadastroConsultaView());
        });
        this.add(voltarButton, BorderLayout.SOUTH);
    }
}
