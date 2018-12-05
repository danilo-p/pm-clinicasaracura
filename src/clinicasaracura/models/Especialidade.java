/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicasaracura.models;

/**
 *
 * @author vien
 */
public class Especialidade {
    private int id;
    private String nome;
    
    Especialidade(int id, String nome)
    {
        this.id = id;
        this.nome = nome;
    }
    
    public void setId(int id)
    {
        this.id = id;
    }
    
    public void setNome(String nome)
    {
        this.nome = nome;
    }
    
    public int getId()
    {
        return id;
    }
    
    public String getNome()
    {
        return nome;
    }
    
}
