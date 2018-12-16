/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicasaracura.views;

import clinicasaracura.controllers.MedicosController;
import clinicasaracura.models.Medico;
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


/**
 *
 * @author Bernardo Senna
 */
public class MedicosView extends JPanel{
    
    public MedicosView(){
        
        this.setBorder(new EmptyBorder(15, 15, 15, 15));
        this.setLayout(new BorderLayout(15, 15));

        JPanel tituloPanel = new JPanel();
        tituloPanel.setLayout(new GridLayout(1, 2, 0, 0));

        JLabel titulo = new JLabel("Médicos");
        titulo.setAlignmentX(CENTER_ALIGNMENT);
        tituloPanel.add(titulo);

        JButton novoButton = new JButton("Novo");
        novoButton.addActionListener((ActionEvent e) -> {
            Router.getInstance().goToView(new CadastroMedicoView());
        });
        tituloPanel.add(novoButton);

        this.add(tituloPanel, BorderLayout.NORTH);

        MedicosController medicosController = new MedicosController();
        List medicos = medicosController.getMedicos();

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
        JPanel voltarView = this;
        medicosTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                JTable table =(JTable) mouseEvent.getSource();
                Point point = mouseEvent.getPoint();
                int row = table.rowAtPoint(point);
                if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
                    Medico medicoSelecionado = (Medico) medicos.get(table.getSelectedRow());
                    System.out.println(medicoSelecionado.getNome());
                    Router.getInstance().goToView(new AgendaMedicoView(medicoSelecionado, voltarView, 0));
                }
            }
        });
        

        JScrollPane scrollPane = new JScrollPane(medicosTable);
        medicosTable.setFillsViewportHeight(true);
        this.add(scrollPane, BorderLayout.CENTER);

        JButton voltarButton = new JButton("Voltar");
        voltarButton.addActionListener((ActionEvent e) -> {
            Router.getInstance().goToView(new HomeView());
        });
        this.add(voltarButton, BorderLayout.SOUTH);
    }
}
