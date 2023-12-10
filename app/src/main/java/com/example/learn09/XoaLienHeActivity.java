package com.example.learn09;

import static android.content.ContentValues.TAG;

import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class XoaLienHeActivity extends AppCompatActivity {

    ListView lvTG;
    private DatabaseReference databaseReference;
    private TextView deleteStatus;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> dataList = new ArrayList<>();
    private ArrayList<Contact> contactItemList = new ArrayList<Contact>();
    private ArrayList<String> dataUrl = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xoa_lien_he);

        deleteStatus = findViewById(R.id.deleteStatus);
        getSupportActionBar().setTitle("Xóa liên hệ");

        new deleteContact().execute(0);
    }

    private final class deleteContact extends AsyncTask<Integer, Void, String> {

        @Override
        protected String doInBackground(Integer... params) {

            try {
                SQLiteDatabase db= openOrCreateDatabase("ql_lienhe.db", MODE_PRIVATE, null);
                db.execSQL("DROP TABLE IF EXISTS contact");
                db.close();
                deleteStatus.setText("Đã xóa xong");
            }
            catch(Exception e) {
                return e.getMessage();
            }
            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) {
            Log.d(TAG, "Bắt đầu submit");
        }
    }
}

