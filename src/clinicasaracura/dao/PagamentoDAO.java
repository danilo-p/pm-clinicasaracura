/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicasaracura.dao;

import clinicasaracura.models.Pagamento;
import java.sql.SQLException;

/**
 *
 * @author Bernardo Senna
 */
public class PagamentoDAO extends GenericDAO {
    
    public void salvarPagamentoParticular(Pagamento pagamento) throws SQLException {
        
        String insert = "INSERT INTO pagamentos(valor, tipo, metodo, consulta_id) VALUES(?,?,?,?)";
        int id = save(insert, pagamento.getValor(), pagamento.getTipo(), pagamento.getMetodo(), pagamento.getConsultaId());
        if (id > 0) {
            pagamento.setId(id);
        }
    }
    
    public void salvarPagamentoConvenio(Pagamento pagamento) throws SQLException {
        
        String insert = "INSERT INTO pagamentos(valor, tipo, convenio, matricula, consulta_id) VALUES(?,?,?,?,?)";
        int id = save(insert, pagamento.getValor(), pagamento.getTipo(), pagamento.getConvenio(), pagamento.getMatricula(), pagamento.getConsultaId());
        if (id > 0) {
            pagamento.setId(id);
        }
    }
    
}
