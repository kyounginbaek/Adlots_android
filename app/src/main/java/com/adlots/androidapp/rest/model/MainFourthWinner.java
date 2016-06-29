package com.adlots.androidapp.rest.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by baekkyoungin on 16. 3. 31..
 */
public class MainFourthWinner implements Parcelable, Adaptable {
    public String id, itemid, type, nickname;
    public String category, brand, itemname;
    public String imagelink, referlink, endtime, startime;
    public String endpoint, nowpoint, lotspeople;
    public String howtobuy, userlotspoint, when, pointdone, whendone, winorlose, address, finish;

    public MainFourthWinner(Parcel in) {
        readFromParcel(in);
    }

    public MainFourthWinner(String isnull) {
        this.id = isnull;
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

    public static final Creator<MainFourthWinner> CREATOR = new Creator<MainFourthWinner>(){
        @Override
        public MainFourthWinner createFromParcel(Parcel source) {
            return new MainFourthWinner(source);
        }

        @Override
        public MainFourthWinner[] newArray(int size) {
            return new MainFourthWinner[size];
        }
    };
}
