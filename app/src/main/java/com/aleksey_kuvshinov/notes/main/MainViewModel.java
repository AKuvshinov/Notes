package com.aleksey_kuvshinov.notes.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.aleksey_kuvshinov.notes.App;
import com.aleksey_kuvshinov.notes.model.Note;

import java.util.List;

public class MainViewModel extends ViewModel {
    private LiveData<List<Note>> noteLiveData = App.getInstance().getNoteDao().getAllLiveData();

    public LiveData<List<Note>> getNoteLiveData() {
        return noteLiveData;
    }
}
