/*
 * *
 * Creator: Tobiloba Adejumo on 4/30/19 9:12 AM Last modified: 4/30/19 9:12 AM Copyright: All rights reserved Ⓒ 2019
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

package com.getmobileltd.trafficbar.dashboard.home.food;

import com.google.gson.annotations.SerializedName;

/**
 * Created by themavencoder on 30,April,2019
 */
public class FoodData {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("price")
    private String price;

    @SerializedName("image")
    private String image;

    @SerializedName("href")
    private String href;

    @SerializedName("menu_category_name")
    private String menu_category_name;

    public FoodData() {

    }
    public FoodData(int id, String name, String price, String image, String href, String menu_category_name) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
        this.href = href;
        this.menu_category_name = menu_category_name;
    }

    public String getMenu_category_name() {
        return menu_category_name;
    }

    public int getId() {
        return id;
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
}
