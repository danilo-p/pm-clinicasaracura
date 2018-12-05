/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicasaracura.views;

import clinicasaracura.controllers.ClientesController;
import clinicasaracura.models.Cliente;
import clinicasaracura.models.Pessoa;
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
 * Página de listagem de clientes com botão que leva à página de cadastro de
 * cliente
 *
 * @author danilo
 */
public class ClientesView extends JPanel {

    public ClientesView() {
        this.setBorder(new EmptyBorder(15, 15, 15, 15));
        this.setLayout(new BorderLayout(15, 15));

        JPanel tituloPanel = new JPanel();
        tituloPanel.setLayout(new GridLayout(1, 2, 0, 0));

        JLabel titulo = new JLabel("Clientes");
        titulo.setAlignmentX(CENTER_ALIGNMENT);
        tituloPanel.add(titulo);

        JButton novoButton = new JButton("Novo");
        novoButton.addActionListener((ActionEvent e) -> {
            Router.getInstance().goToView(new CadastroClienteView());
        });
        tituloPanel.add(novoButton);

        this.add(tituloPanel, BorderLayout.NORTH);

        ClientesController clientesController = new ClientesController();
        List clientes = clientesController.getClientes();

        String[] titulos = {"ID", "Nome", "CPF", "Telefone"};
        Object[][] linhas = new Object[clientes.size()][4];
        for (int i = 0; i < clientes.size(); i++) {
            Cliente cliente = (Cliente) clientes.get(i);
            Pessoa pessoa = cliente.getPessoa();
            linhas[i][0] = cliente.getId();
            linhas[i][1] = pessoa.getNome();
            linhas[i][2] = pessoa.getCpf();
            linhas[i][3] = pessoa.getTelefone();
        }

        JTable clientesTable = new JTable(linhas, titulos);
        clientesTable.setDefaultEditor(Object.class, null);
        clientesTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                JTable table =(JTable) mouseEvent.getSource();
                Point point = mouseEvent.getPoint();
                int row = table.rowAtPoint(point);
                if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
                    Cliente clienteSelecionado = (Cliente) clientes.get(table.getSelectedRow());
                    System.out.println(clienteSelecionado.getPessoa().getNome());
                    // TODO: Redirecionar para a página do cliente quando tivermos uma
                }
            }
        });
        

        JScrollPane scrollPane = new JScrollPane(clientesTable);
        clientesTable.setFillsViewportHeight(true);
        this.add(scrollPane, BorderLayout.CENTER);

        JButton voltarButton = new JButton("Voltar");
        voltarButton.addActionListener((ActionEvent e) -> {
            Router.getInstance().goToView(new HomeView());
        });
        this.add(voltarButton, BorderLayout.SOUTH);
    }
}
