package com.adlots.adlots;

import android.content.SharedPreferences;
import android.graphics.Typeface;

/**
 * Created by baekkyoungin on 2015. 11. 18..
 */
public class DataHolder {

    public static Typeface nanum;
    public static Typeface nanumBold;

    public static SharedPreferences appSetting;
    public static SharedPreferences.Editor settingEditor;

    public static boolean isLogged = false;
    public static String name = "GUEST";

    public static String macAddress = "";

    public static class Checked {
        private int id;
        private String name;
        private boolean checked;

        public Checked(int _id, String _name) {
            id = _id;
            name = _name;
            checked = false;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public boolean getChecked() {
            return checked;
        }

        public void setChecked(boolean value) {
            checked = value;
        }

        public void toggle() {
            checked = !checked;
        }
    }
}
