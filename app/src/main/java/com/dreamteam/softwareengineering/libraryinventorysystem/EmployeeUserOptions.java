package com.dreamteam.softwareengineering.libraryinventorysystem;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.view.View.OnClickListener;


public class EmployeeUserOptions extends AppCompatActivity {
    public Button AddUsersButton;
    public Button EditUsersButton;
    public Button deleteUserButton;
    public Button ViewCustomerButton;

    public Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_user_options);
        context = this;

        AddUsersButton = (Button) findViewById(R.id.addUserButton);
        AddUsersButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent employeeAddUser= new Intent(context, EmployeeAddUser.class);
                startActivity(employeeAddUser);
            }
        });

        EditUsersButton = (Button) findViewById(R.id.editUserButton);
        EditUsersButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent employeeEditUser= new Intent(context, EmployeeEditUser.class);
                startActivity(employeeEditUser);
            }
        });

        deleteUserButton = (Button) findViewById(R.id.deleteUserButton);
        deleteUserButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Not Implemented", Toast.LENGTH_SHORT).show();
            }
        });

        ViewCustomerButton = (Button) findViewById(R.id.viewCustomer);
        ViewCustomerButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent employeeViewUser= new Intent(context, EmployeeViewCustomer.class);
                startActivity(employeeViewUser);
            }
        });
    }
}
