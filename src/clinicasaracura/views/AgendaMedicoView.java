/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicasaracura.views;

import clinicasaracura.models.Medico;
import java.awt.BorderLayout;
import static java.awt.Component.CENTER_ALIGNMENT;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import java.sql.Time;

/**
 *
 * @author Bernardo Senna
 */
public class AgendaMedicoView extends JPanel {

    public AgendaMedicoView(Medico medico, JPanel voltarView) {

        this.setBorder(new EmptyBorder(15, 15, 15, 15));
        this.setLayout(new BorderLayout(15, 15));

        JPanel tituloPanel = new JPanel();
        tituloPanel.setLayout(new GridLayout(1, 2, 0, 0));

        JLabel titulo = new JLabel("Agenda " + medico.getNome());
        titulo.setAlignmentX(CENTER_ALIGNMENT);
        tituloPanel.add(titulo);

        this.add(tituloPanel, BorderLayout.NORTH);

        String aux = "Horário,";
        aux = aux.concat(medico.getAgenda().getCargaHoraria());
        String[] titulos = aux.split(",");

        int intervalo = medico.getAgenda().getTempoIntervalo();
        int n_linhas = (60 / intervalo) * 6;
        Object[][] dados = new Object[n_linhas][4];
        dados[0][0] = medico.getAgenda().getHoraInicio();
        for (int j = 1; j < n_linhas; j++) {
            dados[j][0] = new Time(medico.getAgenda().getHoraInicio().getTime() + intervalo * j * 60 * 1000);
        }
        JTable medicosTable = new JTable(dados, titulos);
        medicosTable.setDefaultEditor(Object.class, null);
        medicosTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                JTable table = (JTable) mouseEvent.getSource();
                Point point = mouseEvent.getPoint();
                int row = table.rowAtPoint(point);
                if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {

                    //Aqui iremos chamar a tela pra confirmar a consulta...(comando abaixo)
                    Router.getInstance().goToView(new ConfirmaConsultaView(medico, "2018-12-12 10:00:00"));
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(medicosTable);
        medicosTable.setFillsViewportHeight(true);
        this.add(scrollPane, BorderLayout.CENTER);

        //rodape com botao voltar
        JPanel rodapePanel = new JPanel();
        rodapePanel.setLayout(new GridLayout(1, 2, 0, 0));
        JButton voltarButton = new JButton("Voltar");
        voltarButton.addActionListener((ActionEvent e) -> {
            Router.getInstance().goToView(voltarView);
        });
        rodapePanel.add(voltarButton);
        this.add(rodapePanel, BorderLayout.SOUTH);
    }
}
