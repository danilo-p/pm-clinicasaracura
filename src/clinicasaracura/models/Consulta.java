/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicasaracura.models;

import java.sql.Timestamp;

/**
 * Consulta para agenda.
 *
 * @author danilo
 */
public class Consulta {

    private int id;
    private Timestamp data;
    private Medico medico;
    private Cliente cliente;

    public Consulta() {
    }

    public Consulta(Timestamp data, Medico medico, Cliente cliente) {
        this.data = data;
        this.medico = medico;
        this.cliente = cliente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getData() {
        return data;
    }

    public void setData(Timestamp data) {
        this.data = data;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return this.id + ", " + this.data + ", " + this.cliente.getNome()
                + ", " + this.medico.getNome();
    }
}
