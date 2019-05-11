package com.example.dwalters.mycloset;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddItem extends AppCompatActivity {
    EditText iBrand, iName, iType, iDesc;
    Button addItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        iBrand = (EditText) findViewById(R.id.itemAddBrand);
        iName = (EditText) findViewById(R.id.itemAddName);
        iType = (EditText) findViewById(R.id.itemAddType);
        iDesc = (EditText) findViewById(R.id.itemAddDesc);
        addItem = (Button) findViewById(R.id.itemAddBtn);

        setTitle("Add Item");

        //When "addItem" is pressed, pass all information through to MyCloset.java
        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Employee itemNew = new Employee();

                itemNew.brand = iBrand.getText().toString();
                itemNew.nameOfItem = iName.getText().toString();
                itemNew.typeOfItem = iType.getText().toString();
                itemNew.desc = iDesc.getText().toString();

                MyCloset.closetList.add(itemNew);
                MyCloset.adapter.notifyDataSetChanged();
                Employee.updateJSONFile(AddItem.this, MyCloset.closetList);
                finish();
            }
        });
    }
}