package com.example.btl.simplegame;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.btl.simplegame.actors.PositionedActor;


public class ScoreBoard extends PositionedActor {
	private int score;
	private String username;

	public ScoreBoard(int x, int y, int score, String username) {
		super(x, y);
		this.score = score;
		this.username = username;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public ScoreBoard(int x, int y, int width, int height, int score, String username) {
		super(x, y, width, height);
		this.score = score;
		this.username = username;
	}

	public ScoreBoard(AbstractGamePanel context) {
		super(context.getWidth()/2 - 75, 30);
		this.score = 0;
	}

	@Override
	public void stylePaint(Paint p) {
		p.setTextSize(30);
	}
	
	public void earnPoints(int points) {
		score += points;
	}

	@Override
	public void draw(Canvas canvas) {
		canvas.drawText("Score: " + score, getX(), getY(), getPaint());
	}
	public void draw(Canvas canvas, String username) {
		canvas.drawText(username+"'s score: " + score, getX(), getY(), getPaint());
	}
}
