package com.example.assignment2.provider;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class EMAViewModel extends AndroidViewModel {

    private EMARepository emaRepository;

    private LiveData<List<Category>> allCategories;

    private LiveData<List<Event>> allEvents;
    public EMAViewModel(@NonNull Application application) {
        super(application);
        emaRepository = new EMARepository(application);
        allCategories = emaRepository.getAllCategories();
        allEvents = emaRepository.getAllEvents();
    }

    public LiveData<List<Category>> getAllCategories() {
        return allCategories;
    }

    public Category getCategory(String catId) {
        return emaRepository.getCategory(catId);
    }

    public void updateCategory(Category category) {
        emaRepository.updateCategory(category);
    }

    public void insertCategory(Category category) {
        emaRepository.insertCategory(category);
    }

    public void deleteCategory(String catId) {
        emaRepository.deleteCategory(catId);
    }

    public void deleteAllCategories() {
        emaRepository.deleteAllCategories();
    }

    public LiveData<List<Event>> getAllEvents() {
        return allEvents;
    }

    public Event getEvent(String eveId) {
        return emaRepository.getEvent(eveId);
    }

    public void insertEvent(Event event) {
        emaRepository.insertEvent(event);
    }

    public void deleteEvent(String eveId) {
        emaRepository.deleteEvent(eveId);
    }

    public void deleteAllEvents() {
        emaRepository.deleteAllEvents();
    }
}
