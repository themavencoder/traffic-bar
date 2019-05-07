/*
 * *
 * Creator: Tobiloba Adejumo on 4/26/19 1:21 PM Last modified: 4/26/19 1:21 PM Copyright: All rights reserved Ⓒ 2019
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 *    Unless required by applicable law or agreed to in writing, software distributed under
 *    the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS
 *    OF ANY KIND, either express or implied. See the License for the specific language governing
 *    permissions and limitations under the License.
 * /
 */

package com.getmobileltd.trafficbar.orderfood.menudetails.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by themavencoder on 26,April,2019
 */
public class Menus implements  Parcelable{

    @SerializedName("id")
    private int id;
    @SerializedName("category_id")
    private int category_id;
    @SerializedName("menu_name")
    private String menu_name;
    @SerializedName("menu_description")
    private String menu_description;
    @SerializedName("menu_extra")
    private String menu_extra;
    @SerializedName("menu_price")
    private String menu_price;
    @SerializedName("menu_image_sm")
    private String menu_image_sm;
    @SerializedName("menu_image_bg")
    private String menu_image_bg;

    public Menus(String menu_name, String menu_price, String menu_image_sm, String menu_image_bg,String menu_description, int id,String menu_extra) {
        this.menu_name = menu_name;
        this.menu_price = menu_price;
        this.menu_image_sm = menu_image_sm;
        this.menu_description = menu_description;
        this.menu_image_bg = menu_image_bg;
        this.id = id;
        this.menu_extra = menu_extra;


    }


    protected Menus(Parcel in) {
        id = in.readInt();
        category_id = in.readInt();
        menu_name = in.readString();
        menu_description = in.readString();
        menu_price = in.readString();
        menu_image_sm = in.readString();
        menu_image_bg = in.readString();
    }

    public static final Creator<Menus> CREATOR = new Creator<Menus>() {
        @Override
        public Menus createFromParcel(Parcel in) {
            return new Menus(in);
        }

        @Override
        public Menus[] newArray(int size) {
            return new Menus[size];
        }
    };

    public int getId() {
        return id;
    }

    public String getMenu_extra() {
        return menu_extra;
    }

    public int getCategory_id() {
        return category_id;
    }

    public String getMenu_description() {
        return menu_description;
    }

    public String getMenu_image_bg() {
        return menu_image_bg;
    }

    public String getMenu_image_sm() {
        return menu_image_sm;
    }

    public String getMenu_name() {
        return menu_name;
    }

    public String getMenu_price() {
        return menu_price;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(category_id);
        dest.writeString(menu_name);
        dest.writeString(menu_description);
        dest.writeString(menu_price);
        dest.writeString(menu_image_sm);
        dest.writeString(menu_image_bg);
    }
}
