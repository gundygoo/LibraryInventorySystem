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

public class EmployeeInventoryDelete extends AppCompatActivity {
    public EditText bookNameTextBox;
    public Button DeleteBook;
    public Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_inventory_delete);
        context = getApplicationContext();
        bookNameTextBox = (EditText) findViewById(R.id.whateverKateeCallsItHere);
        final String bookNameText = bookNameTextBox.getText().toString();
        DeleteBook = (Button) findViewById(R.id.idHere);
        DeleteBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    DatabaseAccessor.RemoveBookFromInventory(bookNameText, context);
                    Intent employeeWelcome = new Intent(context, EmployeeWelcome.class);
                    startActivity(employeeWelcome);
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(context, "Error removing book", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
