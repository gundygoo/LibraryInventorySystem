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
    public EditText customerAgeTextBox;
    public EditText customerHomeAddressTextBox;
    public EditText customerEmailAddressTextBox;
    public EditText customerAuthorityLevelTextBox;
    public EditText customerNameTextBox;
    public Button EditUser;
    public Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_edit_user);
        context = getApplicationContext();
        customerNameTextBox = (EditText) findViewById(R.id.whateverKateeCallsItHere);
        final String customerName = customerNameTextBox.getText().toString();
        customerIdTextBox = (EditText) findViewById(R.id.whateverKateeCallsItHere);
        final String customerIdText = customerIdTextBox.getText().toString();
        customerUserNameTextBox = (EditText) findViewById(R.id.whateverKateeCallsItHere);
        final String customerUsernameText = customerUserNameTextBox.getText().toString();
        customerAuthorityLevelTextBox = (EditText) findViewById(R.id.whateverKateeCallsItHere);
        final String customerAuthorityText = customerAuthorityLevelTextBox.getText().toString();
        customerAgeTextBox = (EditText) findViewById(R.id.whateverKateeCallsItHere);
        final String customerAgeText = customerAgeTextBox.getText().toString();
        customerHomeAddressTextBox = (EditText) findViewById(R.id.whateverKateeCallsItHere);
        final String customerHomeAddressText = customerHomeAddressTextBox.getText().toString();
        customerEmailAddressTextBox = (EditText) findViewById(R.id.whateverKateeCallsItHere);
        final String customerEmailAddressText = customerEmailAddressTextBox.getText().toString();
        EditUser = (Button) findViewById(R.id.idHere);
        EditUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    DatabaseAccessor.ModifyCustomerData(customerName, customerIdText,customerUsernameText,customerAgeText,customerAuthorityText, customerHomeAddressText, customerEmailAddressText, context);
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
