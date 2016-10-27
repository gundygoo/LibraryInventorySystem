package com.dreamteam.softwareengineering.libraryinventorysystem;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EmployeeWelcome extends AppCompatActivity {

    public Button CheckInButton;
    public Button CheckOutButton;
    public Button InventoryButton;
    public Button UsersButton;
    public Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = getApplicationContext();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_welcome);
        CheckInButton = (Button) findViewById(R.id.checkInButtonWelcome);
        CheckInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent employeeCheckIn= new Intent(context, employeeCheckIn.class);
                startActivity(employeeCheckIn);
            }
        });
        CheckOutButton = (Button) findViewById(R.id.checkOutBookWelcome);
        CheckOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent employeeCheckOut= new Intent(context, EmployeeCheckOut.class);
                startActivity(employeeCheckOut);
            }
        });
        InventoryButton = (Button) findViewById(R.id.inventoryWelcome);
        InventoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent employeeInventory= new Intent(context, EmployeeInventoryOptions.class);
                startActivity(employeeInventory);
            }
        });
        UsersButton = (Button) findViewById(R.id.users);
        UsersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent employeeUsers= new Intent(context, EmployeeUserOptions.class);
                startActivity(employeeUsers);
            }
        });
    }
}
