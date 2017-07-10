package de.fhpotsdam.unfolding.examples.multi;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.providers.Microsoft;
import de.fhpotsdam.unfolding.utils.MapUtils;
import processing.core.PApplet;

public class SimpleAplet extends PApplet {
	
	UnfoldingMap map;
	
	public void settings()
	{
		size(600,400, P2D);
	}
	
	
	public void setup()
	{
		
		map = new UnfoldingMap(this,0,0,800,400, new Microsoft.HybridProvider());
		MapUtils.createDefaultEventDispatcher(this, map);
		//map.panTo(new Location(20,  90));
		map.zoomToLevel(3);
	}
	
	
	public void draw()
	{
		background(200);
		map.draw();
	}
	
	
	

	public static void main(String[] args) {
		PApplet.main(new String[]{SimpleAplet.class.getName()});

	}


	public void init() {
		// TODO Auto-generated method stub
		
	}


	

}
