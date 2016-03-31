package com.adlots.adlots.activity.MainActivity.MainSecondFragment;

import java.util.ArrayList;

/**
 * Created by baekkyoungin on 16. 3. 31..
 */
public class MainSecondItem extends ArrayList<MainSecondItem> {
    public String main2_category;
    public String main2_brand;
    public String main2_itemname;
    public String main2_imagelink;
    public String main2_referlink;
    public int main2_endpoint;
    public int main2_nowpoint;
    public String main2_endtime;
    public int main2_lotspeople;

    public MainSecondItem(String category, String brand, String itemname ,String imagelink, String referlink , int endpoint, int nowpoint, String endtime, int lotspeople) {
        this.main2_category = category;
        this.main2_brand = brand;
        this.main2_itemname = itemname;
        this.main2_imagelink = imagelink;
        this.main2_referlink = referlink;
        this.main2_endpoint = endpoint;
        this.main2_nowpoint = nowpoint;
        this.main2_endtime = endtime;
        this.main2_lotspeople = lotspeople;
    }
}
