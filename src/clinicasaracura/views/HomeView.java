/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicasaracura.views;

import clinicasaracura.controllers.HomeController;
import clinicasaracura.models.Pessoa;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author danilo
 */
public class HomeView extends JPanel {
    private final HomeController homeController;
    private final List pessoas;

    public HomeView() {
        homeController = new HomeController();
        pessoas = homeController.getPessoas();
        for (int i = 0; i < pessoas.size(); i++) {
            Pessoa pessoa = (Pessoa) pessoas.get(i);
            this.add(new JLabel(pessoa.getNome()));
        }
    }
}
