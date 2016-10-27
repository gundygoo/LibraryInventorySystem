package com.dreamteam.softwareengineering.libraryinventorysystem;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class EmployeeRecoverPassword extends AppCompatActivity {

    public EditText usernameEntry;
    public TextView recoveredPassword;
    public Button getPassword;
    public Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_recover_password);
        context = getApplicationContext();

        usernameEntry = (EditText) findViewById(R.id.resetUsername);
        recoveredPassword = (TextView) findViewById(R.id.recoveredPassword);
        getPassword = (Button) findViewById(R.id.resetPasswordButton);

        getPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    String password = DatabaseAccessor.RecoverPassword(usernameEntry.getText().toString(), context);
                    recoveredPassword.setText(password);
                } catch (IOException e){
                    e.printStackTrace();
                    Toast.makeText(context, "Error recovering password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
