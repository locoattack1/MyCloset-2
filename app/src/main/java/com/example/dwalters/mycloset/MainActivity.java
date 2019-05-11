package com.example.dwalters.mycloset;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {
    private ListView mListView;
    String[] listItems;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Closet"); //Changes the title of the page (called label in manifest)

        //Set up information for the listView here
        mListView = (ListView) findViewById(R.id.listView);
        listItems = new String[]{"My Closet", "Contact Info"};

        adapter = new ArrayAdapter<String>(MainActivity.this, R.layout.activity_listview, listItems);

        mListView.setAdapter(adapter);

        //Opens the page that corresponds to the name you click on in the listView
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent();
                switch (position){
                    case 0:
                        i = new Intent(MainActivity.this, MyCloset.class);
                        break;
                    case 1:
                        i = new Intent(MainActivity.this, ContactInfo.class);
                        break;
                }
                startActivity(i);
            }
        });
    }

}