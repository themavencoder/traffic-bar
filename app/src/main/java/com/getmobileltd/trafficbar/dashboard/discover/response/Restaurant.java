/*
 * *
 * Creator: Tobiloba Adejumo on 4/23/19 11:26 AM Last modified: 4/23/19 11:26 AM Copyright: All rights reserved Ⓒ 2019
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

package com.getmobileltd.trafficbar.dashboard.discover.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by themavencoder on 23,April,2019
 */
public class Restaurant {
    @SerializedName("id")
    private int id;

    public Restaurant() {

    }
   @Expose(serialize = false)
   private boolean isSelected = false;

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public Restaurant(String address) {
        this.address = address;
    }
    @SerializedName("restaurant_name")
    private String name;

    @SerializedName("restaurant_address")
    private String address;

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }
}
