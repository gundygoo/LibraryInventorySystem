package com.dreamteam.softwareengineering.libraryinventorysystem;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EmployeeInventoryOptions extends AppCompatActivity {
    public Button AddInventoryButton;
    public Button DeleteInventoryButton;
    public Button ViewInventoryButton;
    public Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = this;

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_employee_inventory_options);
        AddInventoryButton = (Button) findViewById(R.id.addItemToInventory);
        AddInventoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent EmployeeInventoryAdd= new Intent(context, EmployeeInventoryAdd.class);
                startActivity(EmployeeInventoryAdd);
            }
        });
        DeleteInventoryButton = (Button) findViewById(R.id.deleteItemFromInventory);
        DeleteInventoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent EmployeeInventoryDelete= new Intent(context, EmployeeInventoryDelete.class);
                startActivity(EmployeeInventoryDelete);
            }
        });
        ViewInventoryButton = (Button) findViewById(R.id.viewInventoryButton);
        ViewInventoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent employeeViewInventory= new Intent(context, employeeViewInventory.class);
                startActivity(employeeViewInventory);
            }
        });
    }
}
