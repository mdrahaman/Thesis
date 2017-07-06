package de.fhpotsdam.unfolding.examples.multi;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import de.fhpotsdam.unfolding.Map;
import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.GeoJSONReader;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import de.fhpotsdam.unfolding.marker.SimplePolygonMarker;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.providers.Microsoft;
import de.fhpotsdam.unfolding.providers.OpenStreetMap;
import de.fhpotsdam.unfolding.utils.MapUtils;
import de.fhpotsdam.unfolding.utils.ScreenPosition;
import processing.core.PApplet;

public class MainApp extends PApplet implements ActionListener {

	// map objects create
	UnfoldingMap leftMap;
	UnfoldingMap rightMap;
	private List<Feature> countries = null;

	// Main window

	public void settings() {

		size(1920, 1020, P2D);
	}

	public static final int COLOR_DEFAULT_MARKER = 0x90FFFF00;
	public static final int COLOR_SELECTED_MARKER = 0x30FF00FF;
	public static final int COLOR_SELECTED_MARKER_BORDER = 0xFFFF000;
	public static final Location location1 = new Location(6.456, 26.987);

	public void setup() {

		// create map
		
		leftMap = new UnfoldingMap(this, "leftMap", 0, 10, 870, 1000, true, false, null);
		List<Feature> world = GeoJSONReader.loadData(this, "world/continent.geo.json");
		List<Marker> worldMarkers = MapUtils.createSimpleMarkers(world);
		

		// Set colors of Markers
		for (Marker marker : worldMarkers) {
			marker.setColor(COLOR_DEFAULT_MARKER);
			marker.setHighlightColor(COLOR_SELECTED_MARKER);
			marker.setHighlightStrokeColor(COLOR_SELECTED_MARKER_BORDER);
		}

		leftMap.addMarkers(worldMarkers);
		leftMap.zoomToLevel(2);

		// Location location1 = new Location(54.456, 14.987);
		// map2.zoomAndPanTo(location1, 2);

		// right map drawing //

		rightMap = new UnfoldingMap(this, "rightMap", 880, 10, 600, 1000, true, false, null);
		countries = GeoJSONReader.loadData(this, "asia/countries.geo.json");
		List<Marker> countryMarkers = MapUtils.createSimpleMarkers(countries);
		rightMap.addMarkers(countryMarkers);

		rightMap.zoomToLevel(2);

		MapUtils.createDefaultEventDispatcher(this, leftMap, rightMap);
		
						

	}

	// make clickable map and get ID, location, name of country, location

	public void mouseClicked() {
		Marker marker = leftMap.getFirstHitMarker(mouseX, mouseY);
		if (marker != null) {
			String message = marker.getLocation().toString();
			message += ", ID:" + marker.getId();
			message += ", Name:" + marker.getProperty("name");
			System.out.println(message);

			// rightMap calling
			rightMap = new UnfoldingMap(this, "rightMap", 880, 10, 600, 1000, true, false, null);
			countries = GeoJSONReader.loadData(this, getContinent(marker.getId()));
			List<Marker> countryMarkers = MapUtils.createSimpleMarkers(countries);

			// rightMap marker selection process//
			for (Marker rightMapMarker : countryMarkers) {
				rightMapMarker.setColor(COLOR_DEFAULT_MARKER);
				rightMapMarker.setHighlightColor(COLOR_SELECTED_MARKER);
				rightMapMarker.setHighlightStrokeColor(COLOR_SELECTED_MARKER_BORDER);
			}

			rightMap.addMarkers(countryMarkers);
			rightMap.zoomToLevel(3);

			MapUtils.createDefaultEventDispatcher(this, rightMap);
			
//			public void mouseMoved() {
//			for (Marker rightMapMarker : rightMap.getMarkers()) {
//				rightMapMarker.setSelected(false);
//				rightMapMarker.setStrokeWeight(1);
//			}
//
//			// Get Selected Marker
//			Marker rightMapMarker = rightMap.getFirstHitMarker(mouseX, mouseY);
//			if (rightMapMarker != null) {
//				rightMapMarker.setSelected(true);
//				rightMapMarker.setStrokeWeight(3);
//			}
//			
//		  }

		}

	}

	public void mouseMoved() {
		// Deselect All Markers
		for (Marker marker : leftMap.getMarkers()) {
			marker.setSelected(false);
			marker.setStrokeWeight(1);
		}

		// Get Selected Marker
		Marker marker = leftMap.getFirstHitMarker(mouseX, mouseY);
		if (marker != null) {
			marker.setSelected(true);
			marker.setStrokeWeight(3);
		}

	}

	public void draw() {
		background(300);

		leftMap.draw();
		rightMap.draw();
		
		
		

		// get latitute and lognitute
		
		fill(300,0,0,500); // mouse pointer colour filling
		 noFill();
		 Location location = leftMap.getLocation(mouseX, mouseY);
		 text("geoPosition:" +location.toString(),mouseX,mouseY);

	}

	public static void main(String[] args) {
		PApplet.main(new String[] { MainApp.class.getName() });

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void init() {
		// TODO Auto-generated method stub

	}
	
	
	
	
	
	

	// Popup continent on righMap when click over continent of leftMap //
	 public String getContinent(String continentId) {

		if (continentId.equals("AFC"))
			return "africa/countries.geo.json";
		

		else if (continentId.equals("ASA"))
			return "asia/countries.geo.json";

		else if (continentId.equals("AUS"))
			return "australia/countries.geo.json";

		else if (continentId.equals("EUR"))
			return "europe/countries.geo.json";

		else if (continentId.equals("NAM"))
			return "northAmerica/countries.geo.json";

		else if (continentId.equals("SAM"))
			return "southAmerica/countries.geo.json";

		else if (continentId.equals("ATC"))
			return "antarctica/countries.geo.json";

		return null;
	}

}
