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

public class EmployeeCheckOut extends AppCompatActivity {
    public EditText bookNameTextBox;
    public EditText customerUserNameTextBox;
    public Button checkOut;
    public Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_check_out);
        context = getApplicationContext();
        bookNameTextBox = (EditText) findViewById(R.id.checkInBookName);
        final String bookNameText = bookNameTextBox.getText().toString();
        customerUserNameTextBox = (EditText) findViewById(R.id.checkInCustomerUser);
        final String customerUsernameText = customerUserNameTextBox.getText().toString();
        checkOut = (Button) findViewById(R.id.checkOutButton);
        checkOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    DatabaseAccessor.CheckinBookFromCustomer(bookNameText, customerUsernameText, context);
                    Intent employeeWelcome = new Intent(context, EmployeeWelcome.class);
                    startActivity(employeeWelcome);
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(context, "Error checking in book to customer", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
