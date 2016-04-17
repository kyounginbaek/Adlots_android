package com.adlots.adlots.rest.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by baekkyoungin on 16. 3. 31..
 */
public class MainThirdItem implements Parcelable, Adaptable {
    public String id;
    public String category, brand, itemname;
    public String imagelink, referlink, endtime, startime;
    public String endpoint, nowpoint, lotspeople;

    public MainThirdItem(Parcel in) {
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

    public static final Creator<MainThirdItem> CREATOR = new Creator<MainThirdItem>(){
        @Override
        public MainThirdItem createFromParcel(Parcel source) {
            return new MainThirdItem(source);
        }

        @Override
        public MainThirdItem[] newArray(int size) {
            return new MainThirdItem[size];
        }
    };
}
