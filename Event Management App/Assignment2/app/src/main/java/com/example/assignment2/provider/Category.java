package com.example.assignment2.provider;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = Category.CATEGORY_TABLE_NAME)
public class Category {
    public static final String CATEGORY_TABLE_NAME = "categories";

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;

    @ColumnInfo(name = "categoryId")
    private String categoryId;

    @ColumnInfo(name = "categoryName")
    private String categoryName;

    @ColumnInfo(name = "eventCount")
    private int eventCount;

    @ColumnInfo(name = "isActiveCategory")
    private boolean isActiveCat;

    @ColumnInfo(name = "eventLocation")
    private String eventLocation;

    public Category(String categoryId, String categoryName, int eventCount, boolean isActiveCat, String eventLocation) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.eventCount = eventCount;
        this.isActiveCat = isActiveCat;
        this.eventLocation = eventLocation;
    }

    public int getId() {
        return id;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public int getEventCount() {
        return eventCount;
    }

    public boolean isActiveCat() {
        return isActiveCat;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setEventCount(int eventCount) {
        this.eventCount = eventCount;
    }

    public void setActiveCat(boolean activeCat) {
        isActiveCat = activeCat;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }
}
