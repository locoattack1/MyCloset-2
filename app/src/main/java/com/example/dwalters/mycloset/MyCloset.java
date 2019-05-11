package com.example.dwalters.mycloset;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import java.util.ArrayList;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MyCloset extends AppCompatActivity {
    private ListView mListView;
    public static ArrayList<Employee> closetList;
    public static ArrayAdapter<Employee> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_closet);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        setTitle("My Closet");
        closetList = Employee.getEmployeesFromFile("employeeslocal.json", this);

        mListView = (ListView) findViewById(R.id.listView2);

        adapter = new ArrayAdapter<Employee>(MyCloset.this, R.layout.activity_listview, closetList);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Employee selectedItem = closetList.get(position);

                Intent i = new Intent(MyCloset.this, EditItem.class);

                i.putExtra("edit", 1);  //flag
                i.putExtra("brand", selectedItem.brand);
                i.putExtra("name", selectedItem.nameOfItem);
                i.putExtra("type", selectedItem.typeOfItem);
                i.putExtra("desc", selectedItem.desc);
                i.putExtra("pos", position);

                startActivity(i);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_add) {
            Intent i = new Intent(MyCloset.this, AddItem.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }
}