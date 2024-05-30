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

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ThemLienHeActivity extends AppCompatActivity {

    EditText editMaTG, editTenTG, editUrl;
    Button btnLuu, btnLuuTG, btnLuu100000, btnLuuService, btnLuu1Thread, btnLuu4Thread;
    Contact contact;
    ContentValues value;
    SQLiteDatabase db;
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_lien_he);

        getSupportActionBar().setTitle("Thêm liên hệ");


        db= openOrCreateDatabase("ql_lienhe.db", MODE_PRIVATE, null);
        db.execSQL("create table if not exists contact(ID INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT, email TEXT, imageUrl TEXT)");

        editMaTG=findViewById(R.id.editMaTG);
        editTenTG=findViewById(R.id.editTenTG);
        editUrl=findViewById(R.id.editUrl);

        Button btnLuu = findViewById(R.id.btnLuu);
        Button btnLuuTG = findViewById(R.id.btnLuuTG);
        Button btnLuu100000 = findViewById(R.id.btnLuu100000);
        Button btnLuuService = findViewById(R.id.btnLuuService);
        Button btnLuu1Thread = findViewById(R.id.btnLuu1Thread);
        Button btnLuu4Thread = findViewById(R.id.btnLuu4Thread);

        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                try {
                    String url = editUrl.getText() + "";
                    if (url.length() == 0) {
                        url = "https://file.coinexstatic.com/2023-11-03/00AAA896B058F8834327A5F2FE3FC9B4.png";
                    }
                    value= new ContentValues();
                    value.put("name", editMaTG.getText()+"");
                    value.put("email", editTenTG.getText()+"");
                    value.put("imageUrl", url);
                    long currentTimeMillis = System.currentTimeMillis();
                    for (int i = 0; i < 100000; i++) {
                        Log.d(TAG, "Vòng lặp thứ: " + i);
                        db.insert("contact", null, value);
                    }
                    long lastTimeMillis = System.currentTimeMillis();
                    long time = lastTimeMillis - currentTimeMillis;
                    Log.d(TAG, "Thời gian xử lý: " + time + "ms");
                    Toast.makeText(ThemLienHeActivity.this, "Thêm liên hệ thành công", Toast.LENGTH_LONG).show();

//                    Intent i=new Intent(ThemLienHeActivity.this, DanhSachLienHeActivity.class);
//                    startActivity(i);
                }
                catch(Exception e) {
                    Toast.makeText(ThemLienHeActivity.this, "Thêm liên hệ thất bại", Toast.LENGTH_LONG).show();

                }
            }
        });
        btnLuuTG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                new createContact().execute(1);
            }
        });
        btnLuu100000.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                new createContact().execute(100000);
            }
        });
        btnLuuService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent serviceIntent = new Intent(ThemLienHeActivity.this, CreateContactService.class);
                String url = editUrl.getText() + "";
                if (url.length() == 0) {
                    url = "https://file.coinexstatic.com/2023-11-03/00AAA896B058F8834327A5F2FE3FC9B4.png";
                }
                serviceIntent.putExtra("minPrime", 100000);
                serviceIntent.putExtra("url", url);
                serviceIntent.putExtra("name", editMaTG.getText().toString());
                serviceIntent.putExtra("email", editTenTG.getText().toString());
                startService(serviceIntent);
            }
        });
        btnLuu1Thread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                new createContactThread(100000).start();
            }
        });
//        btnLuu4Thread.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View arg0) {
//                createContactThread thread1 = new createContactThread(25000);
//                createContactThread thread2 = new createContactThread(25000);
//                createContactThread thread3 = new createContactThread(25000);
//                createContactThread thread4 = new createContactThread(25000);
//                createContactThread thread5 = new createContactThread(25000);
//                createContactThread thread6 = new createContactThread(25000);
//
//                thread1.start();
//                thread2.start();
//                thread3.start();
//                thread4.start();
//                thread5.start();
//                thread6.start();
//            }
//        });
    }

    private final class createContact extends AsyncTask<Integer, Void, String> {

        @Override
        protected String doInBackground(Integer... params) {

            try {
                String url = editUrl.getText() + "";
                if (url.length() == 0) {
                    url = "https://file.coinexstatic.com/2023-11-03/00AAA896B058F8834327A5F2FE3FC9B4.png";
                }
                value= new ContentValues();
                value.put("name", editMaTG.getText()+"");
                value.put("email", editTenTG.getText()+"");
                value.put("imageUrl", url);
                long currentTimeMillis = System.currentTimeMillis();
                for (int i = 0; i < params[0]; i++) {
                    Log.d(TAG, "Vòng lặp thứ: " + i);
                    db.insert("contact", null, value);
                }
                long lastTimeMillis = System.currentTimeMillis();
                long time = lastTimeMillis - currentTimeMillis;
                Log.d(TAG, "Thời gian xử lý: " + time + "ms");
            }
            catch(Exception e) {
                return e.getMessage();
            }
            return "Lưu thành công";
        }

        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(ThemLienHeActivity.this, "Thêm liên hệ thành công", Toast.LENGTH_LONG).show();

//            Intent i=new Intent(ThemLienHeActivity.this, DanhSachLienHeActivity.class);
//            startActivity(i);
            Log.d(TAG, result);
        }
    }

    class createContactThread extends Thread {
        int minPrime;
        createContactThread(int minPrime) {
            this.minPrime = minPrime;
        }

        public void run() {
            // compute primes larger than minPrime
            String url = editUrl.getText() + "";
            if (url.length() == 0) {
                url = "https://file.coinexstatic.com/2023-11-03/00AAA896B058F8834327A5F2FE3FC9B4.png";
            }
            value= new ContentValues();
            value.put("name", editMaTG.getText()+"");
            value.put("email", editTenTG.getText()+"");
            value.put("imageUrl", url);
            long currentTimeMillis = System.currentTimeMillis();
            for (int i = 0; i < this.minPrime; i++) {
                Log.d(TAG, "Vòng lặp thứ: " + i);
                db.insert("contact", null, value);
            }
            long lastTimeMillis = System.currentTimeMillis();
            long time = lastTimeMillis - currentTimeMillis;
            Log.d(TAG, "Thời gian xử lý: " + time + "ms");
            Log.d(TAG,"Lưu thành công" );
        }
    }
}
