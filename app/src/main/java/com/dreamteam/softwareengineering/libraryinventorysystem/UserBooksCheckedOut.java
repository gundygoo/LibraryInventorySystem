package com.dreamteam.softwareengineering.libraryinventorysystem;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.io.IOException;
import java.util.List;

public class UserBooksCheckedOut extends AppCompatActivity {
    public Context context;
    public List<String> customerBookList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_books_checked_out);
        context = getApplicationContext();

        try {
            customerBookList = DatabaseAccessor.ViewCustomerBookList(EmployeeViewCustomer.customerUsername, context);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_employee_view_inventory, customerBookList);

        ListView listView = (ListView) findViewById(R.id.inventoryList);
        listView.setAdapter(adapter);
    }
}
