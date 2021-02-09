package simulator.model;

import java.util.*;

import org.json.JSONObject;

public class TrafficSimulator implements Observable<TrafficSimObserver>{
	
	private RoadMap roadMap;
	private List<Event> listEvent;
	private int time;
	private List<TrafficSimObserver> observerList;
	
	public TrafficSimulator(){
		this.roadMap = new RoadMap();
		this.listEvent = new ArrayList<Event>();
		this.time =0;
		this.observerList = new ArrayList<TrafficSimObserver>();
	}
	
	public void addEvent(Event e){
		this.listEvent.add(e);
	}
	
	public void advance(){
		//1
		this.time++;
		
		//Notificamos a los observadores
		onAdvanceStartNotify(roadMap, listEvent, time);
			
		//2
		for(Event e: this.listEvent) {
			if(e.getTime() == this.time) {
				e.execute(roadMap);	//Primero ejecuto el evento.
			}
		}
		
		for(int i =0; i < this.listEvent.size(); i++) {
			if(this.listEvent.get(i).getTime() == this.time) {
				
				//Notificamos a los observadores
				this.onEventAddedNotify(roadMap, listEvent,  time, listEvent.get(i));
				this.listEvent.remove(i); //Despues lo quito de la lista.
				i--;
			}
		}
		
		
		//3
		for(Junction j: this.roadMap.getJunctions()) {
			j.advance(time);
		}
		//4
		for(Road r: this.roadMap.getRoad()) {
			r.advance(time);
		}
		
		//Notificamos a los observadores
		onAdvanceEndNotify(roadMap, listEvent, time);
	}
	
	public void onAdvanceStartNotify(RoadMap map, List<Event> events, int time) {
		for(TrafficSimObserver o : this.observerList) {	
			o.onAdvanceStart(map, events, time);
		}
	}
	
	public void onAdvanceEndNotify(RoadMap map, List<Event> events, int time) {
		for(TrafficSimObserver o : this.observerList) {	
			o.onAdvanceEnd(map, events, time);
		}
	}
	public void onEventAddedNotify(RoadMap map, List<Event> events, int time, Event e) {
		for(TrafficSimObserver o : this.observerList) {	
			o.onEventAdded(roadMap, listEvent, e, time);
		}
	}
	
	public void reset() {
		this.roadMap.reset();
		this.listEvent.clear();
		this.time =0;
		onResetNotify(roadMap, listEvent, time);
	}
	
	public void onResetNotify(RoadMap map, List<Event> events, int time) {
		for(TrafficSimObserver o : this.observerList) {	
			o.onReset(roadMap, listEvent, time);
		}
	}
	
	public JSONObject report() {
		
		JSONObject obj = new JSONObject();
		
		obj.put("time", this.time);
		
		obj.put("state", this.roadMap.report());
		
		return obj;
	}

	@Override
	public void addObserver(TrafficSimObserver o) {
		if(!this.observerList.add(o)) {
			throw new IllegalArgumentException("No se añadio el observer" + o.toString());
		}
		o.onRegister(roadMap, listEvent, time);
	//	o.notifyOnRegister();
		
	}
	
	public void notifyOnRegister() {
		for(TrafficSimObserver o : this.observerList) {	
			o.onReset(roadMap, listEvent, time);
		}
	}

	@Override
	public void removeObserver(TrafficSimObserver o) {
		
		if(this.observerList.isEmpty()) {
			throw new NullPointerException("La ObserverList esta vacia.");
		}
		
		if(this.observerList.indexOf(o)==-1) {
			throw new NullPointerException("El Observer no esta en la lista.");
		}
		
		this.observerList.remove(o);
		
	}


	
}
