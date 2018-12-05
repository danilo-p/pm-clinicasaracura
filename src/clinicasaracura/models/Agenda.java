/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicasaracura.models;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author danilo
 */
public class Agenda {
    private Integer id;
    private Time cargaHoraria;
    private Time horaInicio;
    private Time horaFim;
    private Time tempoIntervalo;
    
    public Agenda() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        try {
            this.cargaHoraria = new Time(sdf.parse("08:00:00").getTime());
            this.horaInicio = new Time(sdf.parse("07:00:00").getTime());
            this.horaFim = new Time(sdf.parse("19:00:00").getTime());
            this.tempoIntervalo = new Time(sdf.parse("00:30:00").getTime());
        } catch (ParseException ex) {
        }
    }
    
    public Agenda(Time cargaHoraria, Time horaInicio, Time horaFim, Time tempoIntervalo) {
        this.cargaHoraria = cargaHoraria;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
        this.tempoIntervalo = tempoIntervalo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Time getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(Time cargaHoraria) {
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

    public Time getTempoIntervalo() {
        return tempoIntervalo;
    }

    public void setTempoIntervalo(Time tempoIntervalo) {
        this.tempoIntervalo = tempoIntervalo;
    }
    
    
}
