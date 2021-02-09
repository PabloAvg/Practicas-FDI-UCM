package simulator.view;

import java.awt.FlowLayout;
import java.util.List;

import javax.swing.*;

import simulator.control.Controller;
import simulator.model.Event;
import simulator.model.RoadMap;
import simulator.model.TrafficSimObserver;

public class StatusBar extends JPanel implements TrafficSimObserver {
	
	int time;
	public StatusBar(Controller contoller) {
		initGUI();
	}

	private void initGUI() {
		
		JFrame frame = new JFrame();
		setLayout(new FlowLayout(FlowLayout.LEFT));
		
		JLabel time, event;
		time = new JLabel("Time: ");
		frame.add(time);
		frame.add(new JSeparator(SwingConstants.HORIZONTAL));//	Separador Horizontal
		event = new JLabel("Event Added: ");
		frame.add(event);
		time.setHorizontalAlignment(JLabel.LEFT);
		event.setHorizontalAlignment(JLabel.LEFT);
		this.setVisible( true );
	}

	@Override
	public void onAdvanceStart(RoadMap map, List<Event> events, int time) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAdvanceEnd(RoadMap map, List<Event> events, int time) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onEventAdded(RoadMap map, List<Event> events, Event e, int time) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onReset(RoadMap map, List<Event> events, int time) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onRegister(RoadMap map, List<Event> events, int time) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onError(String err) {
		// TODO Auto-generated method stub
		
	}
	
}
