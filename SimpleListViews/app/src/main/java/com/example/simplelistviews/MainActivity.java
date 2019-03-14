package com.example.simplelistviews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String[] colors = {"Red", "Blue", "Green", "White", "Black", "Yellow", "Violet", "Orange"};

    Color[] colorObject = {new Color("Red"), new Color("Green"), new Color("Yellow"),
                            new Color("Black"), new Color("Blue"), new Color("White"),
                            new Color("Orange")};

    ArrayList<Color> colorArrayList = new ArrayList<>();

    ArrayAdapter<Color> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.listView);

        //Simple List View of Array
       /* ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                    android.R.id.text1, colors);*/

       //Simple List View of Objects from array
        /*ArrayAdapter<Color> adapter = new ArrayAdapter<Color>(this, android.R.layout.simple_list_item_1,
                android.R.id.text1, colorObject);*/

        //Simple List View of Objects from arrayList

        colorArrayList.add(new Color("Red"));
        colorArrayList.add(new Color("Yellow"));

        final ArrayAdapter<Color> adapter = new ArrayAdapter<Color>(this, android.R.layout.simple_list_item_1,
                android.R.id.text1, colorArrayList);

        listView.setAdapter(adapter);

        /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("demo", "Clicked on position "+ position +", Color is "+colorObject[position]);
            }
        });*/


        //Updating the List view Dynamically
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Color color = adapter.getItem(position);
                colorArrayList.remove(color);
                //adapter.remove(color);
                adapter.notifyDataSetChanged();
                return false;
            }
        });

        findViewById(R.id.addButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = findViewById(R.id.editText);
                colorArrayList.add(new Color(editText.getText().toString()));
                //adapter.add(new Color(editText.getText().toString()));
                adapter.notifyDataSetChanged();
            }
        });
    }

    static class Color{
        String name;

        public Color(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
