package com.spirograph;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {
    ArrayList<String> listItems=new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     //   setContentView(R.layout.activity_main2);
        final TinyDB tinyDB=new TinyDB(getApplicationContext());

        LinearLayout layout = new LinearLayout(getApplicationContext());
        layout.setOrientation(LinearLayout.VERTICAL);
        ListView listView = new ListView(this);
        ArrayList<Integer> lengths= tinyDB.getListInt("lengths");
        ArrayList<Integer> intAngleIncrements= tinyDB.getListInt("intAngleIncrements");
        tinyDB.remove("lengths");
        tinyDB.remove("intAngleIncrements");
        listItems=tinyDB.getListString("listItems");
        if(lengths.size()>0 || intAngleIncrements.size()>0)
              listItems.add("Length :"+lengths + "Speed:"+ intAngleIncrements);
        tinyDB.putListString("listItems",listItems);
        tinyDB.putListString("listItems",tinyDB.getListString("listItems"));

        ArrayAdapter arrayAdapter=new ArrayAdapter(Main2Activity.this,
                android.R.layout.simple_list_item_1,
                listItems
        );
        listView.setAdapter(arrayAdapter);
        layout.addView(listView);
        setContentView(layout);
    }
}
