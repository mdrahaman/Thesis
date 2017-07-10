package de.fhpotsdam.unfolding.examples.multi;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import processing.core.PApplet;
import processing.core.PImage;


public class MyApplet extends PApplet implements ActionListener {

	ArrayList<Ball> ballList;
	PImage bgImg = null;

	// public void init() {
	// // TODO Auto-generated method stub
	//
	// }

	@Override
	public void setup() {
		size(800, 600);
		ballList = new ArrayList<Ball>();
		// creates a first ball
		createNewBall();
	}

	@Override
	public void draw() {

		// check if the background image is already loaded
		// if not, the background is painted white
		if (bgImg == null) {
			background(255);
		} else {
			image(bgImg, 0, 0, width, height);
		}

		// move and display all balls
		for (int i = 0; i < ballList.size(); i++) {
			Ball ball = ballList.get(i);
			ball.move();
			ball.display();
		}
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		if (evt.getActionCommand().equals("create ball")) {
			createNewBall();
		} else {
			println("actionPerformed(): can't handle " + evt.getActionCommand());
		}
	}

	public void loadBgImage(File selectedFile) {
		bgImg = loadImage(selectedFile.getAbsolutePath());

	}

	private void createNewBall() {
		Ball nBall = new Ball();
		ballList.add(nBall);
	}

	private class Ball {
		float x;
		float y;
		float size;
		float speedX;
		float speedY;
		Color color;

		private Ball() {
			this.size = random(10, 40);
			this.x = random(this.size, width - this.size);
			this.y = random(this.size, height - this.size);
			this.speedX = random(-2, 2) * 3;
			this.speedY = random(-2, 2) * 3;
			this.color = new Color(random(1), random(1), random(1));
		}

		private void move() {
			if (x + size / 2f > width || x - size / 2f < 0)
				speedX = -speedX;
			if (y + size / 2f > height || y - size / 2f < 0)
				speedY = -speedY;
			x += speedX;
			y += speedY;

		}

		private void display() {
			stroke(color.getRGB());
			fill(color.getRGB(), 120);
			ellipse(x, y, size, size);
		}
	}

	public void init() {
		// TODO Auto-generated method stub
		
	}

}
