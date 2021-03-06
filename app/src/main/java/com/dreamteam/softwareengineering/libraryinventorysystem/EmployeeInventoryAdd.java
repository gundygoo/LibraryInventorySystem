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

public class EmployeeInventoryAdd extends AppCompatActivity {
    public EditText bookNameTextBox;
    public Button AddBook;
    public Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_inventory_add);
        context = this;
        bookNameTextBox = (EditText) findViewById(R.id.addBookTextField);

        AddBook = (Button) findViewById(R.id.addItemButton);
        AddBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String bookNameText = bookNameTextBox.getText().toString();
                try {
                    DatabaseAccessor.AddBookToInventory(bookNameText, context);
                    Intent employeeWelcome = new Intent(context, EmployeeWelcome.class);
                    startActivity(employeeWelcome);
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(context, "Error adding book", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
