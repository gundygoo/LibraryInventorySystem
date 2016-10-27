package com.dreamteam.softwareengineering.libraryinventorysystem;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class employeeViewInventory extends AppCompatActivity {

    List<String> inventoryArray;
    public Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_view_inventory);
        context = this;

        inventoryArray = new ArrayList<>();

        try{
            inventoryArray = DatabaseAccessor.ViewInventory(context);
        } catch(IOException e){
            e.printStackTrace();
        }


        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.simple_list_item, inventoryArray);

        ListView listView = (ListView) findViewById(R.id.inventoryList);
        listView.setAdapter(adapter);
    }
}
