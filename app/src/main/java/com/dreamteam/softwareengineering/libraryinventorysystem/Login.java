package com.dreamteam.softwareengineering.libraryinventorysystem;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

/**
 * A login screen that offers login via email/password.
 */
public class Login extends AppCompatActivity{

    public EditText usernameTextBox;
    public EditText passwordTextBox;
    public Button submitButton;
    public Button forgotPassword;
    public Context context;
    public Button prototypeDataButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = this;

        usernameTextBox = (EditText) findViewById(R.id.loginUsername);

        passwordTextBox = (EditText) findViewById(R.id.loginPassword);

        submitButton = (Button) findViewById(R.id.loginButton);
        submitButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                final String usernameText = usernameTextBox.getText().toString();
                final String passwordText = passwordTextBox.getText().toString();
                try {
                    String response = DatabaseAccessor.AuthenticateUserLogin(usernameText, passwordText, context);
                    if (response == null){
                        Toast.makeText(context, "User Credentials Not Found", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Intent employeeWelcome = new Intent(context, EmployeeWelcome.class);
                        startActivity(employeeWelcome);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        forgotPassword = (Button) findViewById(R.id.forgotPassword);
        forgotPassword.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent employeeRecoverPassword = new Intent(context, EmployeeRecoverPassword.class);
                startActivity(employeeRecoverPassword);
            }
        });

        prototypeDataButton = (Button) findViewById(R.id.protoDataButton);
        prototypeDataButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    DatabaseAccessor.AddCustomerData("Erik Gunderson", "007", "gundy", "12345", "21", "123 West Ave", "someone@gmail.com", context);
                    DatabaseAccessor.AddBookToInventory("The Illiad", context);
                    DatabaseAccessor.AddBookToInventory("The Odyssey", context);
                    DatabaseAccessor.CreateNewCustomerAccount("gundy", context);
                    DatabaseAccessor.AddBookToInventory("Harry Potter and The Sorcerer's Stone", context);
                    Toast.makeText(context, "adding prototype data successful", Toast.LENGTH_SHORT).show();
                } catch(IOException e){
                    Toast.makeText(context, "error adding test data", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

