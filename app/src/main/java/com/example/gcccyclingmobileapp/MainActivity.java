package com.example.gcccyclingmobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        TextView username = (TextView) findViewById(R.id.username);
        TextView password = (TextView) findViewById(R.id.password);


        MaterialButton loginbtn = (MaterialButton) findViewById(R.id.loginbtn);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckBox adminCheck = (CheckBox) findViewById(R.id.check_admin);
                CheckBox clubCheck = (CheckBox) findViewById(R.id.check_club);
                CheckBox participantCheck = (CheckBox) findViewById(R.id.check_participant);

                boolean isAdminChecked = adminCheck.isChecked();
                boolean isClubChecked = clubCheck.isChecked();
                boolean isParticipantChecked = participantCheck.isChecked();

                String name = username.getText().toString();
                String accountType = null;

                Intent intent = new Intent(MainActivity.this, WelcomePage.class);

                intent.putExtra("name", name);

                if (!username.getText().toString().isEmpty() && !password.getText().toString().isEmpty()) {
                    if (isAdminChecked) {
                        if (username.getText().toString().equals("admin") && password.getText().toString().equals("admin")) {
                            fAuth.createUserWithEmailAndPassword(username.getText().toString(), password.getText().toString());
                            accountType = "ADMIN";
                            intent.putExtra("accountType", accountType);
                            startActivity(intent);
                        }
                    } else if (isClubChecked) {
                        fAuth.createUserWithEmailAndPassword(username.getText().toString(), password.getText().toString());
                        accountType = "CLUB";
                        intent.putExtra("accountType", accountType);
                        startActivity(intent);
                    } else if (isParticipantChecked) {
                        fAuth.createUserWithEmailAndPassword(username.getText().toString(), password.getText().toString());
                        accountType = "PARTICIPANT";
                        intent.putExtra("accountType", accountType);
                        startActivity(intent);
                    }
                }
            }
        });

    }

    public void onAdminCheckBoxClicked(View view){
        CheckBox clubCheck = (CheckBox) findViewById(R.id.check_club);
        CheckBox participantCheck = (CheckBox) findViewById(R.id.check_participant);

        clubCheck.setChecked(false);
        participantCheck.setChecked(false);

    }
    public void onClubCheckBoxClicked(View view){
        CheckBox adminCheck = (CheckBox) findViewById(R.id.check_admin);
        CheckBox participantCheck = (CheckBox) findViewById(R.id.check_participant);

        adminCheck.setChecked(false);
        participantCheck.setChecked(false);

    }
    public void onParticipantCheckBoxClicked(View view){
        CheckBox adminCheck = (CheckBox) findViewById(R.id.check_admin);
        CheckBox clubCheck = (CheckBox) findViewById(R.id.check_club);

        adminCheck.setChecked(false);
        clubCheck.setChecked(false);

    }

}