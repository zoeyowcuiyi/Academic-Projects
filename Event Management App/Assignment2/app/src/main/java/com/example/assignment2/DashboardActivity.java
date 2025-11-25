package com.example.assignment2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GestureDetectorCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.assignment2.provider.Category;
import com.example.assignment2.provider.EMAViewModel;
import com.example.assignment2.provider.Event;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class DashboardActivity extends AppCompatActivity {

    private EditText etEventId, etEventName, etEveCategoryId, etTicketsAva;

    private Switch isActiveEve;

    private DrawerLayout myDrawerLayout;

    private AutoGenerateId generateId = new AutoGenerateId();

    private AlphanumericChecker alphanumericChecker = new AlphanumericChecker();

    private EMAViewModel emaViewModel;

    private FragmentManager fragmentManager = getSupportFragmentManager();

    private FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

    private FragmentListCategory fragmentListCategory = new FragmentListCategory();

    private ArrayList<Category> allCategories;

    private ArrayList<Event> allEvents;

    private TextView tvGesture;

    private GestureDetectorCompat mDetector;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_activity_dashboard);

        Toolbar myToolBar = findViewById(R.id.my_toolbar);
        myDrawerLayout = findViewById(R.id.drawer_layout);
        NavigationView myNavigationView = findViewById(R.id.nav_view);

        setSupportActionBar(myToolBar);

        ActionBarDrawerToggle toggle =
                new ActionBarDrawerToggle(this, myDrawerLayout, myToolBar,
                        R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        myDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        myNavigationView.setNavigationItemSelectedListener(new MyNavigationListener());

        etEventId = findViewById(R.id.editTextEventId);
        etEventName = findViewById(R.id.editTextEventName);
        etEveCategoryId = findViewById(R.id.editTextCategoryId);
        etTicketsAva = findViewById(R.id.editTextTicketsAva);
        isActiveEve = findViewById(R.id.switchIsActiveEvent);

        fragmentTransaction.add(R.id.fragmentContainerCat, fragmentListCategory);

        fragmentTransaction.commit();

        emaViewModel = new ViewModelProvider(this).get(EMAViewModel.class);

        emaViewModel.getAllCategories().observe(this, categories -> {
            allCategories = (ArrayList<Category>) categories;
        });

        emaViewModel.getAllEvents().observe(this, events -> {
            allEvents = (ArrayList<Event>) events;
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveEvent(v);
            }
        });

        View touchpad = findViewById(R.id.touchpad);

        tvGesture = findViewById(R.id.tvGesture);

        CustomGestureListener listener = new CustomGestureListener();

        mDetector = new GestureDetectorCompat(this, listener);

        touchpad.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mDetector.onTouchEvent(event);
                return true;
            }
        });
    }

    private class CustomGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public void onLongPress(@NonNull MotionEvent e) {
            tvGesture.setText("OnLongPress");
            clearEventForm();
            makeToast("Event Form cleared");
            super.onLongPress(e);
        }

        @Override
        public boolean onDoubleTap(@NonNull MotionEvent e) {
            tvGesture.setText("OnDoubleTap");
            saveEvent(getCurrentFocus());
            return super.onDoubleTap(e);
        }
    }

    public class MyUndoListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            String eventId = etEventId.getText().toString();

            String catId = etEveCategoryId.getText().toString();

            emaViewModel.deleteEvent(eventId);

            editEventCount(catId, -1);

            clearEventForm();

            makeToast("Event has been removed");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.option_clear_event_form) {
            clearEventForm();
            makeToast("Event Form cleared");
        } else if (item.getItemId() == R.id.option_delete_all_cat) {
            emaViewModel.deleteAllCategories();
            makeToast("All categories have been removed");
        } else if (item.getItemId() == R.id.option_delete_all_eve) {
            for (int i=0; i < allEvents.size(); i++) {
                Event event = allEvents.get(i);
                editEventCount(event.getEveCategoryId(), -1);
            }
            emaViewModel.deleteAllEvents();
            makeToast("All events have been removed");
        }

        return true;
    }

    class MyNavigationListener implements NavigationView.OnNavigationItemSelectedListener {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            // get the id of the selected item
            int id = item.getItemId();

            if (id == R.id.nav_view_all_cat) {
                intentToActivity(ListCategoryActivity.class);
            } else if (id == R.id.nav_add_category) {
                intentToActivity(EventCategoryActivity.class);
            } else if (id == R.id.nav_view_all_eve) {
                intentToActivity(ListEventActivity.class);
            } else if (id == R.id.nav_logout) {
                intentToActivity(LoginActivity.class);
            }

            myDrawerLayout.closeDrawers();
            return true;
        }
    }

    private void saveEvent(View v) {
        String eventId = etEventId.getText().toString();
        String eventName = etEventName.getText().toString();
        String categoryId = etEveCategoryId.getText().toString();
        String ticketsAvaStr = etTicketsAva.getText().toString();
        boolean isActive = isActiveEve.isChecked();

        int ticketsAvaInt;

        if (ticketsAvaStr.isEmpty()) {
            ticketsAvaInt = 0;
        } else {
            ticketsAvaInt = Integer.parseInt(ticketsAvaStr);
        }

        boolean isNotEmpty = !eventName.isEmpty() && !categoryId.isEmpty();
        boolean isAlphanumeric = alphanumericChecker.isAlphanumeric(eventName);

        boolean categoryExists = categoryExist(categoryId);

        if (eventId.isEmpty()) {
            if (isNotEmpty) {
                if (isAlphanumeric) {
                    if (categoryExists) {
                        eventId = generateId.myAutoGenerateId(AutoGenerateId.EVENT_CHAR, 2, 5);

                        etEventId.setText(eventId);

                        saveEventToRoomDatabase(eventId, categoryId, eventName, ticketsAvaInt, isActive);

                        editEventCount(categoryId, 1);

                        String message = String.format("Event saved: %s to %s", eventId, categoryId);

                        Snackbar.make(v, message, Snackbar.LENGTH_LONG).setAction("UNDO", new MyUndoListener()).show();
                    } else {
                        makeToast("Invalid Category Id");}
                } else {
                    makeToast("Invalid Event name");}
            } else {
                makeToast("Event Name and Category Id fields must be filled");}
        } else {
            makeToast("Event Id field must not be filled");}
    }

    private void saveEventToRoomDatabase (String newEventId, String newCategoryId, String newEventName, int newTicketsAva, boolean newIsActive) {
        Event newEvent = new Event(newEventId, newEventName, newCategoryId, newTicketsAva, newIsActive);
        emaViewModel.insertEvent(newEvent);
    }

    private void clearEventForm() {
        etEventId.setText("");
        etEventName.setText("");
        etEveCategoryId.setText("");
        etTicketsAva.setText("");
        isActiveEve.setChecked(false);
    }

    private void intentToActivity(Class<?> newActivity) {
        Intent intent = new Intent(this, newActivity);
        if (newActivity == LoginActivity.class) {
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        }
        startActivity(intent);
    }

    private void makeToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private boolean categoryExist(String newCategoryId) {
        boolean categoryFound = false;
        for (int i=0; i< allCategories.size(); i++) {
            Category category = allCategories.get(i);
            if (category.getCategoryId().equals(newCategoryId)) {
                categoryFound = true;
                break;
            }
        }
        return categoryFound;
    }

    private void editEventCount(String newCategoryId, int inOrDecrement) {
        for (int i=0; i < allCategories.size(); i++) {
            Category category = allCategories.get(i);
            if (category.getCategoryId().equals(newCategoryId)) {
                int eventCount = category.getEventCount();
                category.setEventCount(eventCount + inOrDecrement);
                emaViewModel.updateCategory(category);
            }
        }
    }
}