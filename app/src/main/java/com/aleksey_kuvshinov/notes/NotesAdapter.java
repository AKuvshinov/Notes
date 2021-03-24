package com.aleksey_kuvshinov.notes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {
    private final NotesArray[] notesArray;
    private MyClickListener myClickListener;

    public NotesAdapter(NotesArray[] notesArray) {
        this.notesArray = notesArray;
    }

    public void setOnItemClickListener(MyClickListener itemClickListener) {
        myClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public NotesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_notes, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesAdapter.ViewHolder holder, int position) {
        holder.getTitleTextView().setText(notesArray[position].getHeading());
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy",
                Locale.getDefault());
        holder.getDateTextView().setText(formatter.format(notesArray[position].getDate().getTime()));
    }

    @Override
    public int getItemCount() {
        return notesArray.length;
    }

    public interface MyClickListener {
        void onItemClick(int position, NotesArray notesArray);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final LinearLayout itemLayout;
        private final TextView titleTextView;
        private final TextView dateTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            CardView cardView = (CardView) itemView;
            itemLayout = itemView.findViewById(R.id.recycler_view);
            titleTextView = itemView.findViewById(R.id.head_item);
            dateTextView = itemView.findViewById(R.id.data_item);
            itemLayout.setOnClickListener(v -> {
                int position = getAdapterPosition();
                myClickListener.onItemClick(position, notesArray[position]);
            });
        }

        public LinearLayout getItemLayout() {
            return itemLayout;
        }

        public TextView getTitleTextView() {
            return titleTextView;
        }

        public TextView getDateTextView() {
            return dateTextView;
        }
    }
}

