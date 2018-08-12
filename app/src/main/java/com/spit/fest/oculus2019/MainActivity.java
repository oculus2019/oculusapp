package com.spit.fest.oculus2019;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EventAdapter eventAdapter;
    private Button signOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signOut=findViewById(R.id.sign_out_button);
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signOut();
            }
        });
        eventAdapter=new EventAdapter(this);
        populateEventList();
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setAdapter(eventAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));

        GoogleSignInAccount account= GoogleSignIn.getLastSignedInAccount(this);
        if (account!=null)
        {
            String personName = account.getDisplayName();
            String personGivenName = account.getGivenName();
            String personFamilyName = account.getFamilyName();
            String personEmail = account.getEmail();
            String personId = account.getId();

            Log.e("Person name: ",personName);
            Log.e("Person given name: ",personGivenName);
            Log.e("Person family name: ",personFamilyName);
            Log.e("Person email: ",personEmail);
            Log.e("Person ID: ",personId);
            Log.e("ID token: ",account.getIdToken());
        }
    }

    private void signOut() {
        com.spit.fest.oculus2019.GoogleSignInClient.getGoogleSignInClient(this).signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                });
    }

    private void populateEventList()
    {
        ArrayList<Event> list=new ArrayList<>();
        list.add(new Event("Virtual Stock Market"));
        list.add(new Event("TechRace"));
        list.add(new Event("Codatron"));
        list.add(new Event("RoboWars"));
        list.add(new Event("Hacking Bad"));
        eventAdapter.addEventList(list);
    }

    @Override
    protected void onStart() {
        if (GoogleSignIn.getLastSignedInAccount(this)==null)
        {
            Intent intent=new Intent(MainActivity.this,LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
        super.onStart();
    }
}
