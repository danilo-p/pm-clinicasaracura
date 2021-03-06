/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicasaracura.models;

/**
 * Abstração de entidades que são agendáveis. Se ela é agendável, então ela tem
 * uma agenda.
 *
 * @author danilo
 */
public abstract class Agendavel {

    protected Agenda agenda;

    public Agendavel() {
    }

    public Agendavel(Agenda agenda) {
        this.agenda = agenda;
    }

    public Agenda getAgenda() {
        return agenda;
    }

    public void setAgenda(Agenda agenda) {
        this.agenda = agenda;
    }
}
