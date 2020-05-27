package com.example.btl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btnStart, btnHD, btnLoad;
    EditText editTextName;
    ListView lsPlayers;
    public static DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnStart = findViewById(R.id.btnStart);
        btnHD = findViewById(R.id.btnHD);
        btnLoad = findViewById(R.id.buttonLoad);
        editTextName = findViewById(R.id.editTextName);
        lsPlayers = findViewById(R.id.lvPlayers);
        dbHelper = new DBHelper(MainActivity.this);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra("username", editTextName.getText().toString());
                startActivity(intent);
            }
        });
        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor1 = DBHelper.getAllRecordOrderByScore();
                PlayerCursorAdapter adapter = new PlayerCursorAdapter(
                        MainActivity.this,
                        R.layout.activity_dong,
                        cursor1,
                        0
                );
                lsPlayers.setAdapter(adapter);
            }
        });

        btnHD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "-Dùng A, S, D, W để di chuyển rắn.\n-Ấn G để bắt đầu trò chơi mới, F để tạm dừng!"+ DBHelper.checkIfExistName("Name 2 xxxxx"), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        dbHelper.openDB();
    }

    public static void open() {
        dbHelper.openDB();
    }
    public static void goToMain(Intent intent) {

    }
    @Override
    protected void onStop() {
        super.onStop();
        dbHelper.closeDB();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
