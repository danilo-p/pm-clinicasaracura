/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicasaracura.views;

import clinicasaracura.controllers.ClientesController;
import clinicasaracura.models.Cliente;
import clinicasaracura.models.Pessoa;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author danilo
 */
public class HomeView extends JPanel {
    private final ClientesController clientesController;
    private final List clientes;

    public HomeView() {
        clientesController = new ClientesController();
        clientes = clientesController.getClientes();
        for (int i = 0; i < clientes.size(); i++) {
            Cliente cliente = (Cliente) clientes.get(i);
            this.add(new JLabel(cliente.getPessoa().getNome()));
        }
    }
}
