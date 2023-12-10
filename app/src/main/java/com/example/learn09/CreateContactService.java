package com.example.learn09;

import android.app.Service;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.IBinder;
import android.util.Log;

public class CreateContactService extends Service {
    private static final String TAG = "CreateContactService";
    SQLiteDatabase db;
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int minPrime = intent.getIntExtra("minPrime", 0);
        db= openOrCreateDatabase("ql_lienhe.db", MODE_PRIVATE, null);
        db.execSQL("create table if not exists contact(ID INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT, email TEXT, imageUrl TEXT)");

        String url = intent.getStringExtra("url");
        if (url == null || url.isEmpty()) {
            url = "https://file.coinexstatic.com/2023-11-03/00AAA896B058F8834327A5F2FE3FC9B4.png";
        }
        ContentValues value = new ContentValues();
        value.put("name", intent.getStringExtra("name"));
        value.put("email", intent.getStringExtra("email"));
        new createContactThread(150000, value).start();

        // Trả về một hằng số để chỉ định cách Service sẽ được restart trong trường hợp bị hủy bởi hệ thống
        return Service.START_NOT_STICKY;
    }

    class createContactThread extends Thread {
        int minPrime;
        ContentValues value;
        createContactThread(int minPrime, ContentValues value) {
            this.minPrime = minPrime;
            this.value = value;
        }

        public void run() {
            for (int i = 0; i < minPrime; i++) {
                Log.d(TAG, "Vòng lặp thứ: " + i);
                db.insert("contact", null, this.value);
            }
            Log.d(TAG, "Lưu thành công");
        }
    }
}