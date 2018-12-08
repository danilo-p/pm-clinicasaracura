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
public class Medico extends Pessoa{
    
    
    protected Especialidade especialidade;
    protected Agenda cargaHoraria;
    protected Agenda horaInicio;
    protected Agenda horaFim;
    protected Agenda tempoIntervalo;
    
    public Medico(){
        super();
    }
    
    public Medico( Especialidade especialidade){
        super();
        this.especialidade = especialidade;
    }
    
    public Especialidade getEspecialidade(){
        return especialidade;
    }
    
    public void setEspecialidade(Especialidade especialidade){
        this.especialidade = especialidade;
    }
    
}
