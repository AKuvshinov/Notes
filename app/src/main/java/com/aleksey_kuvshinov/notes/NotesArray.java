package com.aleksey_kuvshinov.notes;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Calendar;

public class NotesArray implements Parcelable {
    private String heading;
    private String content;
    private Calendar date;

    public NotesArray(String heading, String content, Calendar date) {
        this.heading = heading;
        this.content = content;
        this.date = date;
    }

    protected NotesArray(Parcel in) {
        heading = in.readString();
        content = in.readString();
        date = (Calendar) in.readSerializable();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(heading);
        dest.writeString(content);
        dest.writeSerializable(date);
    }

    public static final Parcelable.Creator<NotesArray> CREATOR = new Parcelable.Creator<NotesArray>() {
        @Override
        public NotesArray createFromParcel(Parcel source) {
            return new NotesArray(source);
        }

        @Override
        public NotesArray[] newArray(int size) {
            return new NotesArray[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }
}

