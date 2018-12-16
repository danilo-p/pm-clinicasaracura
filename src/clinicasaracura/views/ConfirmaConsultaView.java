/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicasaracura.views;

import clinicasaracura.controllers.ClientesController;
import clinicasaracura.controllers.ConsultasController;
import clinicasaracura.controllers.PagamentosController;
import clinicasaracura.models.Cliente;
import clinicasaracura.models.Consulta;
import clinicasaracura.models.Medico;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Bernardo Senna
 */
public class ConfirmaConsultaView extends JPanel {

    Cliente cliente;
    String textoClienteComboBox, textoPagamentoComboBox, textoMatriculaConveniado;
    int tipoPagamento;
    private JTextField matriculaField, valorField;

    public ConfirmaConsultaView(Medico medico, String dataHoraConsulta) {

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

        //PEGA TEXTO DO COMBOBOX DE CLIENTES E GERA UMA VARIAVEL COM OS DADOS DAQUELE CLIENTE.
        clienteComboBox.addActionListener((ActionEvent e) -> {

            textoClienteComboBox = (String) clienteComboBox.getSelectedItem();

            cliente = clientesController.getClienteByName(textoClienteComboBox);

        });

        JPanel tipoFieldPanel = new JPanel();
        JLabel PagamentoLabel = new JLabel("Selecione o tipo de pagamento:");
        tipoFieldPanel.add(PagamentoLabel);
        JComboBox<String> tipoPagamentoComboBox = new JComboBox<String>();
        tipoFieldPanel.add(tipoPagamentoComboBox);

        this.add(tipoFieldPanel, BorderLayout.CENTER);

        tipoPagamentoComboBox.addItem("Particular");
        tipoPagamentoComboBox.addItem("Convenio");

        JPanel escolhaFieldPanel = new JPanel();
        JLabel PagamentoLabel2 = new JLabel("Selecione a forma de pagamento/convenio:");
        escolhaFieldPanel.add(PagamentoLabel2);
        JComboBox<String> pagamentoComboBox = new JComboBox<String>();
        pagamentoComboBox.setEnabled(false);
        escolhaFieldPanel.add(pagamentoComboBox);

        this.add(escolhaFieldPanel, BorderLayout.CENTER);

        JPanel matriculaFieldPanel = new JPanel();
        JLabel matriculaLabel = new JLabel("Digite a matricula do conveniado:");
        matriculaFieldPanel.add(matriculaLabel);
        matriculaField = new JTextField(1);
        matriculaField.setColumns(21);
        matriculaField.setEnabled(false);
        matriculaField.setBackground(Color.LIGHT_GRAY);
        matriculaFieldPanel.add(matriculaField);

        this.add(matriculaFieldPanel, BorderLayout.CENTER);

        JPanel valorFieldPanel = new JPanel();
        JLabel valorLabel = new JLabel("Valor (R$):");
        valorFieldPanel.add(valorLabel);
        valorField = new JTextField(1);
        valorField.setColumns(21);
        valorFieldPanel.add(valorField);

        this.add(valorFieldPanel, BorderLayout.CENTER);

        //PEGA TEXTO DO COMBOBOX DE TIPOS DE PAGAMENTO E MONTA UM COMBOBOX COM AS OPCOES RESPECTIVAS.
        tipoPagamentoComboBox.addActionListener((ActionEvent e) -> {

            textoPagamentoComboBox = (String) tipoPagamentoComboBox.getSelectedItem();

            if ("Particular".equals(textoPagamentoComboBox)) {
                pagamentoComboBox.setEnabled(true);
                pagamentoComboBox.removeAllItems();
                pagamentoComboBox.addItem("Cartao Credito");
                pagamentoComboBox.addItem("Cartao Debito");
                pagamentoComboBox.addItem("Cheque");
                pagamentoComboBox.addItem("Dinheiro");

                matriculaField.setEnabled(false);
                matriculaField.setBackground(Color.LIGHT_GRAY);

                tipoPagamento = 0;//pagamento por particular
            } else if ("Convenio".equals(textoPagamentoComboBox)) {
                pagamentoComboBox.setEnabled(true);
                pagamentoComboBox.removeAllItems();
                pagamentoComboBox.addItem("Amil");
                pagamentoComboBox.addItem("Bradesco");
                pagamentoComboBox.addItem("Golden Cross");
                pagamentoComboBox.addItem("Promed");
                pagamentoComboBox.addItem("Unimed");

                matriculaField.setEnabled(true);
                matriculaField.setBackground(Color.WHITE);

                tipoPagamento = 1;//pagamento por convenio
            } else {
                pagamentoComboBox.setEnabled(false);
            }
        });

        //RODAPÉ....
        JPanel botoesFieldPanel = new JPanel();
        JButton voltarButton = new JButton("Voltar");
        voltarButton.addActionListener((ActionEvent e) -> {
            Router.getInstance().goToView(new AgendaMedicoViewConsulta(medico));
        });
        botoesFieldPanel.add(voltarButton);

        JButton confirmarButton = new JButton("Confirmar");
        confirmarButton.addActionListener((ActionEvent e) -> {
            PagamentosController pagamentosController = new PagamentosController();

            int valor = Integer.parseInt(valorField.getText());
            String pagamento = (String) pagamentoComboBox.getSelectedItem();
            textoMatriculaConveniado = (String) matriculaField.getText();

            ConsultasController consultasController = new ConsultasController();
            Consulta consulta = consultasController.criarConsulta(dataHoraConsulta, medico, cliente);

            if (tipoPagamento == 0) {
                pagamentosController.criarPagamentoParticular(valor, tipoPagamento, pagamento, consulta.getId());
            } else {
                pagamentosController.criarPagamentoConvenio(valor, tipoPagamento, pagamento, textoMatriculaConveniado, 1);
            }

            Router.getInstance().goToView(new HomeView());
        });
        botoesFieldPanel.add(confirmarButton);
        this.add(botoesFieldPanel, BorderLayout.SOUTH);
    }

}
