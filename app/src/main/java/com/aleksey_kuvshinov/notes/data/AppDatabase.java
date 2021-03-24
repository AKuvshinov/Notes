package com.aleksey_kuvshinov.notes.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.aleksey_kuvshinov.notes.model.Note;

@Database(entities = {Note.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract NoteDao noteDao();
}
