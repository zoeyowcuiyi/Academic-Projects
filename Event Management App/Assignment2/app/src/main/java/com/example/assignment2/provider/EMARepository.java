package com.example.assignment2.provider;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class EMARepository {

    private EMADao emaDao;

    private LiveData<List<Category>> allCategories;

    private LiveData<List<Event>> allEvents;

    EMARepository (Application application) {
        EMADatabase db = EMADatabase.getDatabase(application);
        emaDao = db.emaDao();
        allCategories = emaDao.getAllCategories();
        allEvents = emaDao.getAllEvents();
    }

    LiveData<List<Category>> getAllCategories() {
        return allCategories;
    }

    Category getCategory(String catId) {
        return emaDao.getCategory(catId);
    }

    void updateCategory(Category category) {
        EMADatabase.databaseWriteExecutor.execute(() -> emaDao.updateCategory(category));
    }

    void insertCategory(Category category) {
        EMADatabase.databaseWriteExecutor.execute(() -> emaDao.addCategory(category));
    }

    void deleteCategory(String catId) {
        EMADatabase.databaseWriteExecutor.execute(() -> emaDao.deleteCategory(catId));
    }

    void deleteAllCategories() {
        EMADatabase.databaseWriteExecutor.execute(() -> emaDao.deleteAllCategories());
    }

    LiveData<List<Event>> getAllEvents() {
        return allEvents;
    }

    Event getEvent(String eveId) {
        return emaDao.getEvent(eveId);
    }

    void insertEvent(Event event) {
        EMADatabase.databaseWriteExecutor.execute(() -> emaDao.addEvent(event));
    }

    void deleteEvent(String eveId) {
        EMADatabase.databaseWriteExecutor.execute(() -> emaDao.deleteEvent(eveId));
    }

    void deleteAllEvents() {
        EMADatabase.databaseWriteExecutor.execute(() -> emaDao.deleteAllEvents());
    }
}
