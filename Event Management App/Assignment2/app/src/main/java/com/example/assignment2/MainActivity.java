package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etUsername, etPassword, etConfPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUsername = findViewById(R.id.editTextUsername);
        etPassword = findViewById(R.id.editTextPassword);
        etConfPassword = findViewById(R.id.editTextConfPassword);
    }

    public void onRegisterButtonClick(View view) {
        // get user input from the UI elements using getText() and toString() methods
        String usernameStr = etUsername.getText().toString();
        String passwordStr = etPassword.getText().toString();
        String confPasswordStr = etConfPassword.getText().toString();

        /* set conditions that needs to be fulfilled
         * cond1: all UI elements must be filled
         * cond2: password and confirm password must be exactly the same
         */
        boolean cond1 = !(usernameStr.isEmpty() || passwordStr.isEmpty() || confPasswordStr.isEmpty());
        boolean cond2 = (passwordStr.equals(confPasswordStr));

        // declare a string for toast
        String message;

        /* once both conditions are true, save their username and password to shared preferences
         * notify user they have registered successfully through toast message
         * intent to the login activity
         * if any of the conditions fail to be true, toast "Invalid username or passwords" to user
         */
        if (cond1 && cond2) {
            saveDataToSharedPreference(usernameStr, confPasswordStr);

            message = "Registered successfully";

            intentToLoginActivity();
        }
        else {
            message = "Invalid username or passwords";
        }

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void onLoginButtonClick(View view) {
        // when user clicks on the login button, intent them to the login activity
        intentToLoginActivity();
    }

    private void saveDataToSharedPreference (String newUsername, String newPassword) {
        // initialise the SharedPreferences class to File to store usernames and passwords
        SharedPreferences sharedPreferences = getSharedPreferences(KeyStore.USERNAME_FILE, MODE_PRIVATE);

        // use new SharedPreference reference to access the editor
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // save last username used in key value format
        // save new Username (key) and new Password (value) in key value format
        editor.putString(KeyStore.KEY_USERNAME, newUsername);
        editor.putString(newUsername, newPassword);

        // apply the changes to the file
        editor.apply();
    }

    private void intentToLoginActivity() {
        // initialise new Intent class to intent to Login Activity
        Intent intent = new Intent(this, LoginActivity.class);
        // launch the activity using startActivity method
        startActivity(intent);
    }
}