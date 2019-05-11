package com.example.dwalters.mycloset;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditItem extends AppCompatActivity {
    EditText iBrand, iName, iType, iDesc;
    Button editItem, deleteItem;
    int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        Intent inc = getIntent();

        setTitle("Edit Item");

        pos = inc.getExtras().getInt("pos");    //Save the index of the array pos of the selected item

        iBrand = (EditText) findViewById(R.id.itemEditBrand);
        iName = (EditText) findViewById(R.id.itemEditName);
        iType = (EditText) findViewById(R.id.itemEditType);
        iDesc = (EditText) findViewById(R.id.itemEditDesc);
        editItem = (Button) findViewById(R.id.itemEditBtn);
        deleteItem = (Button) findViewById(R.id.itemDelete);

        iBrand.setText(inc.getExtras().getString("brand"));
        iName.setText(inc.getExtras().getString("name"));
        iType.setText(inc.getExtras().getString("type"));
        iDesc.setText(inc.getExtras().getString("desc"));

        editItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Employee editItem = new Employee();

                editItem.brand = iBrand.getText().toString();
                editItem.nameOfItem = iName.getText().toString();
                editItem.typeOfItem = iType.getText().toString();
                editItem.desc = iDesc.getText().toString();

                MyCloset.closetList.set(pos, editItem);
                MyCloset.adapter.notifyDataSetChanged();
                Employee.updateJSONFile(EditItem.this, MyCloset.closetList);
                finish();
            }
        });

        deleteItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyCloset.closetList.remove(pos);
                MyCloset.adapter.notifyDataSetChanged();
                Employee.updateJSONFile(EditItem.this, MyCloset.closetList);
                finish();
            }
        });
    }
}
