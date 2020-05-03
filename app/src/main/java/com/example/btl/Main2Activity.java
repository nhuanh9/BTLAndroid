package com.example.btl;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.btl.simplegame.GameActivity;
import com.example.btl.simplegame.SnakeGamePanel;

public class Main2Activity extends GameActivity {

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

    }
}
