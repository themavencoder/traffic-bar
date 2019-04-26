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

import com.google.gson.annotations.SerializedName;

/**
 * Created by themavencoder on 26,April,2019
 */
public class Menus {

    @SerializedName("id")
    private int id;
    @SerializedName("category_id")
    private int category_id;
    @SerializedName("menu_name")
    private String menu_name;
    @SerializedName("menu_description")
    private String menu_description;
    @SerializedName("menu_price")
    private String menu_price;
    @SerializedName("menu_image_sm")
    private String menu_image_sm;
    @SerializedName("menu_image_bg")
    private String menu_image_bg;

    public Menus(String menu_name, String menu_price, String menu_image_sm) {
        this.menu_name = menu_name;
        this.menu_price = menu_price;
        this.menu_image_sm = menu_image_sm;

    }

    public int getId() {
        return id;
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

}
