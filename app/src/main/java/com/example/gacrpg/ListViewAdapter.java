package com.example.gacrpg;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.gacrpg.AnimalNames;
import com.example.gacrpg.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ListViewAdapter extends BaseAdapter {

    // Declare Variables

    Context mContext;
    LayoutInflater inflater;
    private List<AnimalNames> visibleList ; //was told this was redundnant = null;
    private List<AnimalNames> animalNamesList ; //was told this was redundnant = null;
    private ArrayList<AnimalNames> arraylist;
    private ArrayList<AnimalNames> visArraylist;//69 added

    //can i make the original visible from the get
    //is it compatible with the code for the picutre

    public ListViewAdapter(Context context, List<AnimalNames> animalNamesList, List<AnimalNames> visibleList) {
        mContext = context;
        this.animalNamesList = visibleList; //animalNamesList
        this.visibleList = visibleList;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<>();//ArrayList<AnimalNames> was told by codeman to change to this
        this.arraylist.addAll(animalNamesList);

        this.visArraylist = new ArrayList<>();// //ArrayList<AnimalNames> was told by codeman to change to this
        this.visArraylist.addAll(visibleList);//
    }

    public class ViewHolder {
        TextView name;
    }

    @Override
    public int getCount() {
        return animalNamesList.size();
    }

    @Override
    public AnimalNames getItem(int position) {
        return animalNamesList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;


        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.list_view_items, null);
            // Locate the TextViews in listview_item.xml
            holder.name = view.findViewById(R.id.name);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews

        holder.name.setText(animalNamesList.get(position).getAnimalName()); //this is part of the problem. aniamlsNamesList is the searchlist. visibleList causes bugsin the seaching. visArraylist causes the same bugs

        if (AnimalLeash(animalNamesList.get(position))){//this is what we wanttttt except this is always the first and we want it to recognize the parent category.
            view.setBackgroundColor(Color.parseColor("#bae0dd"));

            //
        }

            if(arraylist.get(visArraylist.indexOf(visibleList.get(position))).getAnimalName().contains("!")){
                view.setBackgroundColor(Color.parseColor("#bae0dd"));
            }else{
                view.setBackgroundColor(Color.parseColor("#e0babd")); //this also stops some color bugs, where you
            }

        Log.d("myTabby2", position + " " +arraylist.get(visArraylist.indexOf(visibleList.get(position))).getAnimalName() + " " +   visibleList.get(position).getAnimalName() + " " +  animalNamesList.get(position).getAnimalName() + "  " + visArraylist.get(position).getAnimalName() );
//take animalNamesList or visibleList and look it up in visArrayList.
        //one second we need to fic the bug, when it gets both and you go back to begging and then reset.
        // find the equ in arraylist
        return view;
    }
    public boolean AnimalLeash(AnimalNames input){
        boolean blue = false;


            if(input.getAnimalName().toLowerCase(Locale.getDefault()).contains("!")){
                blue=true;
            }
        return blue;
    }
    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        animalNamesList.clear();
        Log.d("myTag", arraylist.get(0).getAnimalName() +" | " + arraylist.get(1).getAnimalName() + " | " + arraylist.get(2).getAnimalName() );

        if (charText.length() == 0) {
            animalNamesList.addAll(visArraylist); //611 visArraylist
        } else {

            for (AnimalNames wp : arraylist) {
                if (wp.getAnimalName().toLowerCase(Locale.getDefault()).contains(charText)) {
                    Log.d("myTag2", visArraylist.get(0).getAnimalName() +" | " + visArraylist.get(1).getAnimalName() + " | " + visArraylist.get(2).getAnimalName() );
                    //animalNamesList.add(wp); //this works but is so outdated
                    int index = arraylist.indexOf(wp);
                    AnimalNames p = visArraylist.get(index);
                    AnimalNames tempName = new AnimalNames(index + "test");
                    animalNamesList.add(p); //okay so this can show a single one, but in reality, i want to show the one that the linked list.

                }
            }
        }
        notifyDataSetChanged();
    }
}