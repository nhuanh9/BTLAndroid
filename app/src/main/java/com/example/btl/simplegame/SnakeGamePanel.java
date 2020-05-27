package com.example.btl.simplegame;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

import com.example.btl.DBHelper;
import com.example.btl.MainActivity;
import com.example.btl.Player;

import java.util.ArrayList;
import java.util.Random;

import static com.example.btl.Main2Activity.openMain;
import static com.example.btl.MainActivity.dbHelper;


public class SnakeGamePanel extends AbstractGamePanel implements View.OnTouchListener {
    private Context context;
    public SnakeGamePanel(Context context) {
        super(context);
    }

    public SnakeGamePanel(Context context, String userName) {
        super(context);
        this.context = context;
        this.userName = userName;
    }

    private String userName;
    private SnakeActor snake;
    private AppleActor apple;
    private ScoreBoard score;
    private boolean isPaused = false;

    @Override
    public void onStart() {
        this.snake = new SnakeActor(100, 100);
        this.apple = new AppleActor(200, 50);
        this.score = new ScoreBoard(this);
    }

    @Override
    public void onTimer() {
        if (!isPaused) {
            if (this.snake.checkBoundsCollision(this)) {
                this.snake.setEnabled(false);
            }
            this.snake.move();
            if (this.apple.intersect(this.snake)) {
                this.snake.grow();
                this.score.earnPoints(50);
                this.apple.reposition(this);
            }
        }
    }

    @Override
    public void redrawCanvas(Canvas canvas) {
        if (this.snake.isEnabled()) {
            this.snake.draw(canvas);
            this.apple.draw(canvas);
            this.score.draw(canvas, userName);
        } else {
            Paint p = getPaint();
            p.setTextSize(50);
            p.setColor(Color.RED);
            canvas.drawText("Game over! " + userName + "'s score: " + this.score.getScore(), 100, 300, p);
            Random rd = new Random();
            int id = rd.nextInt((100000 - 10 + 1) + 10);
            MainActivity.open();
            try {
                long result = DBHelper.Insert(id, userName, this.score.getScore());
                stopGameLoop();
            } catch (Exception e){
                openMain = true;
            }

        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        this.snake.handleKeyInput(keyCode);
        if (keyCode == KeyEvent.KEYCODE_G) {
            this.onStart();
            openMain = true;
        }
        if (keyCode == KeyEvent.KEYCODE_P) {
            isPaused = !isPaused;
        }
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            this.snake.handleTouchInput(event);
            return true;
        }
        return false;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }
}
