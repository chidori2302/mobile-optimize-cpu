package com.example.learn09;

import static android.content.ContentValues.TAG;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;

public class PrintLienHeActivity extends AppCompatActivity {

    Button btnLuu1Thread, btnLuu4Thread;
    Contact contact;
    ContentValues value;
    SQLiteDatabase db;
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print_lien_he);

        getSupportActionBar().setTitle("Thêm liên hệ");


        db= openOrCreateDatabase("ql_lienhe.db", MODE_PRIVATE, null);
        db.execSQL("create table if not exists contact(ID INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT, email TEXT, imageUrl TEXT)");

        Button btnLuu1Thread = findViewById(R.id.btnLuu1Thread);
        Button btnLuu4Thread = findViewById(R.id.btnLuu4Thread);

        btnLuu1Thread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                new createContactThread(100000).start();
            }
        });
        btnLuu4Thread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                createContactThread thread1 = new createContactThread(25000);
                createContactThread thread2 = new createContactThread(25000);
                createContactThread thread3 = new createContactThread(25000);
                createContactThread thread4 = new createContactThread(25000);
//                createContactThread thread5 = new createContactThread(25000);
//                createContactThread thread6 = new createContactThread(25000);

                thread1.start();
                thread2.start();
                thread3.start();
                thread4.start();
//                thread5.start();
//                thread6.start();
            }
        });
    }

    class createContactThread extends Thread {
        int minPrime;
        createContactThread(int minPrime) {
            this.minPrime = minPrime;
        }

        public void run() {
            // compute primes larger than minPrime

            long currentTimeMillis = System.currentTimeMillis();
            for (int i = 0; i < this.minPrime; i++) {
                Log.d(TAG, "Vòng lặp thứ: " + i);
//                db.insert("contact", null, value);
            }
            long lastTimeMillis = System.currentTimeMillis();
            long time = lastTimeMillis - currentTimeMillis;
            Log.d(TAG, "Thời gian xử lý: " + time + "ms");
            Log.d(TAG,"Lưu thành công" );
        }
    }
}
