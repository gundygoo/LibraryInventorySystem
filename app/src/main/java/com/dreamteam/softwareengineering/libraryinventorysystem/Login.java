package com.dreamteam.softwareengineering.libraryinventorysystem;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class Login extends AppCompatActivity{

    public EditText usernameTextBox;
    public EditText passwordTextBox;
    public Button submitButton;
    public Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = getApplicationContext();

        usernameTextBox = (EditText) findViewById(R.id.whateverKateeCallsItHere);
        final String usernameText = usernameTextBox.getText().toString();

        passwordTextBox = (EditText) findViewById(R.id.whatever);
        final String passwordText = passwordTextBox.getText().toString();

        submitButton = (Button) findViewById(R.id.idHere);
        submitButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
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
    }
}

