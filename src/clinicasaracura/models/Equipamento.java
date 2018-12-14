/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicasaracura.models;
import java.util.List;


/**
 *
 * @author Vien
 */
public class Equipamento extends Agendavel{
    private int id;
    private String nome;
    private Especialidade especialidade;
    
    public Equipamento(){
        super();
    }
    
    public Equipamento(String nome, Especialidade especialidade){
        super();
        this.nome = nome;
        this.especialidade = especialidade;
    }
    
    public void setNome(String nome)
    {
        this.nome = nome;
    }
    
    public void setEspecialidade(Especialidade especialidade)
    {
        this.especialidade = especialidade;
    }
    
    public void setId(int id)
    {
        this.id = id;
    }
    
    public String getNome()
    {
        return nome;
    }
    
    public Especialidade getEspecialidade()
    {
        return especialidade;
    }
    
    public int getId()
    {
        return id;
    }
}
