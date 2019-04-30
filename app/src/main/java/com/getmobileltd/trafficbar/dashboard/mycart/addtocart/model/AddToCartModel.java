/*
 * *
 * Creator: Tobiloba Adejumo on 3/16/19 2:19 PM Last modified: 3/16/19 2:19 PM Copyright: All rights reserved Ⓒ 2019
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

package com.getmobileltd.trafficbar.dashboard.mycart.addtocart.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by themavencoder on 16,March,2019
 */
public class AddToCartModel {

    @SerializedName("menu_id")
    private int menuId;
    @SerializedName("quantity")
    private int quantity;

    public AddToCartModel(int menuId, int quantity) {
        this.menuId = menuId;
        this.quantity = quantity;

    }

    public int getMenuId() {
        return menuId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Expose(deserialize = false)
    private String name;
    @Expose(deserialize = false)
    private String price;
    public AddToCartModel() {}

    public AddToCartModel(String name, String price) {
        this.name = name;
        this.price = price;
    }

    public String getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }
}
