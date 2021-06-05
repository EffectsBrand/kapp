package com.example.gacrpg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.chrisbanes.photoview.PhotoView;

public class activity_image extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);


       //int sessionId = getIntent().getIntExtra("thing_selected",R.drawable.ab2);
       // imageView.setImageResource(R.drawable.ab2);

        String sessionId = getIntent().getStringExtra("thing_selected");//the R.id might have to be an int with a default
        TextView textview =  findViewById(R.id.textView);
        //textview.setText(sessionId); //this is for testing purposes

        PhotoView photoView = (PhotoView) findViewById(R.id.photo_view3);
        //photoView.setImageResource(R.drawable.abnormale); //works
        int test = getStringIdentifier(this, sessionId); //is this right? well it works?
        //photoView.setImageResource(R.drawable.abnormale);
        photoView.setImageResource(test);
       // textview.setText(test+""); //this is for testing purposes

    }

    public static int getStringIdentifier(Context context, String name) {
        return context.getResources().getIdentifier(name, "drawable", context.getPackageName());
    }


}