/*
 * *
 * Creator: Tobiloba Adejumo on 4/30/19 9:08 AM Last modified: 4/30/19 9:07 AM Copyright: All rights reserved Ⓒ 2019
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

import com.getmobileltd.trafficbar.dashboard.home.food.FoodData;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by themavencoder on 30,April,2019
 */
public class DrinkResponse {

    @SerializedName("status")
    private String status;

    @SerializedName("code")
    private int code;

    @SerializedName("count")
    private int count;

    @SerializedName("messsage")
    private String message;

    @SerializedName("data")
    private List<DrinkData> data;

    public int getCode() {
        return code;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public List<DrinkData> getData() {
        return data;
    }

    public int getCount() {
        return count;
    }
}
