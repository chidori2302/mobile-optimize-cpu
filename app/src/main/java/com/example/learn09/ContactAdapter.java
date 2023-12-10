package com.example.learn09;
import static android.content.ContentValues.TAG;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ContactAdapter extends ArrayAdapter<Contact> {
    private Context mContext;
    private List<Contact> mCartItemList;

    public ContactAdapter(Context context, ArrayList<Contact> cartItemList) {
        super(context, 0, cartItemList);
        mContext = context;
        mCartItemList = cartItemList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item_layout, parent, false);
        }

        Contact currentItem = mCartItemList.get(position);

        ImageView itemImageView = convertView.findViewById(R.id.imageView);
        TextView itemNameTextView = convertView.findViewById(R.id.name);
        TextView itemEmailTextView = convertView.findViewById(R.id.email);

        String imageUrl =currentItem.getImageUrl();
        Log.d(TAG, "imageUrl is: " + imageUrl);
        Glide.with(mContext)
                .load(imageUrl)
                .into(itemImageView);
        itemNameTextView.setText(currentItem.getName()+"");
        itemEmailTextView.setText(currentItem.getEmail()+"");

        return convertView;
    }
}
