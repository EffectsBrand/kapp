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
import java.util.Arrays;

public class MainActivity extends AppCompatActivity  implements SearchView.OnQueryTextListener {


    ListView list;
    ListViewAdapter adapter;
    SearchView editsearch;
    ArrayList<AnimalNames> arraylist = new ArrayList<>(); //told to change <AnimalNames> to just <>
    ArrayList<AnimalNames> visList = new ArrayList<>();//told to change <AnimalNames> to just <>


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Generate sample data
        Resources res = getResources();

       // String[] planets = res.getStringArray(R.array.allCats); //this is what was correct, turned off for testing purposes
        String[] planets = res.getStringArray(R.array.test2);
        String[] visArr = res.getStringArray(R.array.test1);
        String[] imArr = res.getStringArray(R.array.test3);

        // Locate the ListView in listview_main.xml
        ListView list =  findViewById(R.id.listview);


        for (String planet : planets) {
            AnimalNames animalNames = new AnimalNames(planet);
            // Binds all strings into an array
            arraylist.add(animalNames);
        } //i created a class called AnimalNames
        //this list is the list you search through


        for (String s : visArr) {
            AnimalNames tempName = new AnimalNames(s);
            visList.add(tempName);
        } //same thing, this one should be the list you see

        // Pass results to ListViewAdapter Class
        adapter = new ListViewAdapter(this, arraylist, visList);

        // Binds the Adapter to the ListView
        list.setAdapter(adapter);

        // Locate the EditText in listview_main.xml
        editsearch =  findViewById(R.id.search);
        editsearch.setOnQueryTextListener(this);


        //onclick
        list.setClickable(true);
        list.setOnItemClickListener((arg0, arg1, position, arg3) -> {

            //position is the newest one
            String o = list.getItemAtPosition(position).toString() ;
            AnimalNames pause = (AnimalNames)  list.getItemAtPosition(position);

            String p = pause.getAnimalName(); //this is a hat you want
            //p is the name of the thing you can click. ppre-filter is the searchable array, once you filter it's the visible array. this can be fixed but i will just work around it,.

            Intent startIntent = new Intent(getApplicationContext(), activity_image.class);
            //next to link the name to the drawable file.
            //get it to find R.drawable.ab2. Depends on whether it's looking at searchArray or visibleArray. THis may be unnecessary if I fix earlier bugs.
            int index = Arrays.asList(planets).indexOf(p);
            if (index==-1){
                index = Arrays.asList(visArr).indexOf(p);
            }

            String imSnippet = imArr[index];
          //  AnimalNames p = visArraylist.get(index);

           startIntent.putExtra("thing_selected", imSnippet); //brings image file into the conversation
           // startIntent.putExtra("thing_selected", p); //this is for testing
            startActivity(startIntent);

        });

        //end
    }

    @Override
    public boolean onQueryTextSubmit(String query) {

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        adapter.filter(newText);
        return false;
    }
}