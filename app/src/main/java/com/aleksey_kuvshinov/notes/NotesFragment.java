package com.aleksey_kuvshinov.notes;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class NotesFragment extends Fragment {

    static final String NOTES = "notes";
    private NotesArray notes;

    public static NotesFragment newInstance(NotesArray notes) {
        NotesFragment fragment = new NotesFragment();
        Bundle args = new Bundle();
        args.putParcelable(NOTES, notes);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            notes = getArguments().getParcelable(NOTES);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notes, container, false);
        TextView headingText = view.findViewById(R.id.heading);
        TextView contentText = view.findViewById(R.id.content);
        TextView date = view.findViewById(R.id.date);
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy", Locale.getDefault());
        date.setText(String.format("%s", formatter.format(notes.getDate().getTime())));
        headingText.setText(notes.getHeading());
        contentText.setText(notes.getContent());
        return view;
    }
}