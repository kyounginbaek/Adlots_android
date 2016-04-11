package com.adlots.adlots.rest.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by baekkyoungin on 16. 3. 31..
 */
public class MainSecondItem implements Parcelable, Adaptable {
    public String id, type;
    public String category, brand, itemname;
    public String imagelink, referlink, endtime, startime;
    public String endpoint, nowpoint, lotspeople;

    public MainSecondItem(Parcel in) {
        readFromParcel(in);
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

    public static final Creator<MainSecondItem> CREATOR = new Creator<MainSecondItem>(){
        @Override
        public MainSecondItem createFromParcel(Parcel source) {
            return new MainSecondItem(source);
        }

        @Override
        public MainSecondItem[] newArray(int size) {
            return new MainSecondItem[size];
        }
    };
}
