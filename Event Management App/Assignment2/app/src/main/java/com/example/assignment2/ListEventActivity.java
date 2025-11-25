package com.example.assignment2;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class ListEventActivity extends AppCompatActivity {

    FragmentListEvent fragmentListEvent = new FragmentListEvent();

    FragmentManager fragmentManager = getSupportFragmentManager();

    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.event_app_bar_layout);

        Toolbar toolBarEvent = findViewById(R.id.toolbar_event);
        toolBarEvent.setTitle(R.string.event_toolbar);
        setSupportActionBar(toolBarEvent);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        fragmentTransaction.add(R.id.fragmentContainerViewEve, fragmentListEvent);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}