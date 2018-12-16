/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicasaracura.dao;

import clinicasaracura.models.Cliente;
import clinicasaracura.models.Consulta;
import clinicasaracura.models.Medico;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object para o modelo Consulta.
 *
 * @see https://pt.wikipedia.org/wiki/Objeto_de_acesso_a_dados
 *
 * @author danilo
 */
public class ConsultaDAO extends GenericDAO {
    private final MedicoDAO medicoDAO;
    private final ClienteDAO clienteDAO;

    public ConsultaDAO() {
        this.medicoDAO = new MedicoDAO();
        this.clienteDAO = new ClienteDAO();
    }

    public void salvar(Consulta consulta) throws SQLException {
        String insert = "INSERT INTO consultas(data, medico_id, cliente_id) VALUES(?, ?, ?)";
        int id = save(insert, consulta.getData(), consulta.getMedico().getId(), consulta.getCliente().getId());
        if (id > 0) {
            consulta.setId(id);
        }
    }

    public void alterar(Consulta consulta) throws SQLException {
        String update = "UPDATE consultas SET data = ?, medico_id = ?, cliente_id = ? WHERE id = ?";
        update(update, consulta.getData(),
                consulta.getMedico().getId(), consulta.getCliente().getId());
    }

    public List findByCliente(Cliente cliente) throws SQLException {
        List consultas = new ArrayList();

        String select = "SELECT * FROM consultas WHERE cliente_id = ?";
        Connection connection = getConnection();
        PreparedStatement stmt = connection.prepareStatement(select);

        stmt.setInt(1, cliente.getId());
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Consulta consulta = new Consulta();
            consulta.setId(rs.getInt("id"));
            consulta.setData(rs.getTimestamp("data"));
            consulta.setCliente(cliente);

            int medicoId = rs.getInt("medico_id");
            Medico medico = this.medicoDAO.findById(medicoId);
            consulta.setMedico(medico);

            consultas.add(consulta);
        }

        rs.close();
        stmt.close();
        connection.close();

        return consultas;
    }

    public List findByMedico(Medico medico, Timestamp inicio, Timestamp fim) throws SQLException {
        List consultas = new ArrayList();

        String select = "SELECT * FROM consultas WHERE medico_id = ? AND data BETWEEN ? AND ?";
        Connection connection = getConnection();
        PreparedStatement stmt = connection.prepareStatement(select);

        stmt.setInt(1, medico.getId());
        stmt.setTimestamp(2, inicio);
        stmt.setTimestamp(3, fim);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Consulta consulta = new Consulta();
            consulta.setId(rs.getInt("id"));
            consulta.setData(rs.getTimestamp("data"));
            consulta.setMedico(medico);

            int clienteId = rs.getInt("cliente_id");
            Cliente cliente = this.clienteDAO.findById(clienteId);
            consulta.setCliente(cliente);

            consultas.add(consulta);
        }

        rs.close();
        stmt.close();
        connection.close();

        return consultas;
    }
}
