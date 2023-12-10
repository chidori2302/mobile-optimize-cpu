package com.example.learn09;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;

public class MainActivity extends AppCompatActivity {

    Button btnThem, btnXem, btnQL;
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("Quản lý danh bạ");

        Button btnThem = findViewById(R.id.btnThem);
        Button btnXem = findViewById(R.id.btnXem);
        Button btnXoa = findViewById(R.id.btnXoa);

        btnThem.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent i=new Intent(MainActivity.this, ThemLienHeActivity.class);
                startActivity(i);
            }
        });

        btnXem.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent i=new Intent(MainActivity.this, DanhSachLienHeActivity.class);
                startActivity(i);
            }
        });

        btnXoa.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent i=new Intent(MainActivity.this, XoaLienHeActivity.class);
                startActivity(i);
            }
        });

    }
}