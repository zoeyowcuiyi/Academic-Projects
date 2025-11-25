package com.example.assignment2;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class ListCategoryActivity extends AppCompatActivity {

    FragmentManager fragmentManager = getSupportFragmentManager();

    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

    FragmentListCategory fragmentListCategory = new FragmentListCategory();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.category_app_bar_layout);

        Toolbar toolBarCategory = findViewById(R.id.toolbar_category);
        toolBarCategory.setTitle(R.string.category_toolbar);
        setSupportActionBar(toolBarCategory);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        fragmentTransaction.add(R.id.fragmentContainerCat2, fragmentListCategory);
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