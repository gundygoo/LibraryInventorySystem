package com.dreamteam.softwareengineering.libraryinventorysystem;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

public class employeeViewInventory extends AppCompatActivity {

    List<String> inventoryArray;
    public Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_view_inventory);
        context = getApplicationContext();

        try{
            inventoryArray = DatabaseAccessor.ViewInventory(context);
        } catch(IOException e){
            e.printStackTrace();
        }


        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_employee_view_inventory, inventoryArray);

        ListView listView = (ListView) findViewById(R.id.listviewid);
        listView.setAdapter(adapter);
    }
}
