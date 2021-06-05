package com.example.gacrpg;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  implements SearchView.OnQueryTextListener {

    // Declare Variables
    ListView list;
    ListViewAdapter adapter;
    SearchView editsearch;
    ArrayList<AnimalNames> arraylist = new ArrayList<AnimalNames>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Generate sample data
         Resources res = getResources();
        String[] planets = res.getStringArray(R.array.allCats);

        // Locate the ListView in listview_main.xml
        ListView list = (ListView) findViewById(R.id.listview);


        for (int i = 0; i < planets.length; i++) {
            AnimalNames animalNames = new AnimalNames(planets[i]);
            // Binds all strings into an array
            arraylist.add(animalNames);
        }

        // Pass results to ListViewAdapter Class
        adapter = new ListViewAdapter(this, arraylist);

        // Binds the Adapter to the ListView
        list.setAdapter(adapter);

        // Locate the EditText in listview_main.xml
        editsearch = (SearchView) findViewById(R.id.search);
        editsearch.setOnQueryTextListener(this);

        //

        //onclick
        list.setClickable(true);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

                //position is the newest one
                String o = list.getItemAtPosition(position).toString() ;
                AnimalNames pause = (AnimalNames)  list.getItemAtPosition(position);
                String p = "abnormale";
               // String p = pause.getAnimalName(); //this is a hat you want
                //p is the name of the thing'
                //next to link the name to the drawable file.


                Intent startIntent = new Intent(getApplicationContext(), activity_image.class);

                //get it to find R.drawable.ab2

               // startIntent.putExtra("thing_selected", R.drawable.ab2); //this is the one that works
                startIntent.putExtra("thing_selected", p); //this is for testing
                startActivity(startIntent);


    /* write you handling code like...
    String st = "sdcard/";
    File f = new File(st+o.toString());
    // do whatever u want to do with 'f' File object
    */

                //wtf is this code

            }
        });

        //end


    }


    @Override
    public boolean onQueryTextSubmit(String query) {

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String text = newText;
        adapter.filter(text);
        return false;
    }
}