package com.aleksey_kuvshinov.notes;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initList(view);
        RecyclerView recyclerView = view.findViewById(R.id.notes_recycler_view);
        initRecyclerView(recyclerView, notesArray);
    }

    private void initList(View view) {
        notesArray = new NotesArray[]{
                new NotesArray(getString(R.string.notes1), getString(R.string.notes1_content), Calendar.getInstance()),
                new NotesArray(getString(R.string.notes2), getString(R.string.notes2_content), Calendar.getInstance()),
                new NotesArray(getString(R.string.notes3), getString(R.string.notes3_content), Calendar.getInstance()),
                new NotesArray(getString(R.string.notes4), getString(R.string.notes4_content), Calendar.getInstance()),
                new NotesArray(getString(R.string.notes5), getString(R.string.notes5_content), Calendar.getInstance()),
                new NotesArray(getString(R.string.notes6), getString(R.string.notes6_content), Calendar.getInstance()),
                new NotesArray(getString(R.string.notes7), getString(R.string.notes7_content), Calendar.getInstance()),
                new NotesArray(getString(R.string.notes8), getString(R.string.notes8_content), Calendar.getInstance()),
                new NotesArray(getString(R.string.notes9), getString(R.string.notes9_content), Calendar.getInstance()),
                new NotesArray(getString(R.string.notes10), getString(R.string.notes10_content), Calendar.getInstance()),
        };
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void initRecyclerView(RecyclerView recyclerView, NotesArray[] notesArray){
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        NotesAdapter adapter = new NotesAdapter(notesArray);
        adapter.setOnItemClickListener((position, note) -> {
            notes = note;
            displayNote(notes);
        });
        recyclerView.setAdapter(adapter);

        DividerItemDecoration itemDecoration = new DividerItemDecoration(getContext(),  LinearLayoutManager.VERTICAL);
        itemDecoration.setDrawable(getResources().getDrawable(R.drawable.separator, null));
        recyclerView.addItemDecoration(itemDecoration);
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
        fragmentTransaction.replace(R.id.notes_layout, fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();
    }

    private void displayPortNotes(NotesArray notes) {
        NotesFragment fragment = NotesFragment.newInstance(notes);
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack("list_fragment");
        fragmentTransaction.replace(R.id.notes_container, fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notes_list, container, false);
    }
}