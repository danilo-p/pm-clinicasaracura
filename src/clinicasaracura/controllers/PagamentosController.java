/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicasaracura.controllers;

import clinicasaracura.dao.PagamentoDAO;
import clinicasaracura.models.Pagamento;
import java.sql.SQLException;

/**
 *
 * @author Bernardo Senna
 */
public class PagamentosController {
    
    private final PagamentoDAO pagamentoDAO;

    public PagamentosController() {
        this.pagamentoDAO = new PagamentoDAO();
    }
    
    
    public void criarPagamentoParticular(int valor, int tipoPagamento, String pagamento, int idConsulta) {
        Pagamento novoPagamento = new Pagamento();

        novoPagamento.setValor(valor);
        novoPagamento.setTipo(tipoPagamento);
        novoPagamento.setMetodo(pagamento);
        novoPagamento.setConsultaId(idConsulta);

        try {
            this.pagamentoDAO.salvarPagamentoParticular(novoPagamento);
        } catch (SQLException ex) {
            System.out.println("PagamentosController: Falha ao salvar pagamento.");
            System.out.println(ex);
        }
    }
    
    public void criarPagamentoConvenio(int valor, int tipoPagamento, String pagamento, String matricula, int idConsulta) {
        Pagamento novoPagamento = new Pagamento();

        novoPagamento.setValor(valor);
        novoPagamento.setTipo(tipoPagamento);
        novoPagamento.setConvenio(pagamento);
        novoPagamento.setMatricula(matricula);
        novoPagamento.setConsultaId(idConsulta);

        try {
            this.pagamentoDAO.salvarPagamentoConvenio(novoPagamento);
        } catch (SQLException ex) {
            System.out.println("PagamentosController: Falha ao salvar pagamento.");
            System.out.println(ex);
        }
    }
    
    
}
