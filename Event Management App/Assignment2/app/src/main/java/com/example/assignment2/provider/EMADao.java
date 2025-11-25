package com.example.assignment2.provider;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface EMADao {

    @Query("select * from categories")
    LiveData<List<Category>> getAllCategories();

    @Query("select * from categories where categoryId=:catId")
    Category getCategory(String catId);

    @Update
    void updateCategory(Category category);

    @Insert
    void addCategory(Category category);

    @Query("delete from categories where categoryId=:catId")
    void deleteCategory(String catId);

    @Query("delete from categories")
    void deleteAllCategories();

    @Query("select * from events")
    LiveData<List<Event>> getAllEvents();

    @Query("select * from events where eventId=:eventId")
    Event getEvent(String eventId);

    @Insert
    void addEvent(Event event);

    @Query("delete from events where eventId=:eveId")
    void deleteEvent(String eveId);

    @Query("delete from events")
    void deleteAllEvents();
}
