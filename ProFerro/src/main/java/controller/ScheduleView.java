package controller;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;
 
@Named
@ViewScoped
public class ScheduleView implements Serializable {
	 
	private static final long serialVersionUID = 1L;

	private ScheduleModel eventModel;
     
    private ScheduleEvent event = new DefaultScheduleEvent();
    
    private String titulo;
    
    private Date DateDe;
    
    private Date DateAte;
    
 
    @PostConstruct
    public void init() {
        eventModel = new DefaultScheduleModel();
         
    }
    
    public void salvar(){
        event = new DefaultScheduleEvent(getTitulo(), getDateDe(), getDateAte());
        eventModel.addEvent(event);
        event = new DefaultScheduleEvent();
    }

    public ScheduleModel getEventModel() {
        return eventModel;
    }
     
    public ScheduleEvent getEvent() {
        return event;
    }
 
    public void setEvent(ScheduleEvent event) {
        this.event = event;
    }
     
    public void addEvent(ActionEvent actionEvent) {
        if(event.getId() == null){
            eventModel.addEvent(event);
        }else
            eventModel.updateEvent(event);
         
        event = new DefaultScheduleEvent();
    }
     
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Date getDateDe() {
		return DateDe;
	}

	public void setDateDe(Date dateDe) {
		DateDe = dateDe;
	}

	public Date getDateAte() {
		return DateAte;
	}

	public void setDateAte(Date dateAte) {
		DateAte = dateAte;
	}
    
}
