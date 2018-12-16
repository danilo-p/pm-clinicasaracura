/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicasaracura.views;

import clinicasaracura.controllers.ConsultasController;
import clinicasaracura.models.Cliente;
import clinicasaracura.models.Consulta;
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
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Bernardo Senna
 */
public class AgendaMedicoView extends JPanel {

    public AgendaMedicoView(Medico medico, JPanel voltarView, int controleSemana) {

        this.setBorder(new EmptyBorder(15, 15, 15, 15));
        this.setLayout(new BorderLayout(15, 15));

        JPanel tituloPanel = new JPanel();
        tituloPanel.setLayout(new GridLayout(1, 2, 0, 0));

        JLabel titulo = new JLabel("Agenda " + medico.getNome());
        titulo.setAlignmentX(CENTER_ALIGNMENT);
        tituloPanel.add(titulo);

        JPanel controlesPanel = new JPanel();
        controlesPanel.setLayout(new GridLayout(1, 2, 0, 0));
        JButton semanaAnterior = new JButton("Semana ant.");
        semanaAnterior.addActionListener((ActionEvent e) -> {
            Router.getInstance().goToView(new AgendaMedicoView(medico, voltarView, controleSemana - 1));
        });
        controlesPanel.add(semanaAnterior);
        JButton proximaSemana = new JButton("Próx. semana");
        proximaSemana.addActionListener((ActionEvent e) -> {
            Router.getInstance().goToView(new AgendaMedicoView(medico, voltarView, controleSemana + 1));
        });
        controlesPanel.add(proximaSemana);
        tituloPanel.add(controlesPanel);

        this.add(tituloPanel, BorderLayout.NORTH);

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0); // ! clear would not reset the hour of day !
        cal.clear(Calendar.MINUTE);
        cal.clear(Calendar.SECOND);
        cal.clear(Calendar.MILLISECOND);
        cal.add(Calendar.WEEK_OF_MONTH, controleSemana);

        String aux = "Horário";
        String[] cargaHoraria = medico.getAgenda().getCargaHoraria().split(",");
        int[] cargaHorariaInteiros = new int[cargaHoraria.length];
        for (int i = 0; i < cargaHorariaInteiros.length; i++) {
            cargaHorariaInteiros[i] = Integer.parseInt(cargaHoraria[i]);
        }
        for (int dia : cargaHorariaInteiros) {
            cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
            cal.add(Calendar.DATE, dia);
            String diaNome = "";
            switch (dia) {
                case 1:
                    diaNome = "Seg. ";
                    break;
                case 2:
                    diaNome = "Ter. ";
                    break;
                case 3:
                    diaNome = "Qua. ";
                    break;
                case 4:
                    diaNome = "Qui. ";
                    break;
                case 5:
                    diaNome = "Sex. ";
                    break;
            }
            aux += "," + diaNome + cal.get(Calendar.DAY_OF_MONTH) + "/" + (cal.get(Calendar.MONTH) + 1);
        }
        String[] titulos = aux.split(",");

        int intervalo = medico.getAgenda().getTempoIntervalo();
        int n_linhas = (60 / intervalo) * 6;
        Object[][] dados = new Object[n_linhas][4];
        dados[0][0] = medico.getAgenda().getHoraInicio();
        for (int j = 1; j < n_linhas; j++) {
            dados[j][0] = new Time(medico.getAgenda().getHoraInicio().getTime() + intervalo * j * 60 * 1000);
        }
        
        ConsultasController consultasController = new ConsultasController();
        cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
        cal.add(Calendar.DATE, cargaHorariaInteiros[0]);
        Timestamp cargaHorariaInicio = new Timestamp(cal.getTimeInMillis());
        cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
        cal.add(Calendar.DATE, cargaHorariaInteiros[cargaHorariaInteiros.length - 1]);
        Timestamp cargaHorariaFim = new Timestamp(cal.getTimeInMillis());
        List consultas = consultasController.getByMedico(medico, cargaHorariaInicio, cargaHorariaFim);
        for(int i = 0; i < consultas.size(); i++) {
            Consulta consulta = (Consulta) consultas.get(i);
            Timestamp dataConsulta = consulta.getData();

            cal.setTimeInMillis(dataConsulta.getTime());
            int diaSemanaConsultaIndice = cal.get(Calendar.DAY_OF_WEEK) - 1;

            int horaConsultaIndice = 0;
            for (int j = 1; j < n_linhas; j++) {
                String horaConsulta = new Time(dataConsulta.getTime()).toString();
                String horaDados = dados[j][0].toString();
                if (horaConsulta.equals(horaDados)) {
                    horaConsultaIndice = j;
                }
            }

            if (diaSemanaConsultaIndice > 0 && horaConsultaIndice > 0) {
                Cliente cliente = consulta.getCliente();
                dados[horaConsultaIndice][diaSemanaConsultaIndice] = cliente.getNome() + " - " + cliente.getTelefone();
            }
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
