package com.dreamteam.softwareengineering.libraryinventorysystem;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EmployeeViewCustomer extends AppCompatActivity {

    public static String customerUsername;
    public EditText customerUserNameTextBox;
    public Button ViewCustomerBooks;
    public Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_view_customer);
        context = this;

        customerUserNameTextBox = (EditText) findViewById(R.id.viewCustomerUsername);
        ViewCustomerBooks = (Button) findViewById(R.id.ViewBooks);
        ViewCustomerBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customerUsername = customerUserNameTextBox.getText().toString();
                Intent userBooksCheckedOut = new Intent(context, UserBooksCheckedOut.class);
                startActivity(userBooksCheckedOut);
            }
        });
    }
}
