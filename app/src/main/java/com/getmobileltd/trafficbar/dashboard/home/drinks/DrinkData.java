/*
 * *
 * Creator: Tobiloba Adejumo on 4/30/19 9:53 AM Last modified: 4/30/19 9:53 AM Copyright: All rights reserved Ⓒ 2019
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

package com.getmobileltd.trafficbar.dashboard.home.drinks;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by themavencoder on 30,April,2019
 */
public class DrinkData implements Parcelable {
    @SerializedName("id")
    private int id;

    @SerializedName("description")
    private String description;

    @SerializedName("name")
    private String name;

    @SerializedName("price")
    private String price;

    @SerializedName("bg_image")
    private String big_image;

    @SerializedName("image")
    private String image;

    @SerializedName("href")
    private String href;

    @SerializedName("menu_category_name")
    private String menu_category_name;

    public String getDescription() {
        return description;
    }

    protected DrinkData(Parcel in) {
        id = in.readInt();
        description = in.readString();
        name = in.readString();
        price = in.readString();
        big_image = in.readString();
        image = in.readString();
        href = in.readString();
        menu_category_name = in.readString();
    }

    public static final Creator<DrinkData> CREATOR = new Creator<DrinkData>() {
        @Override
        public DrinkData createFromParcel(Parcel in) {
            return new DrinkData(in);
        }

        @Override
        public DrinkData[] newArray(int size) {
            return new DrinkData[size];
        }
    };

    public String getMenu_category_name() {
        return menu_category_name;
    }

    public int getId() {
        return id;
    }

    public String getBig_image() {
        return big_image;
    }

    public String getImage() {
        return image;
    }

    public String getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public String getHref() {
        return href;
    }

    public DrinkData(int id, String name, String price, String image, String href, String menu_category_name, String big_image, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
        this.href = href;
        this.menu_category_name = menu_category_name;
        this.big_image = big_image;
        this.description = description;
    }

    public DrinkData() {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(description);
        dest.writeString(name);
        dest.writeString(price);
        dest.writeString(big_image);
        dest.writeString(image);
        dest.writeString(href);
        dest.writeString(menu_category_name);
    }
}
