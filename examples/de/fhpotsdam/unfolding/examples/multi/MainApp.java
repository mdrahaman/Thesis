package de.fhpotsdam.unfolding.examples.multi;

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

public class MainApp extends PApplet {

	// map objects create
	UnfoldingMap leftMap;
	UnfoldingMap rightMap;

	// Main window

	public void settings() {

		size(1920, 1020, P2D);
	}

	public static final int COLOR_DEFAULT_MARKER = 0x90FFFF00;
	public static final int COLOR_SELECTED_MARKER = 0x30FF00FF;
	public static final int COLOR_SELECTED_MARKER_BORDER = 0xFFFF000;
	
	
	public void setup() {

		 
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
		//List<Feature> countries = GeoJSONReader.loadData(this, "africa/countries.geo.json");
		//List<Marker> countryMarkers = MapUtils.createSimpleMarkers(countries);
		//rightMap.addMarkers(countryMarkers);

		rightMap.zoomToLevel(2);

		MapUtils.createDefaultEventDispatcher(this, leftMap, rightMap);

	}
	
	// make clickable map and get ID, location, name of country
	
	public void mouseClicked() {
		Marker marker = leftMap.getFirstHitMarker(mouseX, mouseY);
		if (marker != null) {
			String message = marker.getLocation().toString();
			message += ", ID:" + marker.getId();
			message += ", Name:" + marker.getProperty("name");

			System.out.println(message);
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

	}

	public static void main(String[] args) {
		PApplet.main(new String[] { MainApp.class.getName() });

	}

}
