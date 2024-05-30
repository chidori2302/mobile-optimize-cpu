package com.example.learn09;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DanhSachLienHeActivity extends AppCompatActivity {

    ListView lvTG;
    private DatabaseReference databaseReference;
    private ContactAdapter contactAdapter;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> dataList = new ArrayList<>();
    private ArrayList<Contact> contactItemList = new ArrayList<Contact>();
    private ArrayList<String> dataUrl = new ArrayList<>();
    private Contact lienHe;
    private int offset = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_lien_he);

        getSupportActionBar().setTitle("Danh sách liên hệ");
        lvTG = findViewById(R.id.lvTG);
        contactAdapter = new ContactAdapter(DanhSachLienHeActivity.this, contactItemList);

        new getContact().execute(offset);
        lvTG.setAdapter(contactAdapter);

//        lvTG.setOnScrollListener(new AbsListView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(AbsListView view, int scrollState) {
//                // Do something on scroll state change if needed
//            }
//
//            @Override
//            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//                // Check if the user is at the bottom of the list
//                int lastVisibleItem = firstVisibleItem + visibleItemCount;
//                if (lastVisibleItem == totalItemCount && totalItemCount != 0) {
//                    offset++;
//                    new getContact().execute(offset);
//                }
//
//
//            }
//        });

    }

    private final class getContact extends AsyncTask<Integer, Void, String> {

        @Override
        protected String doInBackground(Integer... params) {

            try {
                SQLiteDatabase db= openOrCreateDatabase("ql_lienhe.db", MODE_PRIVATE, null);
                db.execSQL("create table if not exists contact(ID INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT, email TEXT, imageUrl TEXT)");

                String tableName = "contact";
                String selection = null; // your selection criteria
                String groupBy = null; // group by clause
                String having = null; // having clause
                String orderBy = null; // order by clause
                String limit = "10"; // limit the number of rows returned
                String offset = params[0]+""; // offset to skip the first 20 rows

                String query = "SELECT * FROM " + tableName +
                        (selection != null ? " WHERE " + selection : "") +
                        (groupBy != null ? " GROUP BY " + groupBy : "") +
                        (having != null ? " HAVING " + having : "") +
                        (orderBy != null ? " ORDER BY " + orderBy : "")
//                        +
//                        (limit != null ? " LIMIT " + limit : "") +
//                        (offset != null ? " OFFSET " + offset : "")
                        ;

                Cursor cursor = db.rawQuery(query, null);

                if (cursor.moveToFirst()) {
                    do {
                        @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("name"));
                        @SuppressLint("Range") String email = cursor.getString(cursor.getColumnIndex("email"));
                        @SuppressLint("Range") String url = cursor.getString(cursor.getColumnIndex("imageUrl"));
                        lienHe = new Contact(name, email, url);
                        contactItemList.add(lienHe);
                        contactAdapter.notifyDataSetChanged();
                    } while (cursor.moveToNext());
                }
            }
            catch(Exception e) {
                return e.getMessage();
            }

            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) {
            Log.d(TAG, "Trang "+offset);
        }
    }
}

