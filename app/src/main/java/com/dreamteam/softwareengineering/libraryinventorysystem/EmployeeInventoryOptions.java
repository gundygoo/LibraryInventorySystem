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
        context = getApplicationContext();

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_employee_inventory_options);
        AddInventoryButton = (Button) findViewById(R.id.deleteUserButton);
        AddInventoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent EmployeeInventoryAdd= new Intent(context, employeeCheckIn.class);
                startActivity(EmployeeInventoryAdd);
            }
        });
        DeleteInventoryButton = (Button) findViewById(R.id.editUserButton);
        DeleteInventoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent EmployeeInventoryDelete= new Intent(context, EmployeeCheckOut.class);
                startActivity(EmployeeInventoryDelete);
            }
        });
        ViewInventoryButton = (Button) findViewById(R.id.addUserButton);
        ViewInventoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent employeeViewInventory= new Intent(context, EmployeeInventoryOptions.class);
                startActivity(employeeViewInventory);
            }
        });
    }
}
