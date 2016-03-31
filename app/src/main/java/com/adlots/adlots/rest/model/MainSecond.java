package com.adlots.adlots.rest.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by baekkyoungin on 16. 3. 31..
 */
public class MainSecond implements Parcelable, Adaptable {
    public String id;
    public String word_id;
    public String word;
    public String ssm;
    public String author;
    public String email;
    public String when;
    public String open;
    public String liked;

    public MainSecond(Parcel in) {
        readFromParcel(in);
    }

    public MainSecond(String author, String id) {
        this.author = author;
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    private void readFromParcel(Parcel in) {
        id = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
    }

    public static final Creator<MainSecond> CREATOR = new Creator<MainSecond>(){
        @Override
        public MainSecond createFromParcel(Parcel source) {
            return new MainSecond(source);
        }

        @Override
        public MainSecond[] newArray(int size) {
            return new MainSecond[size];
        }
    };
}
