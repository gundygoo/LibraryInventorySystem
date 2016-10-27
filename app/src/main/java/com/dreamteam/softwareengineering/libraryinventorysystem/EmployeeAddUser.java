package com.dreamteam.softwareengineering.libraryinventorysystem;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

public class EmployeeAddUser extends AppCompatActivity {
    public EditText customerPasswordTextBox;
    public EditText customerUserNameTextBox;
    public EditText customerAuthorityLevelTextBox;
    public Button AddUser;
    public Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_add_user);
        context = getApplicationContext();
        customerPasswordTextBox = (EditText) findViewById(R.id.whateverKateeCallsItHere);
        final String customerPasswordText = customerPasswordTextBox.getText().toString();
        customerUserNameTextBox = (EditText) findViewById(R.id.whateverKateeCallsItHere);
        final String customerUsernameText = customerUserNameTextBox.getText().toString();
        customerAuthorityLevelTextBox = (EditText) findViewById(R.id.whateverKateeCallsItHere);
        final String customerAuthorityText = customerAuthorityLevelTextBox.getText().toString();
        AddUser = (Button) findViewById(R.id.idHere);
        AddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    DatabaseAccessor.AddNewUserLogin( customerUsernameText,customerPasswordText,customerAuthorityText, context);
                    Intent employeeWelcome = new Intent(context, EmployeeWelcome.class);
                    startActivity(employeeWelcome);
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(context, "Error creating customer", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
