
package de.fhpotsdam.unfolding.examples.multi;

import java.awt.Color; 

import java.awt.Font;
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener;
//import java.awt.Color; 
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.GeoJSONReader;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.marker.SimplePolygonMarker;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.providers.Microsoft;
import de.fhpotsdam.unfolding.utils.MapUtils;
import processing.core.PApplet;



public class AfricaMapMarkerApp extends PApplet {

	UnfoldingMap map2;

	public void settings() {
		
		size(1920, 1000, P2D);
	}
	
	

	public static final int COLOR_DEFAULT_MARKER = 0x90FFFF00;
	public static final int COLOR_SELECTED_MARKER = 0x30FF00FF;
	public static final int COLOR_SELECTED_MARKER_BORDER = 0xFFFF000;

	public static void main(String args[]) {
		PApplet.main(new String[] { AfricaMapMarkerApp.class.getName() });
	}

	public void setup() {
		
		
//		map2 = new UnfoldingMap(this, "map2", 880, 10, 600, 1000, true, false, new Microsoft.HybridProvider());
		map2 = new UnfoldingMap(this, "map2", 880, 10, 600, 1000, true, false, new Google.GoogleSimplifiedProvider());
		
//		map2 = new UnfoldingMap(this, 0, 50, 1470, 930);
		 map2.zoomToLevel(3.5);
		Location location1 = new Location(15.456, 20.987);
		map2.zoomAndPanTo(location1, 3.5);
		MapUtils.createDefaultEventDispatcher(this, map2);

		// map2 = new UnfoldingMap(this, 560, 50, 100, 100);

		List<Feature> countries = GeoJSONReader.loadData(this, "africa/countries.geo.json");

		List<Marker> countryMarkers = MapUtils.createSimpleMarkers(countries);
		// Set colors of Markers
		for (Marker marker : countryMarkers) {
			marker.setColor(COLOR_DEFAULT_MARKER);
			marker.setHighlightColor(COLOR_SELECTED_MARKER);
			marker.setHighlightStrokeColor(COLOR_SELECTED_MARKER_BORDER);
		}
		map2.addMarkers(countryMarkers);
		
		
		
	}

	public void draw() {
		
		background(200);
		map2.draw();
		// map2.draw();

		// show latitute anad lagnitute at mouse position
		// fill(300,0,0,500); // mouse pointer colour filling
		// noFill();
		// Location location = map2.getLocation(mouseX, mouseY);
		// text("geoPosition:" +location.toString(),mouseX,mouseY);
	}
	
	

	
	public void mouseClicked() {
		Marker marker = map2.getFirstHitMarker(mouseX, mouseY);
		if (marker != null) {
			String message = marker.getLocation().toString();
			message += ", ID:" + marker.getId();
			message += ", Name:" + marker.getProperty("name");

			System.out.println(message);
		}
	}

	public void mouseMoved() {
		// Deselect All Markers
		for (Marker marker : map2.getMarkers()) {
			marker.setSelected(false);
			marker.setStrokeWeight(0);
		}

		// Get Selected Marker
		Marker marker = map2.getFirstHitMarker(mouseX, mouseY);
		if (marker != null) {
			marker.setSelected(true);
			marker.setStrokeWeight(3);
		}

	}
}
