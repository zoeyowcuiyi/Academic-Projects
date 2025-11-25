package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText etUsernameLogin, etPasswordLogin;

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsernameLogin = findViewById(R.id.editTextUsernameLogin);
        etPasswordLogin = findViewById(R.id.editTextPasswordLogin);

        sharedPreferences = getSharedPreferences(KeyStore.USERNAME_FILE, MODE_PRIVATE);

        String usernameRestored = restoreDataFromSharedPreference(KeyStore.KEY_USERNAME);
        etUsernameLogin.setText(usernameRestored);
    }

    public void onSecondRegisterButtonClick(View view) {
        // intent back to the register activity by initialising a new intent
        Intent intent = new Intent(this, MainActivity.class);

        // launch the register activity
        startActivity(intent);
    }

    public void onSecondLoginButtonClick(View view) {
        // get the user input from the UI elements
        String usernameStr = etUsernameLogin.getText().toString();
        String passwordStr = etPasswordLogin.getText().toString();

        String message;

        boolean usernameExists = sharedPreferences.contains(usernameStr);

        if (!(usernameStr.isEmpty() || passwordStr.isEmpty())) {
            if (usernameExists) {
                String passwordRestored = restoreDataFromSharedPreference(usernameStr);
                if (passwordStr.equals(passwordRestored)) {
                    message = "Login successfully";

                    Intent intent = new Intent(this, DashboardActivity.class);
                    startActivity(intent);
                } else {
                    message = "Incorrect password";}
            } else {
                message = "Username does not exists";}
        } else {
            message = "Incorrect username or password";}

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private String restoreDataFromSharedPreference(String key) {
        String defValue = "";
        String valueRestored = sharedPreferences.getString(key,defValue);

        return valueRestored;
    }
}