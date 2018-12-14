/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicasaracura.models;

/**
 *
 * @author Bernardo Senna
 */
public class Pagamento {
    
   
    private int id;
    private int valor;
    private int tipo;
    private String metodo;
    private String convenio;
    private String matricula;
    private int consultaId;
    
    public Pagamento(){
    
    }
    
    Pagamento(int id, int valor, int tipo, String metodo, String convenio, String matricula, int consultaId)
    {
        this.id = id;
        this.valor = valor;
        this.tipo = tipo;
        this.metodo = metodo;
        this.convenio = convenio;
        this.matricula = matricula;
        this.consultaId = consultaId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public String getConvenio() {
        return convenio;
    }

    public void setConvenio(String convenio) {
        this.convenio = convenio;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public int getConsultaId() {
        return consultaId;
    }

    public void setConsultaId(int consultaId) {
        this.consultaId = consultaId;
    }
    
    
}
