/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicasaracura.views;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * Página Home com os botões que levam para as outras telas
 *
 * @author danilo
 */
public class HomeView extends JPanel {

    public HomeView() {
        this.setBorder(new EmptyBorder(15, 15, 15, 15));
        this.setLayout(new GridLayout(2, 2, 15, 15));
        
        JButton medicosButton = new JButton("Médicos");
        medicosButton.addActionListener((ActionEvent e) -> {
            Router.getInstance().goToView(new MedicosView());
        });
        this.add(medicosButton);

        JButton clientesButton = new JButton("Clientes");
        clientesButton.addActionListener((ActionEvent e) -> {
            Router.getInstance().goToView(new ClientesView());
        });
        this.add(clientesButton);
        
        JButton novaConsultaButton = new JButton("Nova Consulta");
        novaConsultaButton.addActionListener((ActionEvent e) -> {
            Router.getInstance().goToView(new CadastroConsultaView());
        });
        this.add(novaConsultaButton);

        this.add(new JButton("Novo Exame"));
    }
}
