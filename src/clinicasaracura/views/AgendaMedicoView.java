/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicasaracura.views;

import clinicasaracura.controllers.MedicosController;
import clinicasaracura.models.Medico;
import clinicasaracura.models.Pessoa;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Ana Luisa Rodrigues
 */
public class AgendaMedicoView extends JPanel{
    public AgendaMedicoView(Medico medico){
        
        this.setBorder(new EmptyBorder(15, 15, 15, 15));
        this.setLayout(new BorderLayout(15, 15));

        JPanel tituloPanel = new JPanel();
        tituloPanel.setLayout(new GridLayout(1, 2, 0, 0));

        JLabel titulo = new JLabel("Agenda " + medico.getNome());
        titulo.setAlignmentX(CENTER_ALIGNMENT);
        tituloPanel.add(titulo);
        
        this.add(tituloPanel, BorderLayout.NORTH);
        
        //System.out.print(medico.getAgenda().getCargaHoraria());
        //System.out.print(medico.getAgenda().getTempoIntervalo());
        
        String[] dias = medico.getAgenda().getCargaHoraria().split(", ");
        System.out.print(Arrays.toString(dias));
        
        //String[] titulos = {"ID", "Nome", "CPF", "Telefone", "Especialidade"};
//        Object[][] linhas = new Object[medicos.size()][5];
//        for (int i = 0; i < medicos.size(); i++) {
//            Medico medico = (Medico) medicos.get(i);
//            linhas[i][0] = medico.getId();
//            linhas[i][1] = medico.getNome();
//            linhas[i][2] = medico.getCpf();
//            linhas[i][3] = medico.getTelefone();
//            linhas[i][4] = medico.getEspecialidade().getNome();
//        }
        
        //rodape com botao voltar
        JPanel rodapePanel = new JPanel();
        rodapePanel.setLayout(new GridLayout(1, 2, 0, 0));
        JButton voltarButton = new JButton("Voltar");
        voltarButton.addActionListener((ActionEvent e) -> {
            Router.getInstance().goToView(new MedicosView());
        });
        rodapePanel.add(voltarButton);
        this.add(rodapePanel, BorderLayout.SOUTH);
    }
}
