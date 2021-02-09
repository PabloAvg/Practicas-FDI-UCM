package simulator.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import simulator.control.Controller;
import simulator.model.Event;
import simulator.model.Junction;
import simulator.model.Road;
import simulator.model.RoadMap;
import simulator.model.TrafficSimObserver;
import simulator.model.Vehicle;
import simulator.model.VehicleStatus;

import javax.swing.JComponent;

public class MapByRoad extends JPanel implements TrafficSimObserver {
	
	private static final long serialVersionUID = 1L;

	private static final int _JRADIUS = 10;

	private static final Color _BG_COLOR = Color.WHITE;
	private static final Color _JUNCTION_COLOR = Color.BLUE;
	private static final Color _JUNCTION_LABEL_COLOR = new Color(200, 100, 0);
	private static final Color _GREEN_LIGHT_COLOR = Color.GREEN;
	private static final Color _RED_LIGHT_COLOR = Color.RED;

	private RoadMap _map;

	private Image _car;
	
	MapByRoad(Controller ctrl) {
		this.setPreferredSize(new Dimension (300, 200));
		initGUI();
		ctrl.addObserver(this);
	}

	private void initGUI() {
		_car = loadImage("car_front.png");
	}
	
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		Graphics2D g = (Graphics2D) graphics;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		// clear with a background color
		g.setColor(_BG_COLOR);
		g.clearRect(0, 0, getWidth(), getHeight());

		if (_map == null || _map.getJunctions().size() == 0) {
			g.setColor(Color.red);
			g.drawString("No map yet!", getWidth() / 2 - 50, getHeight() / 2);
		} else {
			updatePrefferedSize();
			drawMap(g);
		}
	}
	
	private void drawMap(Graphics g) {
		drawRoads(g);
		drawVehicles(g);
		drawJunctions(g);
	}
	
	
	private void drawJunctions(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	private void drawVehicles(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	private void drawRoads(Graphics g) {
		for (Road r : _map.getRoads()) {

			// the road goes from (x1,y1) to (x2,y2)
			int x1 = 50;
			int y1 = r.getSource().getY();
			int x2 = getWidth()-100;
			int y2 = r.getDestination().getY();

			// choose a color for the arrow depending on the traffic light of the road
			Color arrowColor = _RED_LIGHT_COLOR;
			int idx = r.getDestination().getCurrGreen();
			if (idx != -1 && r.equals(r.getDestination().getInRoads().get(idx))) {
				arrowColor = _GREEN_LIGHT_COLOR;
			}

			// choose a color for the road depending on the total contamination, the darker
			// the
			// more contaminated (wrt its co2 limit)
			//Esta formula es para saber el color de la carretera
			int roadColorValue = 200 - (int) (200.0 * Math.min(1.0, (double) r.getTotalContamination() / (1.0 + (double) r.getcontaminationAlarmLimit())));
			Color roadColor = new Color(roadColorValue, roadColorValue, roadColorValue);

			// draw line from (x1,y1) to (x2,y2) with arrow of color arrowColor and line of
			// color roadColor. The size of the arrow is 15px length and 5 px width
			drawLineWithArrow(g, x1, y1, x2, y2, 15, 5, roadColor, arrowColor);
		}
		
	}

	// this method is used to update the preffered and actual size of the component,
		// so when we draw outside the visible area the scrollbars show up
		private void updatePrefferedSize() {
			int maxW = 200;
			int maxH = 200;
			for (Junction j : _map.getJunctions()) {
				maxW = Math.max(maxW, j.getX());
				maxH = Math.max(maxH, j.getY());
			}
			maxW += 20;
			maxH += 20;
			setPreferredSize(new Dimension(maxW, maxH));
			setSize(new Dimension(maxW, maxH));
		}

	// loads an image from a file
	private Image loadImage(String img) {
		Image i = null;
		try {
			return ImageIO.read(new File("resources/icons/" + img));
		} catch (IOException e) {
		}
		return i;
	}
		public void update(RoadMap map) {
		_map = map;
		repaint();
	}
		@Override
	public void onAdvanceStart(RoadMap map, List<Event> events, int time) {
	}
		@Override
	public void onAdvanceEnd(RoadMap map, List<Event> events, int time) {
		update(map);
	}
		@Override
	public void onEventAdded(RoadMap map, List<Event> events, Event e, int time) {
		update(map);
	}
		@Override
	public void onReset(RoadMap map, List<Event> events, int time) {
		update(map);
	}
		@Override
	public void onRegister(RoadMap map, List<Event> events, int time) {
		update(map);
	}
		@Override
	public void onError(String err) {
	}
}
