package com.example.assignment2;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.assignment2.provider.Category;
import com.example.assignment2.provider.EMAViewModel;

public class EventCategoryActivity extends AppCompatActivity {

    EditText etCategoryId, etCategoryName, etEventCount, etEventLocation;

    Switch isActiveCat;

    AutoGenerateId generateId = new AutoGenerateId();

    AlphanumericChecker alphanumericChecker = new AlphanumericChecker();

    private EMAViewModel emaViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_new_category);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etCategoryId = findViewById(R.id.editTextCategoryId2);
        etCategoryName = findViewById(R.id.editTextCategoryName);
        etEventCount = findViewById(R.id.editTextEventCount);
        isActiveCat = findViewById(R.id.switchIsActiveCat);
        etEventLocation = findViewById(R.id.editTextEventLocation);

        emaViewModel = new ViewModelProvider(this).get(EMAViewModel.class);
    }

    public void onSaveCategoryButtonClick(View view) {
        String categoryId = etCategoryId.getText().toString();
        String categoryName = etCategoryName.getText().toString();
        String eventCountStr = etEventCount.getText().toString();
        boolean isActive = isActiveCat.isChecked();
        String eventLocation = etEventLocation.getText().toString();

        int eventCountInt;

        if (eventCountStr.isEmpty()) {
            eventCountInt = 0;
        } else {
            eventCountInt = Integer.parseInt(eventCountStr);
        }

        String message;

        if (categoryId.isEmpty()) {
            if (!categoryName.isEmpty()) {
                if (alphanumericChecker.isAlphanumeric(categoryName)) {
                    categoryId = generateId.myAutoGenerateId(AutoGenerateId.CATEGORY_CHAR, 2, 4);

                    etCategoryId.setText(categoryId);

                    saveCategoryToSharedPreferences(categoryId, categoryName, eventCountInt, isActive, eventLocation);

                    message = String.format("Category saved successfully: %s", categoryId);

                    finish();
                } else {
                    message = "Invalid Category name";
                }
            } else {
                message = "Category name field must be filled";
            }
        } else {
            message = "Category Id field must not be filled";
        }

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void saveCategoryToSharedPreferences (String newCategoryId, String newCategoryName, int newEventCount, boolean newIsActive, String newEveLocation) {

        Category newCategory = new Category(newCategoryId, newCategoryName, newEventCount, newIsActive, newEveLocation);

        emaViewModel.insertCategory(newCategory);
    }

}