package com.example.gcccyclingmobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class WelcomePage extends AppCompatActivity {
    TextView updateAccountType;
    TextView updateName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);

        Bundle bundle = getIntent().getExtras();
        String name = bundle.getString("name");
        String accountType = bundle.getString("accountType");

        updateName = (TextView) findViewById(R.id.name);
        updateName.setText(name + "!");

        updateAccountType = (TextView) findViewById(R.id.accountType);
        updateAccountType.setText(accountType);
    }
}