package com.example.lenovo.lab1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView emailText;
    private TextView passText;
    private Button logoutButton;

    public static final String MY_PREFS_NAME = "credencialesAcceso";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailText= findViewById(R.id.emailMainId);
        passText= findViewById(R.id.passMainId);
        logoutButton = findViewById(R.id.logoutMainId);

        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String emailString = prefs.getString("Email", null);

        if (emailString != null) {
            emailString = prefs.getString("Email", "");
            String passwordString = prefs.getString("Password", "");
            emailText.setText("Email: "+emailString);
            passText.setText("Password: "+passwordString);
        }
        else
        {
            Intent intent = new Intent(this, Main2Activity.class);
            startActivityForResult(intent,1);
        }
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Logout();
            }
        });
    }

    public void Logout(){
        emailText.setText("");
        passText.setText("");
        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        editor.clear();
        editor.apply();
        Intent intent = new Intent(this, Main2Activity.class);
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int request, int result, Intent data)
    {
        super.onActivityResult(request, result, data);
        if(request==1)
        {
            String emailIntentContent = data.getStringExtra("EmailIntent");
            String passwordIntentContent = data.getStringExtra("PasswordIntent");

            SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
            editor.putString("Email", emailIntentContent);
            editor.putString("Password", passwordIntentContent);
            editor.apply();
            emailText.setText("Email:" + emailIntentContent);
            passText.setText("Password: " + passwordIntentContent);
        }

    }

}
