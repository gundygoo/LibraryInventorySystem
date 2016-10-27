package com.dreamteam.softwareengineering.libraryinventorysystem;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EmployeeUserOptions extends AppCompatActivity {
    public Button AddUsersButton;
    public Button EditUsersButton;

    public Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_user_options);
        AddUsersButton = (Button) findViewById(R.id.addUserButton);
        AddUsersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent employeeAddUser= new Intent(context, EmployeeAddUser.class);
                startActivity(employeeAddUser);
            }
        });
        EditUsersButton = (Button) findViewById(R.id.editUserButton);
        EditUsersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent employeeEditUser= new Intent(context, EmployeeEditUser.class);
                startActivity(employeeEditUser);
            }
        });
    }
}
