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

public class EmployeeViewCustomer extends AppCompatActivity {

    public static String customerUsername;
    public EditText customerUserNameTextBox;
    public Button ViewCustomerBooks;
    public Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_view_customer);
        context = getApplicationContext();

        customerUserNameTextBox = (EditText) findViewById(R.id.newUsername);
        ViewCustomerBooks = (Button) findViewById(R.id.editUpdateButton);
        ViewCustomerBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent userBooksCheckedOut = new Intent(context, UserBooksCheckedOut.class);
                startActivity(userBooksCheckedOut);

            }
        });

    }
}
