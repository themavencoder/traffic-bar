/*
 * *
 * Creator: Tobiloba Adejumo on 3/6/19 3:47 PM Last modified: 3/6/19 3:47 PM Copyright: All rights reserved Ⓒ 2019
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
 * Created by themavencoder on 06,March,2019
 */
public class MenuDetailsModel {
    @SerializedName("status")
    private String status;
    @SerializedName("code")
    private int code;
    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private MenuDetailsInformation data;

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }

    public MenuDetailsInformation getData() {
        return data;
    }

    public int getCode() {
        return code;
    }

    private String name;
    private String price;

    public MenuDetailsModel(String name, String price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
