package com.example.btl;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;

import com.example.btl.simplegame.GameActivity;
import com.example.btl.simplegame.SnakeGamePanel;

public class Main2Activity extends GameActivity {
    public static boolean openMain = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        String username = bundle.getString("username");
        //before activity is created : cold start
        //switch back to original Theme (App Theme)
        setTheme(R.style.AppTheme);
        switchFullscreen();
        setContentView(new SnakeGamePanel(this, username));
        while (openMain) {
            Intent myIntent = new Intent(this, MainActivity.class);
            startActivity(myIntent);
            openMain = false;
        }

    }

}
