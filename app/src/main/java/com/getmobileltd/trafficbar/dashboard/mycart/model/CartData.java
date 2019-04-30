/*
 * *
 * Creator: Tobiloba Adejumo on 4/29/19 12:43 PM Last modified: 4/29/19 12:43 PM Copyright: All rights reserved Ⓒ 2019
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

package com.getmobileltd.trafficbar.dashboard.mycart.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by themavencoder on 29,April,2019
 */
public class CartData {

    @SerializedName("id")
    private int id;

    @SerializedName("user_id")
    private int user_id;

    @SerializedName("menu_id")
    private int menu_id;

    @SerializedName("name")
    private String name;

    @SerializedName("category")
    private String category;

    @SerializedName("price")
    private String price;

    @SerializedName("image")
    private String image;

    @SerializedName("quanity")
    private int quantity;

    @SerializedName("total")
    private String total;

    @SerializedName("tax")
    private String tax;

    public int getQuantity() {
        return quantity;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public CartData() {

    }

    public String getTotal() {
        return total;
    }

    public CartData(int id, int user_id, int menu_id, String name, String category, String price, String image, int quantity, String total, String tax) {
        this.id = id;
        this.user_id = user_id;
        this.menu_id = menu_id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.image = image;
        this.quantity = quantity;
        this.total = total;
        this.tax = tax;
    }

    public String getPrice() {
        return price;
    }

    public int getMenu_id() {
        return menu_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getCategory() {
        return category;
    }

    public String getImage() {
        return image;
    }

    public String getTax() {
        return tax;
    }

}
