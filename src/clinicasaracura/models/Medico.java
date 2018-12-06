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
