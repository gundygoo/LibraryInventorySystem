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

public class EmployeeEditUser extends AppCompatActivity {
    public EditText customerIdTextBox;
    public EditText customerUserNameTextBox;
    public EditText customerPasswordTextBox;
    public EditText customerAgeTextBox;
    public EditText customerHomeAddressTextBox;
    public EditText customerEmailAddressTextBox;
    public EditText customerNameTextBox;
    public Button EditUser;
    public Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_edit_user);
        context = getApplicationContext();
        customerNameTextBox = (EditText) findViewById(R.id.customerNameEdit);

        customerIdTextBox = (EditText) findViewById(R.id.customerID);

        customerUserNameTextBox = (EditText) findViewById(R.id.newUsername);

        customerPasswordTextBox = (EditText) findViewById(R.id.newPassword);

        customerAgeTextBox = (EditText) findViewById(R.id.newAge);

        customerHomeAddressTextBox = (EditText) findViewById(R.id.newAddress);

        customerEmailAddressTextBox = (EditText) findViewById(R.id.newEmailAddress);

        EditUser = (Button) findViewById(R.id.editUpdateButton);
        EditUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String customerName = customerNameTextBox.getText().toString();
                final String customerIdText = customerIdTextBox.getText().toString();
                final String customerUsernameText = customerUserNameTextBox.getText().toString();
                final String customerPasswordText = customerPasswordTextBox.getText().toString();
                final String customerAgeText = customerAgeTextBox.getText().toString();
                final String customerHomeAddressText = customerHomeAddressTextBox.getText().toString();
                final String customerEmailAddressText = customerEmailAddressTextBox.getText().toString();
                try {
                    DatabaseAccessor.ModifyCustomerData(customerName, customerIdText,customerUsernameText, customerPasswordText, customerAgeText, customerHomeAddressText, customerEmailAddressText, context);
                    Intent employeeWelcome = new Intent(context, EmployeeWelcome.class);
                    startActivity(employeeWelcome);
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(context, "Error editing customer", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
