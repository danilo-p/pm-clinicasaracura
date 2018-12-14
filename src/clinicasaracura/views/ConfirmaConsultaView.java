/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicasaracura.views;

import clinicasaracura.controllers.ClientesController;
import clinicasaracura.controllers.PagamentosController;
import clinicasaracura.models.Cliente;
import clinicasaracura.models.Medico;
import java.awt.BorderLayout;
import static java.awt.Component.CENTER_ALIGNMENT;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Bernardo Senna
 */
public class ConfirmaConsultaView extends JPanel{
    
    public ConfirmaConsultaView(Medico medico, String dataHoraConsulta){
          
	JPanel clienteFieldPanel = new JPanel();        
        JLabel ClienteLabel = new JLabel("Selecione o cliente:");
        clienteFieldPanel.add(ClienteLabel);
        JComboBox<String> clienteComboBox = new JComboBox<String>();
        clienteFieldPanel.add(clienteComboBox);
		
	ClientesController clientesController = new ClientesController();
        List clientes = clientesController.getClientes();
        for (int i = 0; i < clientes.size(); i++) {
            Cliente cliente = (Cliente) clientes.get(i);
            clienteComboBox.addItem(cliente.getNome()); 
        }
        this.add(clienteFieldPanel, BorderLayout.NORTH);
        
        JPanel pagamentoFieldPanel = new JPanel();        
        JLabel PagamentoLabel = new JLabel("Selecione a forma de pagamento:");
        pagamentoFieldPanel.add(PagamentoLabel);
        JComboBox<String> pagamentoComboBox = new JComboBox<String>();
        pagamentoFieldPanel.add(pagamentoComboBox);
        
        pagamentoComboBox.addItem("Particular");
        pagamentoComboBox.addItem("Convenio");
        this.add(pagamentoFieldPanel, BorderLayout.CENTER);
        
        //metodo pra buscar id cliente para cadastrar no banco de dados
        //finalizar cadastro da consulta com pagamentos
        //exibir dados na tela fica por conta do Danilo
        
        
        //RODAPÉ....
        JPanel botoesFieldPanel = new JPanel();
        JButton voltarButton = new JButton("Voltar");
        voltarButton.addActionListener((ActionEvent e) -> {
            Router.getInstance().goToView(new AgendaMedicoViewConsulta(medico));
        });
        botoesFieldPanel.add(voltarButton);
        
        JButton BuscarButton = new JButton("Confirmar");
        BuscarButton.addActionListener((ActionEvent e) -> {
            //insere no banco de dados os detalhes da consulta e retorna à tela principal
            Router.getInstance().goToView(new HomeView());
        });
        botoesFieldPanel.add(BuscarButton);
        this.add(botoesFieldPanel, BorderLayout.SOUTH);
    }
    
}
