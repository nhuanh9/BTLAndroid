package com.example.btl.simplegame;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.btl.simplegame.actors.PositionedActor;


public class ScoreBoard extends PositionedActor {
	private int score;

	public ScoreBoard(AbstractGamePanel context) {
		super(context.getWidth() - 150, 30);
		this.score = 0;
	}

	@Override
	public void stylePaint(Paint p) {
		p.setTextSize(20);
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
