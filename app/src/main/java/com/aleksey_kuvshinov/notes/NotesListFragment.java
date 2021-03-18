package com.aleksey_kuvshinov.notes;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Calendar;

public class NotesListFragment extends Fragment {

    private boolean orientLandscape;
    private NotesArray[] notesArray;
    private NotesArray notes;

    public NotesListFragment() {

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initList(view);
    }

    private void initList(View view) {
        notesArray = new NotesArray[]{
                new NotesArray(getString(R.string.notes1), getString(R.string.notes1_content), Calendar.getInstance()),
                new NotesArray(getString(R.string.notes2), getString(R.string.notes2_content), Calendar.getInstance()),
                new NotesArray(getString(R.string.notes3), getString(R.string.notes3_content), Calendar.getInstance()),
        };

        for (NotesArray notesArray : notesArray) {
            Context context = getContext();
            if (context != null) {
                LinearLayout linearView = (LinearLayout) view;
                TextView firstTextView = new TextView(context);
                firstTextView.setText(notes.getHeading());
                linearView.addView(firstTextView);
                firstTextView.setPadding(10, 30, 0, 0);
                firstTextView.setTextSize(36);
                firstTextView.setOnClickListener(v -> {
                    notes = notesArray;
                    displayNote(notes);
                });
            }
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelable(NotesFragment.NOTES, notes);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        orientLandscape = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
        if (savedInstanceState != null) {
            notes = savedInstanceState.getParcelable(NotesFragment.NOTES);
        } else {
            notes = notesArray[0];
        }
        if (orientLandscape) {
            displayLandNotes(notes);
        }
    }

    private void displayNote(NotesArray notes) {
        if (orientLandscape) {
            displayLandNotes(notes);
        } else {
            displayPortNotes(notes);
        }
    }

    private void displayLandNotes(NotesArray notes) {
        NotesFragment fragment = NotesFragment.newInstance(notes);
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.note_layout, fragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
    }

    private void displayPortNotes(NotesArray notes) {
        Intent intent = new Intent(getActivity(), NotesActivity.class);
        intent.putExtra(NotesFragment.NOTES, notes);
        startActivity(intent);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notes_list, container, false);
    }
}