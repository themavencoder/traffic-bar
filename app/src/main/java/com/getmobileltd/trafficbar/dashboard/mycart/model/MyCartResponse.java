/*
 * *
 * Creator: Tobiloba Adejumo on 4/29/19 12:42 PM Last modified: 4/29/19 12:42 PM Copyright: All rights reserved Ⓒ 2019
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

import java.util.List;

/**
 * Created by themavencoder on 29,April,2019
 */
public class MyCartResponse {

    @SerializedName("status")
    public String status;

    @SerializedName("data")
    public List<CartData> data;

    @SerializedName("count")
    private int count;

    public int getCount() {
        return count;
    }

    public String getStatus() {
        return status;
    }

    public List<CartData> getData() {
        return data;
    }
}
