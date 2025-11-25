package com.example.assignment2.provider;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = Event.EVENT_TABLE_NAME)
public class Event {
    public static final String EVENT_TABLE_NAME = "events";

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;

    @ColumnInfo(name = "eventId")
    private String eventId;

    @ColumnInfo(name = "eventName")
    private String eventName;

    @ColumnInfo(name = "categoryId")
    private String eveCategoryId;

    @ColumnInfo(name = "ticketsAvailability")
    private int ticketsAva;

    @ColumnInfo(name = "isActiveEvent")
    private boolean isActiveEve;

    public Event(String eventId, String eventName, String eveCategoryId, int ticketsAva, boolean isActiveEve) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.eveCategoryId = eveCategoryId;
        this.ticketsAva = ticketsAva;
        this.isActiveEve = isActiveEve;
    }

    public int getId() {
        return id;
    }

    public String getEventId() {
        return eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public String getEveCategoryId() {
        return eveCategoryId;
    }

    public int getTicketsAva() {
        return ticketsAva;
    }

    public boolean isActiveEve() {
        return isActiveEve;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setEveCategoryId(String eveCategoryId) {
        this.eveCategoryId = eveCategoryId;
    }

    public void setTicketsAva(int ticketsAva) {
        this.ticketsAva = ticketsAva;
    }

    public void setActiveEve(boolean activeEve) {
        isActiveEve = activeEve;
    }
}
