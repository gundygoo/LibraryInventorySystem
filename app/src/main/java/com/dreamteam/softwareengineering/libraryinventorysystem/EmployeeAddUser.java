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
        context = this;

        customerPasswordTextBox = (EditText) findViewById(R.id.passwordTextEntry);

        customerUserNameTextBox = (EditText) findViewById(R.id.customerUsernameAdd);

        customerAgeTextBox = (EditText) findViewById(R.id.customerAgeAdd);

        customerHomeAddressTextBox = (EditText) findViewById(R.id.customerAddressAdd);

        customerEmailAddressTextBox = (EditText) findViewById(R.id.customerEmailAdd);

        customerFullNameTextBox = (EditText) findViewById(R.id.customerFullNameAdd);

        AddUser = (Button) findViewById(R.id.addUserAccountSubmitButton);
        AddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String customerPasswordText = customerPasswordTextBox.getText().toString();
                final String customerUsernameText = customerUserNameTextBox.getText().toString();
                final String customerAgeText = customerAgeTextBox.getText().toString();
                final String customerHomeAddressText = customerHomeAddressTextBox.getText().toString();
                final String customerEmailAddressText = customerEmailAddressTextBox.getText().toString();
                final String customerFullNameText = customerFullNameTextBox.getText().toString();
                try {
                    DatabaseAccessor.AddCustomerData(customerFullNameText, "007", customerUsernameText,customerPasswordText, customerAgeText, customerHomeAddressText,customerEmailAddressText, context);
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
