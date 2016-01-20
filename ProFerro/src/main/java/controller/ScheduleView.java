package controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import model.Evento;

import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

import repository.Eventos;
 
@Named
@ViewScoped
public class ScheduleView implements Serializable {
	 
	private static final long serialVersionUID = 1L;

	private ScheduleModel eventModel;
     
    private ScheduleEvent event = new DefaultScheduleEvent();
    
    private Evento evento;
    
    @Inject
    private Eventos eventos;
    
    private List<Evento> listaEventos;
    
    
    public ScheduleView() {
    	evento = new Evento();
	}
    
    @PostConstruct
    public void init() {
        eventModel = new DefaultScheduleModel();
        listarEventos();
         
    }
    
    public void salvar(){
    	evento = eventos.guardar(evento);
        event = new DefaultScheduleEvent(evento.getTitulo(), evento.getDataDe(), evento.getDataAte());
        eventModel.addEvent(event);
        //event = new DefaultScheduleEvent();
    }
    
    /**
     * Metódo responsável por listar os eventos cadastrados 
     */
    public void listarEventos(){
    	 listaEventos = eventos.listar();
     	
     	for (Evento evento : listaEventos) {
     		eventModel.addEvent(new DefaultScheduleEvent(evento.getTitulo(), evento.getDataDe(), evento.getDataAte()));
 		}
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

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public List<Evento> getListaEventos() {
		return listaEventos;
	}

	public void setListaEventos(List<Evento> listaEventos) {
		this.listaEventos = listaEventos;
	}
     
	
    
}
