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
    public EditText customerIdTextBox;
    public EditText customerAgeTextBox;
    public EditText customerHomeAddressTextBox;
    public EditText customerEmailAddressTextBox;
    public EditText customerUserNameTextBox;
    public EditText customerFullNameTextBox;
    public Button AddUser;
    public Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_add_user);
        context = getApplicationContext();

        customerPasswordTextBox = (EditText) findViewById(R.id.passwordTextEntry);

        customerUserNameTextBox = (EditText) findViewById(R.id.newUsername);

        customerIdTextBox = (EditText) findViewById(R.id.customerID);

        customerAgeTextBox = (EditText) findViewById(R.id.newAge);

        customerHomeAddressTextBox = (EditText) findViewById(R.id.newAddress);

        customerEmailAddressTextBox = (EditText) findViewById(R.id.newEmailAddress);

        customerFullNameTextBox = (EditText) findViewById(R.id.customerNameEdit);

        AddUser = (Button) findViewById(R.id.editUpdateButton);
        AddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String customerPasswordText = customerPasswordTextBox.getText().toString();
                final String customerUsernameText = customerUserNameTextBox.getText().toString();
                final String customerIdText = customerIdTextBox.getText().toString();
                final String customerAgeText = customerAgeTextBox.getText().toString();
                final String customerHomeAddressText = customerHomeAddressTextBox.getText().toString();
                final String customerEmailAddressText = customerEmailAddressTextBox.getText().toString();
                final String customerFullNameText = customerFullNameTextBox.getText().toString();
                try {
                    DatabaseAccessor.AddCustomerData(customerFullNameText, customerIdText, customerUsernameText,customerPasswordText, customerAgeText, customerHomeAddressText,customerEmailAddressText, context);
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
