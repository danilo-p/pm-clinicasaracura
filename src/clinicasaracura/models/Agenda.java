/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicasaracura.models;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Modelo para agenda.
 *
 * @author danilo
 */
public class Agenda {

    private int id;
    private String cargaHoraria;
    private Time horaInicio;
    private Time horaFim;
    private int tempoIntervalo;

    public Agenda() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        try {
            this.cargaHoraria = "Segunda-feira,Terça-feira,Quarta-feira,Quinta-feira,Sexta-feira";
            this.horaInicio = new Time(sdf.parse("07:00").getTime());
            this.horaFim = new Time(sdf.parse("19:00").getTime());
        } catch (ParseException ex) {
        }
    }

    public Agenda(String cargaHoraria, Time horaInicio, Time horaFim, int tempoIntervalo) {
        this.cargaHoraria = cargaHoraria;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
        this.tempoIntervalo = tempoIntervalo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(String cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public Time getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Time horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Time getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(Time horaFim) {
        this.horaFim = horaFim;
    }

    public int getTempoIntervalo() {
        return tempoIntervalo;
    }

    public void setTempoIntervalo(int tempoIntervalo) {
        this.tempoIntervalo = tempoIntervalo;
    }

}
